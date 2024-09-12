package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class Action1 extends AnAction {

    private JTextArea textArea;


    public Action1() {
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if(e.getPresentation().getText().toString().equals("Fix This")){
            Messages.showMessageDialog("Event Text: Fix This triggered", "File Info", Messages.getInformationIcon());
            String actionText = e.getPresentation().getText();
            textArea.append("Event Text: " + actionText + " triggered\n");

        } else if (e.getPresentation().getText().toString().equals("Explain This")) {
            Messages.showMessageDialog("Event Text: Explain This triggered", "File Info", Messages.getInformationIcon());
        } else if (e.getPresentation().getText().toString().equals("Implement Billing Init")) {
            Messages.showMessageDialog("Event Text: Implement Billing Init triggered", "File Info", Messages.getInformationIcon());
        } else if (e.getPresentation().getText().toString().equals("Implement Query SKUs")) {
            Messages.showMessageDialog("Event Text: Implement Query SKUs triggered", "File Info", Messages.getInformationIcon());
        } else if (e.getPresentation().getText().toString().equals("Implement Purchases")) {
            Messages.showMessageDialog("Event Text: Implement Purchases triggered", "File Info", Messages.getInformationIcon());
        } else if (e.getPresentation().getText().toString().equals("Implement Consume")) {
            Messages.showMessageDialog("Event Text: Implement Consume triggered", "File Info", Messages.getInformationIcon());
        } else if (e.getPresentation().getText().toString().equals("Implement Query Purchases")){
            Messages.showMessageDialog("Event Text: Implement Query Purchases triggered", "File Info", Messages.getInformationIcon());
        }




    }
}
