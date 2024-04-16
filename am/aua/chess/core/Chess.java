package am.aua.chess.core;
import am.aua.chess.utils.ArrayTools;
import am.aua.chess.utils.IllegalArrangementException;
import am.aua.chess.utils.InvalidNumberOfKingsException;

/**
 * The Chess class represents a chess game.
 * It contains methods to initialize the chess board, perform moves, and check game status.
 * The chess board is represented by a 2D array of characters.
 * Each chess piece is represented by a specific character.
 * The class also keeps track of the move count and the turn of the player.
 */
public class Chess implements Cloneable{
    /**
     * The PieceColor enum represents the color of a chess piece.
     * The color can be either WHITE or BLACK.
     */
    public static enum PieceColor {
        /**
         * The WHITE color.
         */
        WHITE,
        /**
         * The BLACK color.
         */
        BLACK
    }

    private Piece[][] board;
    private int moveCount;
    
    /** The number of rows of the chess board. */
    public static final int BOARD_RANKS = 8;
    /** The number of columns of the chess board. */
    public static final int BOARD_FILES = 8;

    /**
     * Constructs a new instance of the Chess class.
     * Initializes the move count to 0 and calls the initializeBoard method.
     * @throws IllegalArrangementException If the arrangement is invalid.
     */
    public Chess() throws IllegalArrangementException {
        moveCount = 0;
        this.fillBoardFromString("RNBQKBNRPPPPPPPP                                pppppppprnbqkbnr");
    }

    /**
     * Copy constructor for the Chess class.
     * @param chess The Chess object to copy.
     */
    public Chess(Chess chess){
        this.board = chess.getBoard();
        this.moveCount = chess.getMoveCount();
    }


    /**
     * Constructs a new instance of the Chess class with the given arrangement and turn.
     * @param arrangement The string representing the arrangements of the chess pieces.
     * @param turn The color of the player to move.
     * @throws IllegalArrangementException If the arrangement is invalid.
     */
    public Chess(String arrangement, PieceColor turn) throws IllegalArrangementException{
        this.fillBoardFromString(arrangement);
        this.moveCount = turn==PieceColor.WHITE ? 0 : 1;
    }

    /**
     * Initializes the chess board with the given arrangements.
     * @param arrangements The string representing the arrangements of the chess pieces.
     */
    private void fillBoardFromString(String arrangements) throws IllegalArrangementException {
        this.verifyArrangement(arrangements);

        board = new Piece[BOARD_RANKS][BOARD_FILES];
        for (int i = 0; i < BOARD_RANKS; i++) {
            for (int j = 0; j < BOARD_FILES; j++) {
                char piece = arrangements.charAt(i * BOARD_FILES + j);
                Position p = Position.generateFromRankAndFile(i, j);
                if (p==null) throw new IllegalArrangementException("Invalid position");
                switch (piece) {
                    case 'K':
                        this.setPieceAt(p, new King(PieceColor.WHITE));
                        break;
                    case 'k':
                        this.setPieceAt(p, new King(PieceColor.BLACK));
                        break;
                    case 'Q':
                        this.setPieceAt(p, new Queen(PieceColor.WHITE));
                        break;
                    case 'q':
                        this.setPieceAt(p, new Queen(PieceColor.BLACK));
                        break;
                    case 'R':
                        this.setPieceAt(p, new Rook(PieceColor.WHITE));
                        break;
                    case 'r':
                        this.setPieceAt(p, new Rook(PieceColor.BLACK));
                        break;
                    case 'S':
                        this.setPieceAt(p, new Rook(PieceColor.WHITE, true));
                        break;
                    case 's':
                        this.setPieceAt(p, new Rook(PieceColor.BLACK, true));
                        break;
                    case 'B':
                        this.setPieceAt(p, new Bishop(PieceColor.WHITE));
                        break;
                    case 'b':
                        this.setPieceAt(p, new Bishop(PieceColor.BLACK));
                        break;
                    case 'N':
                        this.setPieceAt(p, new Knight(PieceColor.WHITE));
                        break;
                    case 'n':
                        this.setPieceAt(p, new Knight(PieceColor.BLACK));
                        break;
                    case 'P':
                        Pawn pawnW = new Pawn(PieceColor.WHITE);
                        this.setPieceAt(p, pawnW);
                        break;
                    case 'p':
                        Pawn pawnB = new Pawn(PieceColor.BLACK);
                        this.setPieceAt(p, pawnB);
                        break;
                    case 'L':
                        this.setPieceAt(p, new King(PieceColor.WHITE, true));
                        break;
                    case 'l':
                        this.setPieceAt(p, new King(PieceColor.BLACK, true));
                        break;
                    default:
                        board[i][j] = null;
                        break;
                }
            }
        }
    }

    /**
     * Checks if the arrangement string is valid.
     * @param s The arrangement string to check.
     * @throws IllegalArrangementException if the arrangement string is invalid.
     */
    private void verifyArrangement(String s) throws IllegalArrangementException {
        if (s.length() != 64) {
            throw new IllegalArrangementException("The arrangement string should contain 64 characters");
        }
        if (s.matches("[^KkQqRrBbNnPpLlSs]")) {
            throw new IllegalArrangementException("The arrangement string should contain only valid characters which are [KkQqRrBbNnPpLl]");
        }
        int lCount = getCharCount(s, 'l');
        int kCount = getCharCount(s, 'k');
        int capLCount = getCharCount(s, 'L');
        int capKCount = getCharCount(s, 'K');
        if (lCount + kCount + capLCount + capKCount != 2)
            throw new InvalidNumberOfKingsException();
    }

    /**
     * Returns the number of occurrences of the character c in the string s.
     * @param s The string to check.
     * @param c The character to count.
     * @return The number of occurrences of the character c in the string s.
     */
    private int getCharCount(String s, char c){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
    /**
     * Gets the current state of the chess board.
     * @return The 2D array representing the chess board.
     */
    public Piece[][] getBoard() {
        return ArrayTools.deepCopy(board);
    }

    /**
     * Gets the turn number of the current move.
     * @return The turn number.
     */
    public PieceColor getTurn() {
        return (moveCount%2==0)?PieceColor.WHITE:PieceColor.BLACK;
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return false;
    }

    /**
     * Gets the number of moves performed in the game.
     * @return The number of moves performed.
     */
    public int getMoveCount(){
        return moveCount;
    }

    /**
     * Checks if the specified position is empty.
     * @param p The position to check.
     * @return true if the position is empty, false otherwise.
     */
    public boolean isEmpty(Position p) {
        return this.getPieceAt(p) == null;
    }

    /**
     * Gets the piece at the specified position.
     * @param p The position to get the piece from.
     * @return The character representing the piece at the position.
     */
    public Piece getPieceAt(Position p) {
        return board[p.getRank()][p.getFile()];
    }

    /**
     * Sets the specified piece at the given position on the chess board.
     *
     * @param p     the position where the piece should be set
     * @param piece the character representation of the piece to be set
     */
    private void setPieceAt(Position p, Piece piece) {
        board[p.getRank()][p.getFile()] = piece;
    }

    /**
     * Gets the positions reachable from the specified origin position.
     * @param origin The origin position.
     * @return An array of positions reachable from the origin position.
     */
    public Position[] reachableFrom(Position origin) {
        Piece piece = this.getPieceAt(origin);
        if (piece != null){
            return piece.allDestinations(this, origin);
        }

        return null;
    }

    /**
     * Performs the specified move on the chess board.
     * @param move The move to perform.
     * @return true if the move was performed successfully, false otherwise.
     */
    public boolean performMove(Move move){
        Position destination = move.getDestination();
        Position origin = move.getOrigin();
        if (this.getPieceAt(origin)==null){
            System.out.println("There is no piece at the origin!!!");
            return false;
        }
        if (this.getTurn()!=this.getPieceAt(origin).getPieceColor()){
            System.out.println("It is not your turn!!!");
            return false;
        }

        // A backup is created first. If the king is exposed to a threat after a potential move,
        // the backup is restored. Hence, the following copy needs to be a deep copy.
        Piece[][] boardCopy = this.getBoard();

        if (ArrayTools.contains(this.reachableFrom(origin), destination)){
            // TODO: The case for castling is not handled yet.
            // TODO: The case for en passant is not handled yet.
            this.setPieceAt(destination, this.getPieceAt(origin));
            this.setPieceAt(origin, null);

            if (this.getPieceAt(destination) instanceof Rook){
                ((Rook) this.getPieceAt(destination)).move();
            }
            else if (this.getPieceAt(destination) instanceof King){
                ((King) this.getPieceAt(destination)).move();
            }

            if (isKingUnderAttack(this.getTurn())) {
                this.board = boardCopy;
                return false;
            }
            this.moveCount++;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Determines whether the king of the given color is in check.
     * @param kingColor The color of the king in question.
     * @return True, if the king in question is under attack by the opponent.
     */
    public boolean isKingUnderAttack(PieceColor kingColor) {
        Position kingPosition = null;
        PieceColor opponentColor;
        Position[] p = null;

        //find the king
        for (int i = 0; i < BOARD_RANKS; i++)
            for (int j = 0; j < BOARD_FILES; j++)
                if (board[i][j] != null
                        && board[i][j].getPieceColor() == kingColor
                        && board[i][j] instanceof King)
                    kingPosition = Position.generateFromRankAndFile(i, j);

        //determine the opposite color
        if (kingColor == PieceColor.WHITE)
            opponentColor = PieceColor.BLACK;
        else
            opponentColor = PieceColor.WHITE;

        p = getAllDestinationsByColor(opponentColor);

        for (int i = 0; i < p.length; i++)
            if (p[i].equals(kingPosition))
                return true;

        return false;
    }

    /**
     * A method that accumulates every square that can be reached by every piece of the given
     * color.
     * @param color The color of the pieces to consider
     * @return An array with all reachable squares by all pieces of a color
     */
    public Position[] getAllDestinationsByColor(PieceColor color) {
        Position[] result = new Position[0];

        for (int i = 0; i < BOARD_RANKS; i++)
            for (int j = 0; j < BOARD_FILES; j++)
                if (board[i][j] != null && board[i][j].getPieceColor() == color) {
                    Position[] current = board[i][j].allDestinations(this,
                            Position.generateFromRankAndFile(i, j));

                    duplicates:
                    for (int k = 0; k < current.length; k++) {
                        for (int l = 0; l < result.length; l++)
                            if (current[k].equals(result[l]))
                                continue duplicates;
                        result = Position.appendPositionsToArray(result, current);
                    }
                }

        return result;
    }

    /**
     * Returns a clone of the Chess object.
     */
    public Chess clone() {
        try{
            Chess c = (Chess) super.clone();
            c.board = ArrayTools.deepCopy(this.board);
            c.moveCount = this.getMoveCount();
            return c;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
