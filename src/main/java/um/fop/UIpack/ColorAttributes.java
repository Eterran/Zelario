package UIpack;

import javax.swing.text.*;
import java.awt.*;

public class ColorAttributes {
    private static final StyleContext sc = StyleContext.getDefaultStyleContext();

    public static AttributeSet get(Color color) {
        return sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
    }

    public static AttributeSet RED = get(Color.RED);
    public static AttributeSet GREEN = get(Color.GREEN);
    public static AttributeSet BLUE = get(Color.BLUE);
    public static AttributeSet YELLOW = get(Color.YELLOW);
    public static AttributeSet ORANGE = get(Color.ORANGE);
    public static AttributeSet PINK = get(Color.PINK);
    public static AttributeSet CYAN = get(Color.CYAN);
    public static AttributeSet MAGENTA = get(Color.MAGENTA);
    public static AttributeSet BLACK = get(Color.BLACK);
    public static AttributeSet WHITE = get(Color.WHITE);
    public static AttributeSet GRAY = get(Color.GRAY);
    public static AttributeSet LIGHT_GRAY = get(Color.LIGHT_GRAY);
    public static AttributeSet DARK_GRAY = get(Color.DARK_GRAY);
}