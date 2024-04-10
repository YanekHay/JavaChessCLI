package am.aua.chess.utils;

/**
 * This exception is thrown when the arrangement string contains an invalid number of kings.
 * The arrangement string should contain exactly 2 kings: 1 White and 1 Black.
 */
public class InvalidNumberOfKingsException extends IllegalArrangementException {
    /**
     * Constructs a new InvalidNumberOfKingsException with a default error message.
     */
    public InvalidNumberOfKingsException() {
        super("The arrangement string should contain exactly 2 kings: 1 White and 1 Black.");
    }

    /**
     * Constructs a new InvalidNumberOfKingsException with the specified error message.
     *
     * @param message the error message
     */
    public InvalidNumberOfKingsException(String message) {
        super(message);
    }
}
