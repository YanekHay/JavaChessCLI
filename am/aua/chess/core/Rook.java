package am.aua.chess.core;
/**
 * Rook class represents the Rook piece in the chess game.
 * It extends the Piece class and implements the allDestinations method.
 */

import java.util.ArrayList;

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
     * Returns the move status of the rook piece.
     *
     * @return the move status of the rook piece
     */
    public boolean getHasMoved() {
        return this.hasMoved;
    }

    /**
     * Sets the move status of the rook piece.
     */
    public void move() {
        this.hasMoved = true;
    }

    /**
     * Returns a string representation of the rook piece.
     * The string representation is "R" for white rook and "r" for black rook.
     *
     * @return a string representation of the rook piece
     */
    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            if (this.hasMoved)
                return "S";
            else
                return "R";
        else
        if (this.hasMoved)
            return "s";
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
    public ArrayList<Position> allDestinations(Chess chess, Position p) {
        return Rook.reachablePositions(chess, p);
    }

    /**
     * Returns an array of reachable positions for the rook piece on the chessboard.
     *
     * @param chess the chess game object
     * @param p     the current position of the rook piece
     * @return an array of reachable positions for the rook piece
     */
    public static ArrayList<Position> reachablePositions(Chess chess, Position p) {
        int[] rankOffsets = {1, -1, 0, 0};
        int[] fileOffsets = {0, 0, 1, -1};
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
     * Returns a clone of the Rook object.
     */
    public Rook clone() {
        Rook r = (Rook) super.clone();
        r.hasMoved = this.hasMoved;
        return r;
    }
}
