package am.aua.chess.utils;

import java.awt.*;
import java.util.Map;

public class Maps {
    public static final Map<String, String> pieceNameMap = Map.of(
            "K", "King",
            "L", "King",
            "P", "Pawn",
            "Q", "Queen",
            "R", "Rook",
            "S", "Rook",
            "N", "Knight",
            "B", "Bishop"
    );

    public static final Map<String, Color> colorMap = Map.of(
            "light", new Color(234, 228, 223),
            "dark", new Color(33, 30, 30),
            "highlight", new Color(156, 46, 46)

    );
}
