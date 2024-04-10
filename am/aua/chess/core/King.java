package am.aua.chess.core;

/**
 * The King class represents a king chess piece.
 */
public class King extends Piece {
    private boolean hasMoved;

    /**
     * Default constructor for the King class.
     * Initializes the King with default values and sets hasMoved to false.
     */
    public King() {
        super();
        this.hasMoved = false;
    }

    /**
     * Constructor for the King class with a specified color.
     * Initializes the King with the given color and sets hasMoved to false.
     *
     * @param color The color of the King.
     */
    public King(Chess.PieceColor color) {
        super(color);
        this.hasMoved = false;
    }

    /**
     * Constructor for the King class with a specified color and hasMoved value.
     * Initializes the King with the given color and hasMoved value.
     *
     * @param color    The color of the King.
     * @param hasMoved The hasMoved value of the King.
     */
    public King(Chess.PieceColor color, boolean hasMoved) {
        super(color);
        this.hasMoved = hasMoved;
    }

    /**
     * Returns the hasMoved value of the King.
     *
     * @return The hasMoved value of the King.
     */
    public boolean getHasMoved() {
        return this.hasMoved;
    }

    /**
     * Sets the hasMoved value of the King.
     */
    public void move() {
        this.hasMoved = true;
    }

    /**
     * Returns a string representation of the King.
     * If the King's color is WHITE, returns "K". Otherwise, returns "k".
     *
     * @return The string representation of the King.
     */
    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            if (this.hasMoved)
                return "L";
            else
                return "K";
        else if (this.hasMoved)
            return "l";
        else
            return "k";
    }

    /**
     * Returns an array of all possible destinations for the King.
     * Calls the static reachablePositions method to calculate the reachable positions.
     *
     * @param chess The Chess object.
     * @param p     The current position of the King.
     * @return An array of Position objects representing the reachable positions.
     */
    public Position[] allDestinations(Chess chess, Position p) {
        return King.reachablePositions(chess, p);
    }

    /**
     * Calculates the reachable positions for the King.
     * Checks all possible positions around the King and adds them to the result array.
     *
     * @param chess The Chess object.
     * @param p     The current position of the King.
     * @return An array of Position objects representing the reachable positions.
     */
    public static Position[] reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {-1, -1, 1, 1, 0, 0, -1, 1};
        int[] fileOffsets = {-1, 1, -1, 1, -1, 1, 0, 0};
        Position[] result = new Position[0];

        for (int d = 0; d < rankOffsets.length; d++) {
            int i = p.getRank() + rankOffsets[d];
            int j = p.getFile() + fileOffsets[d];
            if (i >= 0 && i < Chess.BOARD_RANKS &&
                    j >= 0 && j < Chess.BOARD_FILES) {
                Position current = Position.generateFromRankAndFile(i, j);
                if (chess.isEmpty(current))
                    result = Position.appendPositionsToArray(result, current);
                else {
                    assert current != null;
                    if (chess.getPieceAt(current).getPieceColor() != chess.getPieceAt(p).getPieceColor())
                        result = Position.appendPositionsToArray(result, current);
                }
            }
        }

        return result;
    }

    /**
     * Returns a clone of the King object.
     */
    public King clone() {
        King k = (King) super.clone();
        k.hasMoved = this.hasMoved;
        return k;
    }
}
