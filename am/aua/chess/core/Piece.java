package am.aua.chess.core;
/**
 * The Piece class represents a chess piece.
 * It contains methods to get the color of the piece and all possible destinations for the piece.
 * The class is extended by specific piece classes such as Bishop, King, Knight, Pawn, Queen, and Rook.
 */
public abstract class Piece implements Cloneable {
    private final Chess.PieceColor color;

    /**
     * Constructs a new Piece object with the specified color.
     *
     * @param color the color of the piece
     */
    public Piece(Chess.PieceColor color) {
        this.color = color;
    }

    /**
     * Constructs a new Piece object with the default color (WHITE).
     */
    public Piece(){
        this(Chess.PieceColor.WHITE);
    }

    /**
     * Constructs a new Piece object with the default color (WHITE).
     */
    public Piece(Piece that){
        this.color = that.getPieceColor();
    }
    /**
     * Returns the color of the piece.
     *
     * @return the color of the piece
     */
    public final Chess.PieceColor getPieceColor() {
        return color;
    }

    /**
     * Returns an array of all possible destinations for the piece on the chessboard.
     *
     * @param chess the Chess object representing the chessboard
     * @param p the current position of the piece
     * @return an array of all possible destinations for the piece
     */
    public abstract Position[] allDestinations(Chess chess, Position p);

    /**
     * Returns a string representation of the piece.
     *
     * @return a string representation of the piece
     */
    public String toString(){
        return "Piece of " + this.getPieceColor() + " color";
    }

    /**
     * Returns a clone of the Piece object.
     */
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not allowed.");
            return null;
        }
    }
}
