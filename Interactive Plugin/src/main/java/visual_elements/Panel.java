package visual_elements;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Panel {

    static JPanel jpanel;

    public Panel(Color color){
        jpanel = new RoundedBorder();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
        jpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpanel.setBackground(color);
    }

    public void setBorder(Border border){ jpanel.setBorder(border); }
    public void setLayout(LayoutManager layout){
        jpanel.setLayout(layout);
    }
    public void add(JComponent comp){
        jpanel.add(comp);
    }
    public void add(JComponent comp, String side){
        jpanel.add(comp, side);
    }

    public void add(JComponent comp, Float side){
        jpanel.add(comp, side);
    }
    public void add(JComponent comp, GridBagConstraints side){ jpanel.add(comp, side); }

    public void addRigidArea(Dimension dim){
        jpanel.add(Box.createRigidArea(dim));
    }

    public static JPanel getPanel(){
        return jpanel;
    }
    public RoundedScrollablePanelBorder getScrollablePanel(){
        return new RoundedScrollablePanelBorder(jpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
