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
public class ChessUI  extends JFrame implements ActionListener {
    private Chess game;
    private JPanel boardPanel = new JPanel();
    private BoardSquare[][] board = new BoardSquare[Chess.BOARD_RANKS][Chess.BOARD_FILES];
    public static final int WINDOW_SIZE = 800;
    private ArrayList<Position> highlightedPositions = new ArrayList<>();
    private Position selectedPosition;
    public ChessUI() {
        this.game = new Chess();
        this.setTitle("Chess");
        this.setSize(WINDOW_SIZE, WINDOW_SIZE);

        this.setLayout(new FlowLayout());
        boardPanel.setLayout(new GridLayout(Chess.BOARD_RANKS, Chess.BOARD_FILES));
        this.add(boardPanel, BorderLayout.CENTER);
        this.addCells();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

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

    @Override
    public void actionPerformed(ActionEvent event) {
        BoardSquare square = (BoardSquare) event.getSource();
        boardClicked(square.getCoordinate());
    }
}
