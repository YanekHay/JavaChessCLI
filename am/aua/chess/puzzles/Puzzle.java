package am.aua.chess.puzzles;

import am.aua.chess.core.Chess;
import am.aua.chess.utils.MalformedPuzzleException;

public final class Puzzle implements Comparable<Puzzle>{



    public enum Difficulty {
        EASY, MEDIUM, HARD, UNSPECIFIED
    }
    private Difficulty difficulty;
    private Chess.PieceColor turn;
    private String arrangement;
    private String description;

    public Puzzle(String arrangement, String description) throws MalformedPuzzleException{
        System.out.println("ARG  " + arrangement);
        this.parsePuzzleDetails(arrangement);
        this.description = description;
    }

    public String getArrangement() {
        return arrangement;
    }
    public Chess.PieceColor getTurn() {
        return turn;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    private void parsePuzzleDetails(String boardDetails) throws MalformedPuzzleException {
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
        if (!splittedDetails[2].equalsIgnoreCase("easy") && !splittedDetails[2].equalsIgnoreCase("medium") && !splittedDetails[2].equalsIgnoreCase("hard")) {
            throw new MalformedPuzzleException("The given Difficulty should be either Easy, Medium, Hard of UNSPECIFIED.");
        }

        this.arrangement = splittedDetails[0];
        this.turn = Chess.PieceColor.valueOf(splittedDetails[1].toUpperCase());
        this.difficulty = Difficulty.valueOf(splittedDetails[2].toUpperCase());
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

}
