package am.aua.chess;

import am.aua.chess.cli.ChessConsole;
import am.aua.chess.core.Chess;
import am.aua.chess.puzzles.Puzzle;
import am.aua.chess.ui.ChessUI;

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
      if (args.length>0){
         if (args[0].equals("-console")){
            try {
               ChessConsole chess = new ChessConsole();
               chess.run();
            } catch (Exception e) {
               System.out.println("An error occurred: " + e.getMessage());
               System.exit(-1);
            }
         }
         else{
            System.out.println("No such option: " + args[0] + "\nUsage: java  am.aua.chess.Main [-console]");
            System.exit(-1);
         }
      }
      else{
         new ChessUI();
      }

   }
}
