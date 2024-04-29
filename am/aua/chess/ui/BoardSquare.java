package am.aua.chess.ui;
import am.aua.chess.utils.Maps;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents a single cell on the chess board.
 * It extends JButton and contains information about the cell's position, color, and piece.
 */
public class BoardSquare extends JButton {
    private final int x; // Column
    private final int y; // Row
    private final boolean isWhite; // To Determine the color of cell
    public static final int SIZE = 64; // The size of cell
    public static final int ICON_SIZE = SIZE*3/4; // The size of icon in cell
    private static final int NON_SELECTED_CELL_BORDER_SIZE = 1; // The border size for non-selected cell
    private static final int SELECTED_BORDER_SIZE = 5; // The border size for selected cell

    /**
     * The constructor for the BoardSquare class.
     * It initializes the position, color, and size of the cell, and sets its layout and location.
     * @param x The rank of the cell.
     * @param y The file of the cell.
     * @param isWhite Whether the cell is white or not.
     */
    public BoardSquare(int x, int y, boolean isWhite) {
        super();
        this.x = x;
        this.y = y;
        this.setSize(SIZE, SIZE);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setLayout(new BorderLayout());
        this.setLocation(x*SIZE, y*SIZE);
        this.isWhite = isWhite;
        this.setColor();
        this.setBorderColor();
    }

    /**
     * This method returns the coordinates of the cell.
     * @return An array containing the rank and file of the cell.
     */
    public int[] getCoordinate() {
        return new int[]{x, y};
    }

    /**
     * This method returns whether the cell is white or not.
     * @return True if the cell is white, false otherwise.
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * This method sets the color of the cell based on whether it is white or not.
     */
    public void setColor() {
        if (isWhite) {
            this.setBackground(Maps.colorMap.get("light"));
        } else {
            this.setBackground(Maps.colorMap.get("dark"));
        }
    }

    /**
     * This method sets the piece on the cell.
     * It takes the name of the piece, gets the corresponding image, and sets it as the icon of the cell.
     * @param piece The name of the piece.
     */
    public void setPiece(String piece) {
        if (piece == null) {
            this.setIcon(null);
            return;
        }
        if (!Maps.pieceNameMap.containsKey(piece.toUpperCase())){
            this.setIcon(null);
        }
        else {
            String pieceFileName = Maps.pieceNameMap.get(piece.toUpperCase()) + (Character.isUpperCase(piece.charAt(0))? "W" : "B");
            Image img = new ImageIcon("am/aua/chess/ui/gfx/" + pieceFileName +".png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(img));
        }
    }

    /**
     * This method removes the piece from the cell.
     */
    public void setPiece() {
        this.setIcon(null);
    }

    /**
     * This method highlights the cell.
     * It takes a boolean indicating whether the cell should be highlighted or not, and sets the background color accordingly.
     * @param isHighlighted Whether the cell should be highlighted or not.
     */
    public void setHighlight(boolean isHighlighted) {
        if (isHighlighted) {
            this.setBackground(Maps.colorMap.get("highlight"));
        } else {
            this.setColor();
        }
    }

    /**
     * This method selects the cell.
     * It takes a boolean indicating whether the cell should be selected or not, and sets the border color accordingly.
     * @param isSelected Whether the cell should be selected or not.
     */
    public void setSelected(boolean isSelected) {
        if (isSelected)
            this.setBorderColor(Maps.colorMap.get("selected"));
        else
            this.setBorderColor();
    }

    /**
     * This method sets the border color of the cell.
     * It takes a Color object and sets the border of the cell to that color.
     * @param color The color to set the border to.
     */
    private void setBorderColor(Color color) {
        this.setBorder(BorderFactory.createLineBorder(color, SELECTED_BORDER_SIZE));
    }

    /**
     * This method sets the border color of the cell to the default color.
     */
    private void setBorderColor() {
        this.setBorder(BorderFactory.createLineBorder(Maps.colorMap.get("cellBorderColor"), NON_SELECTED_CELL_BORDER_SIZE));
    }

    /**
     * This method returns the rank of the cell.
     * @return The rank of the cell.
     */
    public int getRank() {
        return this.x;
    }

    /**
     * This method returns the file of the cell.
     * @return The file of the cell.
     */
    public int getFile(){
        return this.y;
    }

}
