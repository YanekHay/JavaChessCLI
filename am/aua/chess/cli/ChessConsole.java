package am.aua.chess.cli;

import am.aua.chess.core.*;
import am.aua.chess.utils.ArrayTools;
import am.aua.chess.utils.IllegalArrangementException;

import java.util.Scanner;

/**
 * The ChessConsole class represents a console-based interface for playing chess.
 */
public class ChessConsole {
    private Chess game;
    /**
     * Gets the Chess object representing the game.
     * @return The Chess object representing the game.
     */
    public Chess getGame() {
        return new Chess(game);
    }
    /**
     * Constructs a new ChessConsole object.
     */
    public ChessConsole() throws IllegalArrangementException {
        game = new Chess();
    }

    /**
     * Constructs a new ChessConsole object with the given positioning and turn.
     * @param positioning The positioning string to start the game with.
     * @param turn The turn to start the game with.
     * @Note: The positioning string is a string of 64 characters representing the board from the top-left corner to the bottom-right corner.
     */
    public ChessConsole(String positioning, Chess.PieceColor turn) throws IllegalArrangementException {
        game = new Chess(positioning, turn);
    }
    /**
     * Prints the current state of the chess board.
     */
    public void print() {
        print((Position) null);
    }

    /**
     * Prints the current state of the chess board with a highlighted position.
     * @param pos The position to highlight.
     */
    public void print(Position pos){
        if (pos == null){
            print(new Position[]{});
        } else {
            print(new Position[]{pos});
        }
    }

    /**
     * Prints the current state of the chess board with multiple highlighted positions.
     * @param availablePositions The positions to highlight.
     */
    public void print(Position[] availablePositions){
        print(null, availablePositions);
    }

    /**
     * Prints the current state of the chess board with multiple highlighted positions.
     * @param availablePositions The positions to highlight.
     */
    public void print(Position selectedPosition, Position[] availablePositions){
        // printing the board upside down (for white pieces to occur at the bottom of the board)
        System.out.printf("\n\nTurn: %s    MoveCounts: %d\n\n", (this.game.getTurn()), this.game.getMoveCount() );
        String[][] board = selectedPosition==null?this.prettifyBoard(this.game.getBoard(), availablePositions):this.prettifyBoard(this.game.getBoard(), selectedPosition, availablePositions);
        for (int i = board.length-1; i >= 0; i--) {
            System.out.print((i+1) + "| ");
            ArrayTools.printArray1D(board[i], "");
            System.out.println();
        }
        System.out.println("_|_______________________________");
        System.out.println(" | \u2009\u200AA\u2009 \u2009\u200AB\u2009 \u200AC\u2009 \u200AD\u2009 \u200AE\u2009 \u200AF\u2009 \u200AG\u2009 \u200AH");
    }

    /**
     * Gets the symbol representing the piece.
     * @param piece The piece to get the symbol for.
     * @return The symbol representing the piece.
     */
    private String getPieceSymbol(Piece piece) {
        if (piece == null) return "\u2009\u200A\u200A\u200A\u2009"; // Wide space
        return switch (piece.toString()) {
            case "P" -> "\u2659";
            case "p" -> "\u265F";
            case "R","S" -> "\u2656";
            case "r","s" -> "\u265C";
            case "N" -> "\u2658";
            case "n" -> "\u265E";
            case "B" -> "\u2657";
            case "b" -> "\u265D";
            case "Q" -> "\u2655";
            case "q" -> "\u265B";
            case "K","L" -> "\u2654";
            case "k","l" -> "\u265A";
            default -> "\u2009\u200A\u200A\u200A\u2009"; // Wide space
        };
    }
    
    /**
     * Prettifies the board for printing.
     * @param board The 2D array representing the board.
     * @param highlightCell The cell to highlight.
     * @return The prettified board.
     * @Note: The prettified board is a 2D array of strings, where each string is a cell in the board.
     * The empty space is represented by <code>"\u2009\u2009\u200A\u200A\u200A"</code> \ u2009 is a thin space, \ u200A is a hair space.
     */
    private String[][] prettifyBoard(Piece[][] board, Position highlightCell){
        return prettifyBoard(board, new Position[]{highlightCell});
    }

    /**
     * Prettifies the board for printing with multiple highlighted cells.
     * @param board The 2D array representing the board.
     * @param highlightCells The cells to highlight.
     * @return The prettified board.
     */
    private String[][] prettifyBoard(Piece[][] board, Position[] highlightCells){
        return prettifyBoard(board, null, highlightCells);
    }

    /**
     * Prettifies the board for printing with multiple highlighted cells.
     * @param board The 2D array representing the board.
     * @param selectedCell The cell to highlight with yellow.
     * @param highlightCells The cells to highlight.
     * @return The prettified board.
     */
    private String[][] prettifyBoard(Piece[][] board, Position selectedCell, Position[] highlightCells){
        String[][] prettyBoard = new String[board.length][board[0].length];
        String cellColor;
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                cellColor = (i+j)%2==1? "\u001b[47;1m" : "\u001b[40;1m";
                if (ArrayTools.contains(highlightCells, new Position(i,j))){
                    cellColor = "\u001b[42;1m\u001b[52;1m";
                }
                prettyBoard[i][j] = cellColor + "\u2009" + getPieceSymbol(board[i][j]) + "\u2009" + "\u001b[0m";
            }
        }
        if (selectedCell != null){
            prettyBoard[selectedCell.getRank()][selectedCell.getFile()] = "\u001b[43;1m\u2009" + getPieceSymbol(board[selectedCell.getRank()][selectedCell.getFile()]) + "\u2009\u001b[0m";
        }
        return prettyBoard;
    }

    /**
     * Starts the chess game and handles user input.
     */
    public void play(){
        Scanner sc = new Scanner(System.in);
        String inputLine;

        print();

        while (!game.isGameOver()){
            System.out.println(game.getTurn() + "'s move");

            inputLine = sc.nextLine();
            String[] input = inputLine.split(" ");
            Position p1 = null, p2 = null;

            if (input.length == 1){
                if (input[0].equals("resign")) {
                    System.out.println(game.getTurn() + " has resigned.");
                    return;
                }
                p1 = Position.generateFromString(input[0]);

                if (input[0].equals("debug")) {
                    debug();
                    print();
                    continue;
                }

                assert p1 != null;
                if (game.getPieceAt(p1).getPieceColor() != game.getTurn()) {
                    System.out.println("That piece belongs to the opponent.");
                    continue;
                }
                print(p1, game.reachableFrom(Position.generateFromString(input[0])));
            }
            else if (input.length == 2){
                p2 = Position.generateFromString(input[1]);

                assert p2 != null;
                if (game.getPieceAt(p2).getPieceColor() != game.getTurn()) {
                    System.out.println("That piece belongs to the opponent.");
                    continue;
                }
                Move m = new Move(p1, p2);

                boolean success = game.performMove(m);
                if (!success){
                    System.out.println("Invalid move. Please try again.");
                }
                print();
            }
        }
    }

    public void debug() {
        System.out.println("This method is for testing purposes.");
        //System.out.println(game.getAllDestinationsByColor(Chess.PieceColor.WHITE).length);
    }

}
