package am.aua.chess.utils;


/**
 * This class represents an exception that is thrown when the King is under attack.
 * It extends the Exception class.
 */
public class KingUnderAttackException extends Exception{

    /**
     * This constructor creates a new KingUnderAttackException with a custom message.
     * @param message The custom message for the exception.
     */
    public KingUnderAttackException(String message){
        super(message);
    }

    /**
     * This constructor creates a new KingUnderAttackException with a default message.
     */
    public KingUnderAttackException(){
        super("King is under attack! The move can not be performed!");
    }
}
