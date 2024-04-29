package am.aua.chess.utils;

public class KingUnderAttackException extends Exception{
    public KingUnderAttackException(String message){
        super(message);
    }

    public KingUnderAttackException(){
        super("King is under attack! The move can not be performed!");
    }
}
