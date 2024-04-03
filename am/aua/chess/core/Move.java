package am.aua.chess.core;

/**
 * The am.aua.chess.core.Move class represents a move from one position to another.
 * It contains the origin and destination positions of the move.
 */
public class Move {
    private Position origin;
    private Position destination;

    /**
     * Constructs a am.aua.chess.core.Move object with the given origin and destination positions.
     * 
     * @param origin the origin position of the move
     * @param destination the destination position of the move
     */
    public Move(Position origin, Position destination) {
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Constructs a am.aua.chess.core.Move object by copying the origin and destination positions from another am.aua.chess.core.Move object.
     * 
     * @param that the am.aua.chess.core.Move object to copy from
     */
    public Move(Move that) {
        this(that.getOrigin(), that.getDestination());
    }

    /**
     * Returns a copy of the origin position of the move.
     * 
     * @return a copy of the origin position
     */
    public Position getOrigin() {
        return new Position(this.origin);
    }

    /**
     * Returns a copy of the destination position of the move.
     * 
     * @return a copy of the destination position
     */
    public Position getDestination() {
        return new Position(this.destination);
    }

    /**
     * Sets the origin position of the move.
     * 
     * @param origin the new origin position
     */
    public void setOrigin(Position origin) {
        this.origin = origin;
    }

    /**
     * Sets the destination position of the move.
     * 
     * @param destination the new destination position
     */
    public void setDestination(Position destination) {
        this.destination = destination;
    }

    /**
     * Returns a string representation of the move.
     * The string contains the origin and destination positions separated by a space.
     * 
     * @return a string representation of the move
     */
    public String toString() {
        return origin + " " + destination;
    }
}
