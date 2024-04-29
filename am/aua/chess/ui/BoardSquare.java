package am.aua.chess.ui;
import am.aua.chess.utils.Maps;
import javax.swing.*;
import java.awt.*;
public class BoardSquare extends JButton {
    private final int x;
    private final int y;
    private final boolean isWhite;
    public static final int SIZE = 64;
    public static final int ICON_SIZE = SIZE*3/4;
    private static final int EMPTY_BORDER_SIZE = 1;
    private static final int SELECTED_BORDER_SIZE = 5;

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

    public int[] getCoordinate() {
        return new int[]{x, y};
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setColor() {
        if (isWhite) {
            this.setBackground(Maps.colorMap.get("light"));
        } else {
            this.setBackground(Maps.colorMap.get("dark"));
        }
    }
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

    public void setPiece() {
        this.setIcon(null);
    }

    public void setHighlight(boolean isHighlighted) {
        if (isHighlighted) {
            this.setBackground(Maps.colorMap.get("highlight"));
        } else {
            this.setColor();
        }
    }

    public void setSelected(boolean isSelected) {
        if (isSelected)
            this.setBorderColor(Maps.colorMap.get("selected"));
        else
            this.setBorderColor();
    }

    private void setBorderColor(Color color) {
        this.setBorder(BorderFactory.createLineBorder(color, SELECTED_BORDER_SIZE));
    }
    private void setBorderColor() {
        this.setBorder(BorderFactory.createLineBorder(Maps.colorMap.get("cellBorderColor"), EMPTY_BORDER_SIZE));
    }
    public int getRank() {
        return this.x;
    }
    public int getFile(){
        return this.y;
    }

}
