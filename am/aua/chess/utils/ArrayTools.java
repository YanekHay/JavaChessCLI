package am.aua.chess.utils;
import am.aua.chess.core.Piece;
import am.aua.chess.core.Position;
/**
 * The am.aua.chess.utils.ArrayTools class provides utility methods for working with arrays.
 */
public class ArrayTools {
    
    /**
     * Creates a deep copy of a 2D character array.
     *
     * @param array The original 2D character array.
     * @return A deep copy of the original array.
     */
    public static Piece[][] deepCopy(Piece[][] array) {
        Piece[][] copy = new Piece[array.length][];
        for (int i = 0; i < array.length; i++) {
            copy[i] = new Piece[array[i].length];
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    copy[i][j] = null;
                else
                    copy[i][j] = new Piece(array[i][j]);
            }
        }
        return copy;
    }

    /**
     * Checks if an array of am.aua.chess.core.Position objects contains a specific am.aua.chess.core.Position.
     *
     * @param array The array of am.aua.chess.core.Position objects.
     * @param p The am.aua.chess.core.Position to check for.
     * @return true if the array contains the am.aua.chess.core.Position, false otherwise.
     */
    public static boolean contains(Position[] array, Position p) {
        if (array == null) {
            return false;
        }
        for (Position position : array) {
            if (position.equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints a 1D array of am.aua.chess.core.Position objects with a specified separator.
     *
     * @param array The 1D array of am.aua.chess.core.Position objects.
     * @param separator The separator to use between elements.
     */
    public static void printArray1D(Position[] array, String separator) {
        for (Position position : array) {
            System.out.print(position + separator);
        }
    }

    /**
     * Prints a 1D array of am.aua.chess.core.Position objects with a default separator.
     *
     * @param array The 1D array of am.aua.chess.core.Position objects.
     */
    public static void printArray1D(Position[] array) {
        printArray1D(array, " ");
    }

    /**
     * Prints a 1D array of characters with a specified separator.
     *
     * @param array The 1D array of characters.
     * @param separator The separator to use between elements.
     */
    public static void printArray1D(char[] array, String separator) {
        for (char c : array) {
            System.out.print(c + separator);
        }
    }

    /**
     * Prints a 1D array of characters with a specified separator.
     *
     * @param array The 1D array of Strings.
     * @param separator The separator to use between elements.
     */
    public static void printArray1D(String[] array, String separator) {
        for (String s : array) {
            System.out.print(s + separator);
        }
    }
    /**
     * Prints a 1D array of characters with a default separator.
     *
     * @param array The 1D array of Strings.
     */
    public static void printArray1D(String[] array) {
        printArray1D(array, " ");
    }
    /**
     *
     * Prints a 1D array of characters with a default separator.
     *
     * @param array The 1D array of characters.
     */
    public static void printArray1D(char[] array) {
        printArray1D(array, " ");
    }
}
