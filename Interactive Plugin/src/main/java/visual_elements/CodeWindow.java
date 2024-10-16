package visual_elements;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import dialogs.CardLayoutDialog;
import snipets.Snippets;
import utils.Actions;
import utils.DialogColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static dialogs.CardLayoutDialog.project;

public class CodeWindow {
    JPanel codePanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JTextPane tPane;
    Map<String, JButton> languageButtonsMap = new HashMap<String, JButton>();
    Map<String, ArrayList<Snippet>> languageSnippetsMap = new HashMap<String, ArrayList<Snippet>>();

    JButton implementAutomatically = new JButton("Implement automatically");

    JButton aiImplement = new JButton("Implement via Aptoide CoPilot");

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


    public CodeWindow(String language, String snippet, Color color) {
        createCodePanel(language, snippet, color);
    }

    public CodeWindow(String language, String snippet, Color color, Actions action) {
        createCodePanel(language, snippet, color);
        addImplementAutomaticallyButton(action);
        addAIImplementButton();

        // Add the button panel to the code panel
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        codePanel.add(buttonPanel, c);
    }

    private Project projectToSet;
    private Map<Integer, VirtualFile> filesToSet;

    private String newCodeH;

    public void setCodeFiles(Project project, Map<Integer, VirtualFile> files) {
        projectToSet=project;
        filesToSet=files;
        newCodeH="";
    }

    private void writeOnFile(Project project, Map<Integer, VirtualFile> files, String test) {
            // Write the code to the file
            Document buildGradleDocument = FileDocumentManager.getInstance().getDocument(files.get(2));

        String oldBuildGradleContent = buildGradleDocument.getText();
            String newBuildGradleContent = oldBuildGradleContent;
            if (!oldBuildGradleContent.contains("google()")||!oldBuildGradleContent.contains("mavenCentral()")){
                newBuildGradleContent = newBuildGradleContent.concat(
                        test
                );
            }


            // Clear the content of the document
            Runnable clearContent = () -> {
                buildGradleDocument.setReadOnly(false);
                buildGradleDocument.setText("");
            };
            WriteCommandAction.runWriteCommandAction(project, clearContent);

            // Writing build.gradle file changes
            String finalBuildGradleContent = newBuildGradleContent;
            Runnable r2 = () -> {
                buildGradleDocument.setReadOnly(false);
                buildGradleDocument.setText(finalBuildGradleContent);
            };
            WriteCommandAction.runWriteCommandAction(project, r2);

    }


    private void writeOnFileManifest(Project project, Map<Integer, VirtualFile> files, String test) {
        // Retrieve the currently open file
        VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];

        // Get the document associated with the file
        Document currentDocument = FileDocumentManager.getInstance().getDocument(currentFile);

        // Clear the content of the document
        Runnable clearContent = () -> {
            currentDocument.setReadOnly(false);
            currentDocument.setText("");
        };
        WriteCommandAction.runWriteCommandAction(project, clearContent);

        // Write the content of the test variable to the document
        Runnable writeContent = () -> {
            currentDocument.setReadOnly(false);
            currentDocument.setText(test);
        };
        WriteCommandAction.runWriteCommandAction(project, writeContent);
    }

    private void justWrite(Project project, Map<Integer, VirtualFile> files, String test) {
        // Retrieve the currently open file
        VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];

        // Get the document associated with the file
        Document currentDocument = FileDocumentManager.getInstance().getDocument(currentFile);

        if (currentDocument != null) {
            // Clear the content of the document
            Runnable clearContent = () -> {
                currentDocument.setReadOnly(false);
                currentDocument.setText("");
            };
            WriteCommandAction.runWriteCommandAction(project, clearContent);

            // Write the content of the test variable to the document
            Runnable writeContent = () -> {
                currentDocument.setReadOnly(false);
                currentDocument.setText(test);
            };
            WriteCommandAction.runWriteCommandAction(project, writeContent);
        } else {
            Messages.showMessageDialog("No document found for the current file.", "Error", Messages.getErrorIcon());
        }
    }

    private void addAIImplementButton(){
        aiImplement.setUI(new ImplementAutomaticallyButton());
        aiImplement.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DialogColors.lightGray, 1, true),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        buttonPanel.add(aiImplement);
        aiImplement.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                //Messages.showMessageDialog("Contains?: " + newCodeH, "File Info", Messages.getInformationIcon());


                if(newCodeH.contains("implementation")){
                    writeOnFile(projectToSet, filesToSet, newCodeH);
                }else if(newCodeH.contains("?xml version") && !newCodeH.contains("uses-permission")){
                    writeOnFileManifest(projectToSet, filesToSet, newCodeH);
                }else if(newCodeH.contains("?xml version") && newCodeH.contains("uses-permission")){
                    //Messages.showMessageDialog("Aqui: " + newCodeH, "PASSOU AQUI", Messages.getInformationIcon());
                    justWrite(projectToSet, filesToSet, newCodeH);
                }

                //write filter for other parts like consume pruchase listener makepurchase startconnection

            }
        });
    }

    private void createCodePanel(String language, String snippet, Color color) {
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextPane code = createCodeArea();
        //code.setText(snippet);
        addLanguageButton(language);

        addSnippetContent(language,snippet,color);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 6;
        codePanel.add(code, c);
        showLanguageSnippets(languageSnippetsMap.get(language));

        //codePanel.setBackground(DialogColors.gray);
        codePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    private JTextPane createCodeArea(){
        EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));
        tPane = new JTextPane();
        tPane.setBorder(eb);
        tPane.setMargin(new Insets(5, 5, 5, 5));
        tPane.setBackground(DialogColors.lightGray);
        //JBScrollPane scrollWindow = new JBScrollPane(tPane);
        //scrollWindow.setBorder(new EmptyBorder(0,0,0,0));
        //scrollWindow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollWindow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return tPane;
    }

    private void addLanguageButton(String language){
        JButton languageButton = new JButton(language);
        if (languageButtonsMap.size() == 0){
            //languageButton.setBackground(DialogColors.white);
            languageButton.setBackground(getPanel().getBackground());
        } else {
            languageButton.setBackground(getPanel().getBackground());
        }
        languageButton.setUI(new LanguageButton());
        languageButton.setMargin(new Insets(20, 20, 20, 20));
        c.gridx = languageButtonsMap.size();
        c.gridy = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        codePanel.add(languageButton, c);
        languageButtonsMap.put(language, languageButton);
        languageSnippetsMap.put(language, new ArrayList<Snippet>());

        languageButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                unselectAllButtons();
                languageButton.setBackground(DialogColors.lightGray);
                implementAutomatically.revalidate();
                implementAutomatically.repaint();
                showLanguageSnippets(languageSnippetsMap.get(language));
            }
        });
    }

    private void unselectAllButtons(){
        for (Map.Entry<String, JButton> entry : languageButtonsMap.entrySet()) {
            JButton btn = entry.getValue();
            btn.setBackground(DialogColors.gray);
        }
    }

    private void showLanguageSnippets(ArrayList<Snippet> snippets){
        clearTextPane();
        for (Snippet snippet : snippets) {
            String snp = snippet.getSnippet();
            Color color = snippet.getColor();
            appendToPane(snp, color);
        }
    }

    private void clearTextPane(){
        tPane.setText("");
    }


    public void addAICopilotCode(String newCode){
        tPane.setText(""+newCode);
        newCodeH=newCode;
    }

    public void addAICopilotAction(String action){

    }


    private void appendToPane(String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Courier");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tPane.getDocument().getLength();
        tPane.setCaretPosition(len);
        tPane.setCharacterAttributes(aset, false);
        tPane.replaceSelection(msg);
    }

    public void addSnippetContent(String language, String code, Color c){
        if (languageButtonsMap.containsKey(language) == false){
            addLanguageButton(language);
        }
        Snippet snp = new Snippet(code, c);
        ArrayList<Snippet> snippets = languageSnippetsMap.get(language);
        snippets.add(snp);
        languageSnippetsMap.put(language, snippets);
    }

    private void addImplementAutomaticallyButton(Actions action) {
        // Create and configure the Implement Automatically button
        implementAutomatically = new StatisticsRegisterButton("Implement Static", action);
        implementAutomatically.setUI(new ImplementAutomaticallyButton());
        implementAutomatically.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DialogColors.lightGray, 1, true),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        buttonPanel.add(implementAutomatically);

        implementAutomatically.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //implementAutomatically.setOpaque(false);
                implementAutomatically.setForeground(DialogColors.green);
                //implementAutomatically.setBorder(BorderFactory.createEmptyBorder());
                implementAutomatically.setHorizontalAlignment(SwingConstants.RIGHT);
                implementAutomatically.setText("Implemented!");
                implementAutomatically.revalidate();
                implementAutomatically.repaint();
            }
        });
    }

    public void addButtonAction(Action a){
        implementAutomatically.setAction(a);
    }

    public void addButtonActionListener(ActionListener a){
        implementAutomatically.removeActionListener(implementAutomatically.getActionListeners()[0]);
        implementAutomatically.addActionListener(a);
    }

    private JPanel getEmbeddedPanelInGridBagLayout(){
        JPanel parentPanel = new JPanel(new GridBagLayout());
        //parentPanel.setBackground(DialogColors.lightBlue);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        parentPanel.add(codePanel, c);
        parentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return parentPanel;
    }

    public JPanel getPanel(){
        return getEmbeddedPanelInGridBagLayout();
    }

    public void setImplementAutomaticallyButtonText(String text){
        implementAutomatically.setText(text);
        implementAutomatically.revalidate();
        implementAutomatically.repaint();
    }
}
