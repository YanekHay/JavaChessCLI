package am.aua.chess.puzzles;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PuzzleDatabase {
    public static final String databasePath = "am/aua/chess/puzzles/database.txt";
    private ArrayList<Puzzle> puzzles;

    public PuzzleDatabase(){
        this.load();
    }
    public void load(){
        try {
            Scanner dbReader = new Scanner(new File(PuzzleDatabase.databasePath));
            int puzzleCount = dbReader.nextInt();
            dbReader.nextLine();
            this.puzzles = new ArrayList<>(puzzleCount);
            for (int i = 0; i < puzzleCount; i++){
                String arrangements =  dbReader.nextLine();
                String description = dbReader.nextLine();
                this.puzzles.add(new Puzzle(arrangements, description));
            }
            this.puzzles.sort(Puzzle::compareTo);
        }
        catch (InputMismatchException e){
            System.out.println("The content of the database file should start with a line of single integer.");
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.exit(-1);
        }
    }

    public int getSize(){
        return this.puzzles.size();
    }

    public void save(){
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("am/aua/chess/puzzles/database.txt", false));
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

    public Puzzle getPuzzle(int index){
        if (index>=0 && index<this.getSize()){
            return this.puzzles.get(index);
        }
        else{
            throw new IndexOutOfBoundsException("No puzzle available at index " + index);
        }
    }
    public void addPuzzlesFromFile(String filePath){
        try {
            Scanner inputStream = new Scanner(new File(filePath));
            while (inputStream.hasNextLine()){
                Puzzle puzzle = new Puzzle(inputStream.nextLine(), inputStream.nextLine());
                if (!this.puzzles.contains(puzzle))
                    this.puzzles.add(puzzle);
            }
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        PuzzleDatabase db = new PuzzleDatabase();
        db.addPuzzlesFromFile("am/aua/chess/puzzles/puzzle1.txt");
        db.save();
        System.out.println(db.getPuzzle(3));
    }


}
