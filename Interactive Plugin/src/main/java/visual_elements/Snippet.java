package visual_elements;

import java.awt.*;

public class Snippet {
    String snippet;
    Color color;

    public Snippet(String code, Color color) {
        this.snippet = code;
        this.color = color;
    }

    public String getSnippet() {
        return snippet;
    }

    public Color getColor() {
        return color;
    }
}
