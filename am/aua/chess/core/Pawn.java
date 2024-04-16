package am.aua.chess.core;

/**
 * The Pawn class represents a pawn chess piece.
 */
public class Pawn extends Piece{

    /**
     * Default constructor for Pawn class.
     * Initializes the hasMoved variable to false.
     */
    public Pawn(){
        super();
    }

    /**
     * Constructor for Pawn class with color parameter.
     * Initializes the hasMoved variable to false.
     * @param color The color of the pawn.
     */
    public Pawn(Chess.PieceColor color){
        super(color);
    }


    /**
     * Returns a string representation of the pawn.
     * @return "P" for white pawn, "p" for black pawn.
     */
    public String toString(){
        if(this.getPieceColor() == Chess.PieceColor.WHITE)
            return "P";
        else
            return "p";
    }

    /**
     * Generates all possible destinations for the pawn.
     * @param chess The chess game instance.
     * @param p The current position of the pawn.
     * @return An array of positions representing all possible destinations for the pawn.
     */
    public Position[] allDestinations(Chess chess, Position p){
        Position[] result = new Position[0];
        int rank = p.getRank();
        int file = p.getFile();
        int direction = this.getPieceColor() == Chess.PieceColor.WHITE ? 1 : -1;
        Position forward = Position.generateFromRankAndFile(rank + direction, file);
        Position leftDiagonal = Position.generateFromRankAndFile(rank + direction, file - 1);
        Position rightDiagonal = Position.generateFromRankAndFile(rank + direction, file + 1);

        if (this.isPositionAvailable(chess, forward))
            result = Position.appendPositionsToArray(result, forward);
        if (leftDiagonal!=null && isAttackPositionAvailable(chess, leftDiagonal))
            result = Position.appendPositionsToArray(result, leftDiagonal);
        if (rightDiagonal!=null && isAttackPositionAvailable(chess, rightDiagonal))
            result = Position.appendPositionsToArray(result, rightDiagonal);

        if ((this.getPieceColor()== Chess.PieceColor.WHITE && p.getRank()==1) || (this.getPieceColor()== Chess.PieceColor.BLACK && p.getRank()==6)) {
            Position doubleForward = Position.generateFromRankAndFile(rank + 2 * direction, file);
            if (doubleForward != null && this.isPositionAvailable(chess, doubleForward) && this.isPositionAvailable(chess, forward))
                result = Position.appendPositionsToArray(result, doubleForward);
        }
        return result;
    }

    /**
     * Checks if a position is available for the pawn to move to.
     * @param chess The chess game instance.
     * @param p The position to check.
     * @return true if the position is available, false otherwise.
     */
    private boolean isPositionAvailable(Chess chess, Position p){
        return chess.isEmpty(p);
    }

    /**
     * Checks if a position is available for the pawn to attack.
     * @param chess The chess game instance.
     * @param p The position to check.
     * @return true if the position is available for attack, false otherwise.
     */
    private boolean isAttackPositionAvailable(Chess chess, Position p){
        return !chess.isEmpty(p) && (this.getPieceColor() != chess.getPieceAt(p).getPieceColor());
    }

    /**
     * Returns a clone of the Pawn object.
     */
    public Pawn clone(){
        Pawn p = (Pawn) super.clone();
        return p;
    }
}
