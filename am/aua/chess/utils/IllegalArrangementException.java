package am.aua.chess.utils;

public class IllegalArrangementException extends Exception {
    public IllegalArrangementException() {
        super("Illegal arrangement of pieces.");
    }
    public IllegalArrangementException(String message) {
        super(message);
    }
}
