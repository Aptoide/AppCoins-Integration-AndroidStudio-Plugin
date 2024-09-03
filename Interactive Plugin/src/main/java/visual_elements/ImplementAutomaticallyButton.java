package visual_elements;

import utils.DialogColors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ImplementAutomaticallyButton extends BasicButtonUI {
    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setOpaque(true);
        b.setForeground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(hints);
        g2d.fillRoundRect(10,0,c.getWidth()+30,c.getHeight(),15,15);
        //g2d.fillRoundedRect(new RoundRectangle2D.Double(20, 0, c.getWidth()+50, c.getHeight(), 15, 15));
        g2d.setColor(Color.GREEN);
        super.paint(g, c);
        g2d.dispose();
    }


}
