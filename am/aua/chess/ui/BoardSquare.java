package am.aua.chess.ui;

import am.aua.chess.utils.Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoardSquare extends JButton {
    private int x;
    private int y;
    private final boolean isWhite;
    public static final int SIZE = 64;

    public BoardSquare(int x, int y, boolean isWhite) {
        super();
        this.x = x;
        this.y = y;
        this.setSize(SIZE, SIZE);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setLocation(x*SIZE, y*SIZE);
        this.isWhite = isWhite;
        this.setColor();
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
        }
        if (!Maps.pieceNameMap.containsKey(piece.toUpperCase())){
            this.setIcon(null);
        }
        else {
            String pieceFileName = Maps.pieceNameMap.get(piece.toUpperCase()) + (Character.isUpperCase(piece.charAt(0))? "W" : "B");
            this.setIcon(new ImageIcon("am/aua/chess/ui/gfx/" + pieceFileName +".png"));
        }
    }

    public void setPiece() {
        this.setIcon(null);
    }

    public void setHighlight() {
        this.setBackground(Maps.colorMap.get("highlight"));
    }

    public void removeHighlight() {
        this.setColor();
    }

    public int getRank() {
        return this.x;
    }
    public int getFile(){
        return this.y;
    }

}
