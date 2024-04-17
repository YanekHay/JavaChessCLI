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
   /**
    * The main method creates a new ChessConsole object and starts the game.
    * @param args The command line arguments.
    */
   public static void main(String[] args) {
      try {
//      ChessConsole chess = new ChessConsole("             pk                  b          PPPPK               ", Chess.PieceColor.WHITE);

         ChessConsole chess = new ChessConsole();
         chess.run();
      } catch (Exception e) {
//         e.printStackTrace();
         System.out.println("An error occurred: " + e.getMessage());
         System.exit(-1);
      }
   }
}
