package am.aua.chess.utils;

/**
 * The IllegalArrangementException class represents an exception that is thrown when an illegal arrangement of chess pieces is detected.
 */
public class IllegalArrangementException extends Exception {
    /**
     * Constructs a new IllegalArrangementException with a default error message.
     */
    public IllegalArrangementException() {
        super("Illegal arrangement of pieces.");
    }

    /**
     * Constructs a new IllegalArrangementException with the specified error message.
     *
     * @param message the error message
     */
    public IllegalArrangementException(String message) {
        super(message);
    }
}
