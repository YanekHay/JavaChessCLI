package am.aua.chess.core;

import java.util.ArrayList;

/**
 * The Bishop class represents a bishop piece in chess.
 * It contains methods to get all possible destinations for the bishop.
 * The bishop can move diagonally in any direction.
 */
public class Bishop extends Piece {
    /**
     * Default constructor for the Bishop class.
     */
    public Bishop() {
        super();
    }

    /**
     * Constructor for the Bishop class with a specified color.
     * 
     * @param color The color of the bishop.
     */
    public Bishop(Chess.PieceColor color) {
        super(color);
    }

    /**
     * Returns a string representation of the bishop.
     * 
     * @return The string representation of the bishop.
     */
    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "B";
        else
            return "b";
    }

    /**
     * Returns an array of all possible destinations for the bishop.
     * 
     * @param chess The chess game instance.
     * @param p The current position of the bishop.
     * @return An array of all possible destinations for the bishop.
     */
    public ArrayList<Position> allDestinations(Chess chess, Position p) {
        return Bishop.reachablePositions(chess, p);
    }

    /**
     * Returns an array of reachable positions for the bishop.
     * 
     * @param chess The chess game instance.
     * @param p The current position of the bishop.
     * @return An array of reachable positions for the bishop.
     */
    public static ArrayList<Position> reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {1, 1, -1, -1};
        int[] fileOffsets = {1, -1, 1, -1};
        ArrayList<Position> result = new ArrayList<>();

        for (int d = 0; d < 4; d++) {
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
     * Returns a clone of the Bishop object.
     */
    public Bishop clone() {
        return (Bishop) super.clone();
    }
}
