package am.aua.chess.utils;

public class MalformedPuzzleException extends Exception{
    public MalformedPuzzleException() {
        super("The given Puzzle does not satisfy the requirements.");
    }
    public MalformedPuzzleException(String message) {
        super(message);
    }
}
