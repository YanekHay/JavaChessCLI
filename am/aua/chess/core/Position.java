package am.aua.chess.core;

/**
 * The am.aua.chess.core.Position class represents a position on a chessboard.
 * It stores the rank and file of the position.
 */
public class Position {
    private int rank;
    private int file;

    /**
     * Constructor for creating a Position object with specified rank and file.
     *
     * @param rank    the rank of the position
     * @param file the file of the position
     */
    public Position(int rank, int file) {
        this.setRow(rank);
        this.setColumn(file);
    }

    /**
     * Non-argument constructor for creating a Position object with default rank and file (0, 0).
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Copy constructor for creating an Position object by copying another Position object.
     *
     * @param that the Position object to be copied
     */
    public Position(Position that) {
        this(that.getRank(), that.getFile());
    }

    /**
     * Sets the rank of the position.
     *
     * @param rank the rank to be set
     */
    public void setRow(int rank) {
        if ((rank < 0) || (rank > (Chess.BOARD_RANKS-1))) {
            System.out.println("Row must be in range from 0 to " + (Chess.BOARD_RANKS-1));
            return;
        }
        this.rank = rank;
    }

    /**
     * Sets the file of the position.
     *
     * @param file the file to be set
     */
    public void setColumn(int file) {
        if ((file < 0) || (file > Chess.BOARD_FILES-1)) {
            System.out.println("Column must be in range from 0 to " + (Chess.BOARD_FILES-1));
            return;
        }
        this.file = file;
    }

    /**
     * Returns the rank of the position.
     *
     * @return the rank of the position
     */
    public int getRank() {
        return rank;
    }

    /**
     * Returns the file of the position.
     *
     * @return the file of the position
     */
    public int getFile() {
        return file;
    }

    /**
     * Returns a string representation of the position.
     *
     * @return a string representation of the position
     */
    public String toString() {
        return "" + (char) ('A' + file) + (rank + 1);
    }

    /**
     * Generates a Position object from a string representation.
     *
     * @param s the string representation of the position (e.g., "A1")
     * @return a Position object generated from the string representation
     */
    public static Position generateFromString(String s) {
        if (s.length() != 2) {
            System.out.println("String must be of length 2");
            return null;
        }
        s = s.toUpperCase();
        int file = s.charAt(0) - 'A';
        if ((file < 0) || (file > 7)) {
            System.out.println("Column must be in range from A to H");
            return null;
        }
        int rank = s.charAt(1) - '1';
        if ((rank < 0) || (rank > 7)) {
            System.out.println("Row must be in range from 1 to " + Chess.BOARD_RANKS);
            return null;
        }

        return new Position(rank, file);
    }

    /**
     * Generates a Position object from rank and file numbers.
     *
     * @param rank the rank number (rank) of the position (1 to Chess.BOARD_SIZE)
     * @param file the file number (file) of the position (1 to Chess.BOARD_SIZE)
     * @return a Position object generated from the rank and file numbers
     */
    public static Position generateFromRankAndFile(int rank, int file) {
        if ((rank < 0) || (rank > Chess.BOARD_RANKS)) {
            System.out.println(rank);
            System.out.println("Rank must be in range from 0 to " + (Chess.BOARD_RANKS-1));
            return null;
        }
        if ((file < 0) || (file > Chess.BOARD_FILES)) {
            System.out.println("File must be in range from 0 to " + (Chess.BOARD_FILES-1));
            return null;
        }
        return new Position(rank, file);
    }

    /**
     * Generates a Position object from a 1D array of integers.
     *
     * @param arr the 1D array of Positions.
     * @param positions the positions to append to the array (vararg).
     * @return a Position object generated from the 1D array of integers
     */
    public static Position[] appendPositionsToArray(Position[] arr, Position... positions) {
        Position[] newArr = new Position[arr.length + positions.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        for (int i = 0; i < positions.length; i++) {
            newArr[arr.length + i] = positions[i];
        }
        return newArr;
    }

    /**
     * Checks if a Position object is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the Position object is equal to the other object, false otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            return (this.rank == p.getRank()) && (this.file == p.getFile());
        }
        return false;
    }

}
