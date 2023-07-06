package visual_elements;

import utils.Actions;
import utils.MetricsClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsRegisterButton extends JButton implements ActionListener {
    MetricsClient connection;
    Actions action;

    public StatisticsRegisterButton(String text, Actions actionString){
        super(text);
        addActionListener(this);
        connection = new MetricsClient();
        action = actionString;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        connection.registerAction(action);
    }
}
