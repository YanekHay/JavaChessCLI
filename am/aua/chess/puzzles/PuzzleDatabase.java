package am.aua.chess.puzzles;

import am.aua.chess.utils.MalformedPuzzleException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The PuzzleDatabase class represents a database of chess puzzles.
 * It provides methods for loading puzzles from a file, saving puzzles to a file,
 * retrieving puzzles by index, and adding puzzles from a file to the database.
 */
public class PuzzleDatabase {
    public static final String DATABASE_PATH = "am/aua/chess/puzzles/database.txt"; // Path to the database file
    private ArrayList<Puzzle> puzzles;

    /**
     * Constructs a new PuzzleDatabase object and loads puzzles from the database file.
     */
    public PuzzleDatabase(){
        this.load();
    }

    /**
     * Loads puzzles from the database file.
     * The database file should start with a line containing a single integer representing the number of puzzles.
     * Each puzzle should be represented by two lines: the first line contains the arrangements and the second line contains the description.
     * Puzzles are stored in the puzzles ArrayList and sorted based on their natural order.
     * If an error occurs during loading, an appropriate error message is printed and the program exits.
     */
    public void load(){
        try {
            Scanner dbReader = new Scanner(new File(PuzzleDatabase.DATABASE_PATH));
            int puzzleCount = dbReader.nextInt();
            dbReader.nextLine();
            this.puzzles = new ArrayList<>(puzzleCount);
            for (int i = 0; i < puzzleCount; i++){
                String arrangements =  dbReader.nextLine();
                String description = dbReader.nextLine();
                this.puzzles.add(new Puzzle(arrangements, description));
            }
            this.puzzles.sort(Puzzle::compareTo);
            dbReader.close();
        }
        catch (MalformedPuzzleException e){
            System.out.println("The content of the database file should start with a line of single integer.");
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Returns the number of puzzles in the database.
     * @return the number of puzzles in the database
     */
    public int getSize(){
        return this.puzzles.size();
    }

    /**
     * Saves the puzzles in the database to the database file.
     * If an error occurs during saving, an appropriate error message is printed.
     */
    public void save(){
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(DATABASE_PATH, false));
            outputStream.println(this.getSize());
            for (Puzzle puzzle: this.puzzles){
                outputStream.println(puzzle);
            }
            outputStream.close( );
        }
        catch (Exception e){
            System.out.println("Something went wrong, puzzles not saved: " + e.getMessage());
        }
    }

    /**
     * Retrieves the puzzle at the specified index from the database.
     * @param index the index of the puzzle to retrieve
     * @return the puzzle at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getSize())
     */
    public Puzzle getPuzzle(int index){
        if (index>=0 && index<this.getSize()){
            return this.puzzles.get(index);
        }
        else{
            throw new IndexOutOfBoundsException("No puzzle available at index " + index);
        }
    }

    /**
     * Adds puzzles from a file to the database.
     * Each puzzle in the file should be represented by two lines: the first line contains the arrangements and the second line contains the description.
     * If a puzzle with the same arrangements and description already exists in the database, it will not be added.
     * @param filePath the path to the file containing the puzzles to add
     */
    public void addPuzzlesFromFile(String filePath){
        try {
            Scanner inputStream = new Scanner(new File(filePath));
            while (inputStream.hasNextLine()){
                Puzzle puzzle = new Puzzle(inputStream.nextLine(), inputStream.nextLine());
                if (!this.puzzles.contains(puzzle))
                    this.puzzles.add(puzzle);
            }
            inputStream.close();
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * The main method of the PuzzleDatabase class.
     * It creates a new PuzzleDatabase object, adds puzzles from a file, saves the puzzles to the database file, and prints a puzzle from the database.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PuzzleDatabase db = new PuzzleDatabase();
        db.addPuzzlesFromFile("am/aua/chess/puzzles/puzzle1.txt");
        db.save();
        System.out.println(db.getPuzzle(3));
    }
}
