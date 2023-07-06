package actions;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

public class CopyToClipboard extends AbstractAction {
    private String text;
    public CopyToClipboard(String text){
        super("Copy to clipboard");
        this.text=text;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(text),
                        null
                );
    }
}
