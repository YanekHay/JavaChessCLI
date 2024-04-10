package am.aua.chess;

import am.aua.chess.cli.ChessConsole;
import am.aua.chess.core.Chess;
import am.aua.chess.puzzles.Puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The Main class is the entry point of the chess game.
 */
public class Main {
   public Main() {
   }

   /**
    * The main method creates a new ChessConsole object and starts the game.
    * @param args
    */
   public static void main(String[] args) {
      try {
//      ChessConsole chess = new ChessConsole("             pk                  b          PPPPK               ", Chess.PieceColor.WHITE);
         BufferedReader inputStream = new BufferedReader(new FileReader("am/aua/chess/puzzles/puzzle1.txt"));
         Puzzle puzzle = new Puzzle(inputStream.readLine(), inputStream.readLine());
         ChessConsole chess = new ChessConsole(puzzle.getArrangement(), puzzle.getTurn());
         chess.play();
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("An error occurred: " + e.getMessage());
         System.exit(-1);
      }
   }
}
