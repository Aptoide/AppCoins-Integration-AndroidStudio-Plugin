package visual_elements;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedScrollablePanelBorder extends JBScrollPane {
    public RoundedScrollablePanelBorder(Component panel, int verticalScrollbarAlways, int horizontalScrollbarNever) {
        super(panel,verticalScrollbarAlways,horizontalScrollbarNever);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    /** {@inheritDoc} */
    @Override
    public boolean isOpaque() {
        return false;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Insets insets = getInsets();
        int x = insets.left - 5;
        int y = insets.top - 5;
        int w = getWidth() - insets.left - insets.right;
        int h = getHeight() - insets.top - insets.bottom;
        g2.setColor(getBackground());
        g2.fillRoundRect(x, y, w + 10, h + 10, 20, 20);

        super.paintComponent(g);
    }

}
