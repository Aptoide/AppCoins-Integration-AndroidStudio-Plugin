package visual_elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedBorder extends JPanel {


    public RoundedBorder() {
        super();
        setBorder(new EmptyBorder(20,20,20,20));
    }

    /** {@inheritDoc} */
    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        super.paintComponent(g2);

        Insets insets = getInsets();
        int x = insets.left - 20;
        int y = insets.top - 20;
        int w = getWidth() - insets.left - insets.right;
        int h = getHeight() - insets.top - insets.bottom;
        g2.setColor(getBackground());
        g2.fillRoundRect(x, y, w + 40, h + 40, 20, 20);
    }
}