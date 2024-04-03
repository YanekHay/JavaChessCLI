package am.aua.chess.core;
/**
 * Rook class represents the Rook piece in the chess game.
 * It extends the Piece class and implements the allDestinations method.
 */
/**
 * The Rook class represents a rook chess piece.
 * It extends the Piece class and inherits its properties and methods.
 */
public class Rook extends Piece {
    private boolean hasMoved;

    /**
     * Constructs a new Rook object with the default color of WHITE.
     */
    public Rook() {
        super();
        this.hasMoved = false;
    }

    /**
     * Constructs a new Rook object with the specified color.
     *
     * @param color the color of the rook piece
     */
    public Rook(Chess.PieceColor color) {
        super(color);
        this.hasMoved = false;
    }

    /**
     * Constructs a new Rook object with the specified color and move status.
     *
     * @param color    the color of the rook piece
     * @param hasMoved the move status of the rook piece
     */
    public Rook(Chess.PieceColor color, boolean hasMoved) {
        super(color);
        this.hasMoved = hasMoved;
    }

    /**
     * Returns a string representation of the rook piece.
     * The string representation is "R" for white rook and "r" for black rook.
     *
     * @return a string representation of the rook piece
     */
    public String toString() {
        if (this.getColor() == Chess.PieceColor.WHITE)
            return "R";
        else
            return "r";
    }

    /**
     * Returns an array of all possible destinations for the rook piece on the chessboard.
     *
     * @param chess the chess game object
     * @param p     the current position of the rook piece
     * @return an array of all possible destinations for the rook piece
     */
    public Position[] allDestinations(Chess chess, Position p) {
        return Rook.reachablePositions(chess, p);
    }

    /**
     * Returns an array of reachable positions for the rook piece on the chessboard.
     *
     * @param chess the chess game object
     * @param p     the current position of the rook piece
     * @return an array of reachable positions for the rook piece
     */
    public static Position[] reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {1, -1, 0, 0};
        int[] fileOffsets = {0, 0, 1, -1};
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
