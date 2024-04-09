package am.aua.chess;

import am.aua.chess.cli.ChessConsole;
import am.aua.chess.core.Chess;

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
         ChessConsole chess = new ChessConsole();
         chess.play();
      } catch (Exception e) {
         System.out.println("An error occurred: " + e.getMessage());
         System.exit(-1);
      }
   }
}
