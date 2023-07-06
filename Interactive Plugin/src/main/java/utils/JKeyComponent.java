package utils;

import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class JKeyComponent extends JComponent{
    private JTextField key = new JTextField();
    private Color backgroundColor;
    public JKeyComponent(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public JPanel getPanel(){
    JPanel centerPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx=0;
    c.gridy=0;
    c.weightx=0.2;
    centerPanel.add(getLabel("Insert public key: ", backgroundColor),c);
    c.gridx=1;
    c.weightx=0.8;
    centerPanel.add(key,c);
    return centerPanel;
    }

    private JComponent getLabel(String text, Color backgroundColor){
        JTextField label = new JTextField(text);
        label.setFont(new Font("SansSerif",Font.PLAIN,20));
        label.setForeground(Color.LIGHT_GRAY);
        label.setBackground(backgroundColor);
        label.setBorder(JBUI.Borders.empty(0,5,2,0));
        label.setHorizontalAlignment(JTextField.RIGHT);
        label.setEditable(false);
        return label;
    }

    public JTextField getKey(){
        return key;
    }
}
