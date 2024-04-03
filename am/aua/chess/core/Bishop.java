package am.aua.chess.core;

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
        if (this.getColor() == Chess.PieceColor.WHITE)
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
    public Position[] allDestinations(Chess chess, Position p) {
        return Bishop.reachablePositions(chess, p);
    }

    /**
     * Returns an array of reachable positions for the bishop.
     * 
     * @param chess The chess game instance.
     * @param p The current position of the bishop.
     * @return An array of reachable positions for the bishop.
     */
    public static Position[] reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {1, 1, -1, -1};
        int[] fileOffsets = {1, -1, 1, -1};
        Position[] result = new Position[0];

        for (int d = 0; d < 4; d++) {
            int i = p.getRank() + rankOffsets[d];
            int j = p.getFile() + fileOffsets[d];
            while (i >= 0 && i < Chess.BOARD_RANKS &&
                    j >= 0 && j < Chess.BOARD_FILES) {
                Position current = Position.generateFromRankAndFile(i, j);
                if (chess.isEmpty(current))
                    result = Position.appendPositionsToArray(result, current);
                else {
                    assert current != null;
                    if (chess.getPieceAt(current).getColor() != chess.getPieceAt(p).getColor())
                        result = Position.appendPositionsToArray(result, current);

                    break;
                }
                i += rankOffsets[d];
                j += fileOffsets[d];
            }
        }

        return result;
    }
}
