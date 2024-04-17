package am.aua.chess.core;

import java.util.ArrayList;

/**
 * The Knight class represents a knight piece in chess.
 */
public class Knight extends Piece {

    /**
     * Constructs a new Knight object with the specified color.
     *
     * @param color The color of the knight piece.
     */
    public Knight(Chess.PieceColor color) {
        super(color);
    }

    /**
     * Constructs a new Knight object with no specified color.
     * The color will be set to null.
     */
    public Knight(){
        super();
    }

    /**
     * Returns an array of positions that a knight can reach from a given position on a chessboard.
     *
     * @param chess The Chess object representing the chessboard.
     * @param p The starting position of the knight.
     * @return An array of Position objects representing the reachable positions.
     */
    public ArrayList<Position> allDestinations(Chess chess, Position p){
        return Knight.reachablePositions(chess, p);
    }

    /**
     * Returns an array of positions that a knight can reach from a given position on a chessboard.
     *
     * @param chess The Chess object representing the chessboard.
     * @param p The starting position of the knight.
     * @return An array of Position objects representing the reachable positions.
     */
    public static ArrayList<Position> reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] fileOffsets = {-1, 1, -1, 1, -2, 2, -2, 2};
        ArrayList<Position> positions = new ArrayList<>();
        int row = p.getRank();
        int column = p.getFile();

        for (int d = 0; d < rankOffsets.length; d++) {
            int i = row + rankOffsets[d];
            int j = column + fileOffsets[d];

            if ((i >= 0) && (i < Chess.BOARD_RANKS) && (j >= 0) && (j < Chess.BOARD_FILES)) {
                Position current = Position.generateFromRankAndFile(i, j);
                if (chess.isEmpty(current) || (current != null &&
                        chess.getPieceAt(current).getPieceColor() != chess.getPieceAt(p).getPieceColor())) {
                    positions.add(current);
                }
            }
        }
        return positions;
    }

    /**
     * Returns a string representation of the knight.
     *
     * @return The string representation of the knight.
     */
    public String toString() {
        return this.getPieceColor()==Chess.PieceColor.WHITE ? "N" : "n";
    }

    /**
     * Returns a clone of the Knight object.
     */
    public Knight clone() {
        return (Knight) super.clone();
    }
}
