package am.aua.chess.puzzles;

import am.aua.chess.core.Chess;
import am.aua.chess.utils.MalformedPuzzleException;

/**
 * Represents a chess puzzle.
 */
public final class Puzzle implements Comparable<Puzzle>{

    /**
     * Represents the difficulty level of the puzzle.
     */
    public enum Difficulty {
        EASY, MEDIUM, HARD, UNSPECIFIED
    }

    private Difficulty difficulty;
    private Chess.PieceColor turn;
    private String arrangement;
    private String description;

    /**
     * Constructs a new Puzzle object with the given arrangement and description.
     * 
     * @param arrangement the chess board arrangement as a string of 64 characters
     * @param description the description of the puzzle
     * @throws MalformedPuzzleException if the puzzle arrangement is malformed
     */
    public Puzzle(String arrangement, String description) throws MalformedPuzzleException{
        this.parsePuzzleDetails(arrangement);
        this.description = description;
    }

    /**
     * Gets the chess board arrangement of the puzzle.
     * 
     * @return the chess board arrangement
     */
    public String getArrangement() {
        return arrangement;
    }

    /**
     * Gets the description of the puzzle.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Gets the turn color of the puzzle.
     * 
     * @return the turn color
     */
    public Chess.PieceColor getTurn() {
        return turn;
    }

    /**
     * Gets the difficulty level of the puzzle.
     * 
     * @return the difficulty level
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    private void parsePuzzleDetails(String boardDetails) throws MalformedPuzzleException {
        String[] splittedDetails = getDetails(boardDetails);
        if (!splittedDetails[2].equalsIgnoreCase("easy") && !splittedDetails[2].equalsIgnoreCase("medium") && !splittedDetails[2].equalsIgnoreCase("hard")) {
            throw new MalformedPuzzleException("The given Difficulty should be either Easy, Medium, Hard of UNSPECIFIED.");
        }

        this.arrangement = splittedDetails[0];
        this.turn = Chess.PieceColor.valueOf(splittedDetails[1].toUpperCase());
        this.difficulty = Difficulty.valueOf(splittedDetails[2].toUpperCase());
    }

    private static String[] getDetails(String boardDetails) throws MalformedPuzzleException {
        String[] splittedDetails = boardDetails.split(",");
        if (splittedDetails.length != 3) {
            throw new MalformedPuzzleException("The given Puzzle Should contain 3 parts: Arrangement(64 length), Turn, and Difficulty.");
        }
        if (splittedDetails[0].length() != 64) {
            throw new MalformedPuzzleException("The given Arrangement should be 64 characters long.");
        }
        if (!splittedDetails[1].equalsIgnoreCase("white") && !splittedDetails[1].equalsIgnoreCase("black")) {
            throw new MalformedPuzzleException("The given Turn should be either White or Black.");
        }
        return splittedDetails;
    }

    private int compareDifficulties(Difficulty d1, Difficulty d2){
        if (d1 == d2){
            return 0;
        }
        else if (d1 == Difficulty.EASY && d2 == Difficulty.MEDIUM){
            return -1;
        }
        else if (d1 == Difficulty.EASY && d2 == Difficulty.HARD){
            return -1;
        }
        else if (d1 == Difficulty.MEDIUM && d2 == Difficulty.HARD){
            return -1;
        }
        else {
            return 1;
        }

    }

    /**
     * Compares this puzzle with the specified puzzle for order.
     * 
     * @param that the puzzle to be compared
     * @return a negative integer, zero, or a positive integer as this puzzle is less than, equal to, or greater than the specified puzzle
     * @throws NullPointerException if the specified puzzle is null
     */
    public int compareTo(Puzzle that) {
        if (that == null)
            throw new NullPointerException();

        if (this.equals(that)){
            return 0;
        }
        if (this.difficulty != that.difficulty){
            return compareDifficulties(this.difficulty, that.difficulty);
        }
        else if (this.turn != that.turn){
            if (this.turn == Chess.PieceColor.WHITE)
                return -1;
            else
                return 1;
        }
        else {
            return this.arrangement.compareTo(that.arrangement);
        }
    }

    /**
     * Checks if this puzzle is equal to the specified object.
     * 
     * @param that the object to compare
     * @return true if the specified object is equal to this puzzle, false otherwise
     */
    public boolean equals(Object that){
        if (that instanceof Puzzle){
            Puzzle thatPuzzle = (Puzzle) that;
            if (this.difficulty == thatPuzzle.difficulty &&
                this.turn == thatPuzzle.turn &&
                this.arrangement.equals(thatPuzzle.arrangement)){
                return true;
            }
        }

        return false;
    }

    public String toString(){
        return this.arrangement + "," + this.turn + "," + this.difficulty + "\n" + this.description;
    }

}
