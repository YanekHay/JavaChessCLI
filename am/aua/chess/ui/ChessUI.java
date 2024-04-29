package am.aua.chess.ui;

import am.aua.chess.core.Chess;
import am.aua.chess.core.Move;
import am.aua.chess.core.Piece;
import am.aua.chess.core.Position;
import am.aua.chess.utils.IllegalArrangementException;
import am.aua.chess.utils.KingUnderAttackException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class ChessUI  extends JFrame {
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
                board[i][j].addActionListener(this::onSquareClick);
                this.boardPanel.add(board[i][j]);
            }
        }
    }

    private void onSquareClick(ActionEvent event) {
        BoardSquare square = (BoardSquare) event.getSource();
        int rank = square.getRank();
        int file = square.getFile();
        Position position = new Position(rank, file);
        Piece piece = game.getPieceAt(position);

        if (selectedPosition==null && piece!=null){
            if (piece.getPieceColor() != game.getTurn()) {
                JOptionPane.showMessageDialog(this.boardPanel, "That piece belongs to the opponent.", "Not Your Turn", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            this.highlightedPositions = piece.allDestinations(this.game, position);
            this.selectedPosition = position;
            for(Position p : highlightedPositions){
                board[p.getRank()][p.getFile()].setHighlight(true);
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
}
