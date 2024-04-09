package am.aua.chess.utils;

public class InvalidNumberOfKingsException extends IllegalArrangementException{
    public InvalidNumberOfKingsException() {
        super("The positioning string should contain exactly 2 kings: 1 White and 1 Black.");
    }
    public InvalidNumberOfKingsException(String message) {
        super(message);
    }
}
