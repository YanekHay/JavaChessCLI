package am.aua.chess.core;

import java.util.ArrayList;

/**
 * The Queen class represents a queen chess piece.
 */
public class Queen extends Piece {

    /**
     * Constructs a new Queen object with the specified color.
     *
     * @param color the color of the Queen piece
     */
    public Queen(Chess.PieceColor color) {
        super(color);
    }

    /**
     * Constructs a new Queen object with the default color.
     * The default color is Chess.PieceColor.WHITE.
     */
    public Queen() {
        super();
    }

    /**
     * Returns an array of all possible destinations for the Queen piece on the chessboard.
     *
     * @param chess the Chess object representing the chessboard
     * @param p the current position of the Queen piece
     * @return an array of Position objects representing the reachable positions
     */
    public ArrayList<Position> allDestinations(Chess chess, Position p) {
        return Queen.reachablePositions(chess, p);
    }

    /**
     * Returns an array of reachable positions for the Queen piece on the chessboard.
     *
     * @param chess the Chess object representing the chessboard
     * @param p the current position of the Queen piece
     * @return an array of Position objects representing the reachable positions
     */
    public static ArrayList<Position> reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {-1, -1, 1, 1, 0, 0, -1, 1};
        int[] fileOffsets = {-1, 1, -1, 1, -1, 1, 0, 0};
        ArrayList<Position> result = new ArrayList<>();

        for (int d = 0; d < rankOffsets.length; d++) {
            int i = p.getRank() + rankOffsets[d];
            int j = p.getFile() + fileOffsets[d];
            while (i >= 0 && i < Chess.BOARD_RANKS &&
                    j >= 0 && j < Chess.BOARD_FILES) {
                Position current = Position.generateFromRankAndFile(i, j);
                if (chess.isEmpty(current))
                    result.add(current);
                else {
                    assert current != null;
                    if (chess.getPieceAt(current).getPieceColor() != chess.getPieceAt(p).getPieceColor())
                        result.add(current);
                    break;
                }
                i += rankOffsets[d];
                j += fileOffsets[d];
            }
        }

        return result;
    }

    /**
     * Returns a string representation of the Queen piece.
     * The string is "Q" for white Queen and "q" for black Queen.
     *
     * @return a string representation of the Queen piece
     */
    public String toString() {
        return this.getPieceColor() == Chess.PieceColor.WHITE ? "Q" : "q";
    }

    /**
     * Returns a clone of the Queen object.
     */
    public Queen clone() {
        return (Queen) super.clone();
    }
}
