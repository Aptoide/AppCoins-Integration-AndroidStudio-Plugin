package visual_elements;

import utils.DialogColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Card {
    JPanel jpanel;

    public Card(){
        jpanel = new JPanel(new BorderLayout());
        jpanel.setLayout(new BorderLayout(0,20));
        jpanel.setBorder(new EmptyBorder(10, 40, 10, 40));
        jpanel.setBackground(DialogColors.lightBlue);
    }

    public void setBorder(EmptyBorder border){
        jpanel.setBorder(border);
    }

    public void setLayout(LayoutManager layout){
        jpanel.setLayout(layout);
    }

    public void add(JComponent comp){
        jpanel.add(comp);
    }

    public void add(JComponent comp, String side){
        jpanel.add(comp, side);
    }
    public void add(JComponent comp, GridBagConstraints side){
        jpanel.add(comp, side);
    }

    public JPanel getPanel(){
        return jpanel;
    }

    public RoundedScrollablePanelBorder getScrollablePanel(){
        return new RoundedScrollablePanelBorder(jpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
