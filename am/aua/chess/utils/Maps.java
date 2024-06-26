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
            "dark", new Color(27, 100, 39),
            "highlight", new Color(156, 46, 46),
            "selected", new Color(255, 47, 0),
            "cellBorderColor", new Color(0, 0, 0)

    );
}
