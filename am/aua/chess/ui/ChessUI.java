package am.aua.chess.ui;

import am.aua.chess.core.Move;
import am.aua.chess.core.Piece;
import am.aua.chess.core.Chess;
import am.aua.chess.core.Position;
import am.aua.chess.utils.KingUnderAttackException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * This class represents the User Interface for the Chess game.
 * It extends JFrame and implements ActionListener to handle user interactions.
 */
public class ChessUI  extends JFrame implements ActionListener {

    private final Chess game;
    private final JPanel boardPanel = new JPanel();
    private final BoardSquare[][] board = new BoardSquare[Chess.BOARD_RANKS][Chess.BOARD_FILES];
    public static final int WINDOW_HEIGHT = BoardSquare.SIZE* Chess.BOARD_RANKS+50;
    public static final int WINDOW_WIDTH = BoardSquare.SIZE* Chess.BOARD_FILES+50;
    private ArrayList<Position> highlightedPositions = new ArrayList<>();
    private Position selectedPosition;

    /**
     * The constructor for the ChessUI class.
     * It initializes the game, sets the title, size, layout of the window, adds cells to the board and makes the window visible.
     */public ChessUI() {
        this.game = new Chess();
        this.setTitle("Chess");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        this.setLayout(new FlowLayout());
        boardPanel.setLayout(new GridLayout(Chess.BOARD_RANKS, Chess.BOARD_FILES));
        this.add(boardPanel, BorderLayout.CENTER);
        this.addCells();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * This method adds cells to the board.
     * It iterates over the ranks and files, creates a new BoardSquare for each position, and adds it to the boardPanel.
     */
    private void addCells(){
        for (int i = Chess.BOARD_RANKS-1; i>=0; i--) {
            for (int j = 0; j< Chess.BOARD_FILES; j++) {
                Position p = new Position(i, j);
                Piece piece = game.getPieceAt(p);
                board[i][j] = new BoardSquare(i, j, (i+j)%2==1);
                if (piece != null){
                    board[i][j].setPiece(piece.toString());
                }
                board[i][j].addActionListener(this);
                this.boardPanel.add(board[i][j]);
            }
        }
    }

    /**
     * This method is called when a board is clicked.
     * It takes the coordinates of the clicked position, checks if a piece can be moved from the selected position to the clicked position,
     * and performs the move if it is valid.
     * @param coordinates The coordinates of the clicked position.
     */
    private void boardClicked(int[] coordinates) {
        int rank = coordinates[0];
        int file = coordinates[1];
        Position position = Position.generateFromRankAndFile(rank, file);
        if (position==null){
            return;
        }
        Piece piece = game.getPieceAt(position);

        if (selectedPosition==null){
            if (piece != null) {
                if (piece.getPieceColor() != game.getTurn()) {
                    JOptionPane.showMessageDialog(this.boardPanel, "That piece belongs to the opponent.", "Not Your Turn", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                this.highlightedPositions = piece.allDestinations(this.game, position);
                this.selectedPosition = position;
                board[rank][file].setSelected(true);
                for(Position p : highlightedPositions){
                    board[p.getRank()][p.getFile()].setHighlight(true);
                }
            }
        }
        else {
            if (highlightedPositions.contains(position)) {
                try{
                    game.performMove(new Move(selectedPosition, position));
                }
                catch (KingUnderAttackException e){
                    JOptionPane.showMessageDialog(this.boardPanel, e.getMessage(), "King Under Attack", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            for (Position p : highlightedPositions) {
                board[p.getRank()][p.getFile()].setHighlight(false);
            }
            highlightedPositions.clear();
            board[this.selectedPosition.getRank()][this.selectedPosition.getFile()].setSelected(false);
            selectedPosition = null;
            updatePieces();
        }
    }

    /**
     * This method updates the pieces on the board.
     * It iterates over the ranks and files, gets the piece at each position, and updates the BoardSquare at that position.
     */
    public void updatePieces(){
        for (int i = 0; i<Chess.BOARD_RANKS; i++) {
            for (int j = 0; j< Chess.BOARD_FILES; j++) {
                Position p = new Position(i, j);
                Piece piece = game.getPieceAt(p);
                if (piece != null){
                    board[i][j].setPiece(piece.toString());
                }
                else {
                    board[i][j].setPiece();
                }
            }
        }
    }

    /**
     * This method is called when an action is performed.
     * It gets the source of the event, casts it to a BoardSquare, and calls the boardClicked method with the coordinates of the BoardSquare.
     * @param event The action event.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        BoardSquare square = (BoardSquare) event.getSource();
        boardClicked(square.getCoordinate());
    }
}
