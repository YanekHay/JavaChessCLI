package am.aua.chess.utils;

/**
 * This exception is thrown when a puzzle is malformed and does not satisfy the requirements.
 */
public class MalformedPuzzleException extends Exception{
    /**
     * Constructs a new MalformedPuzzleException with a default error message.
     */
    public MalformedPuzzleException() {
        super("The given Puzzle does not satisfy the requirements.");
    }
    
    /**
     * Constructs a new MalformedPuzzleException with the specified error message.
     * 
     * @param message the error message
     */
    public MalformedPuzzleException(String message) {
        super(message);
    }
}
