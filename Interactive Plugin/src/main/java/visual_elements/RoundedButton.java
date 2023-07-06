package visual_elements;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends BasicButtonUI {
    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setForeground(Color.decode("#FFFFFF"));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(hints);
        g2d.setColor(c.getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), 5, 5));
        g2d.setColor(c.getForeground());
        super.paint(g, c);
        g2d.dispose();
    }

}