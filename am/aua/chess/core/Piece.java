package am.aua.chess.core;
/**
 * The Piece class represents a chess piece.
 * It contains methods to get the color of the piece and all possible destinations for the piece.
 * The class is extended by specific piece classes such as Bishop, King, Knight, Pawn, Queen, and Rook.
 */
public class Piece {
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
        this.color = that.getColor();
    }
    /**
     * Returns the color of the piece.
     *
     * @return the color of the piece
     */
    public Chess.PieceColor getColor() {
        return color;
    }

    /**
     * Returns an array of all possible destinations for the piece on the chessboard.
     *
     * @param chess the Chess object representing the chessboard
     * @param p the current position of the piece
     * @return an array of all possible destinations for the piece
     */
    public Position[] allDestinations(Chess chess, Position p){
        return null;
    }

    /**
     * Returns a string representation of the piece.
     *
     * @return a string representation of the piece
     */
    public String toString(){
        return "Piece of " + this.getColor() + " color";
    }
}
