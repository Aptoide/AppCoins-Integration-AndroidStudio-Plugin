package visual_elements;

import api.ApiService;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import static dialogs.CardLayoutDialog.project;

public class CodeWindow {
    JPanel codePanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JTextPane tPane;
    Map<String, JButton> languageButtonsMap = new HashMap<String, JButton>();
    Map<String, ArrayList<Snippet>> languageSnippetsMap = new HashMap<String, ArrayList<Snippet>>();

    JButton implementAutomatically = new JButton("Static");

    JButton aiImplement = new JButton("Aptoide CoPilot");

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


    public CodeWindow(String language, String snippet, Color color) {
        createCodePanel(language, snippet, color);
    }

    public CodeWindow(String language, String snippet, Color color, Actions action, String step) {
        createCodePanel(language, snippet, color);
        addImplementAutomaticallyButton(action);
        if(!step.equals("0")){
            addAIImplementButton(step, language.toLowerCase());
        }

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
        if (!oldBuildGradleContent.contains("google()") || !oldBuildGradleContent.contains("mavenCentral()")) {
            newBuildGradleContent = test;//newBuildGradleContent.concat(test);
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

    private void writeOnFileManifest2(Project project, Map<Integer, VirtualFile> files, String test) {
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


    private void writeOnFileSkuDetails(Project project, Map<Integer, VirtualFile> files, String test) {
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


    private void writeOnFileQuery(Project project, Map<Integer, VirtualFile> files, String test) {
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



    private void addAIImplementButton(String step, String language){
        aiImplement.setUI(new ImplementAutomaticallyButton());
        aiImplement.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DialogColors.lightGray, 1, true),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        buttonPanel.add(aiImplement);
        aiImplement.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {


                addAICopilotCode("Loading...");


                aiImplement.setForeground(DialogColors.green);
                aiImplement.setText("Implemented!");
                aiImplement.revalidate();
                aiImplement.repaint();


                //Messages.showMessageDialog("Contains?: " + newCodeH, "File Info", Messages.getInformationIcon());

                //Messages.showMessageDialog("Step is: " + step, "Verify Step", Messages.getInformationIcon());

                //Colocar a filtragem por step e especificar o dispositivo

                if(step.equals("0")){



                }else if(step.equals("1")){
                    VirtualFile file = CardLayoutDialog.files.get(2);
                    FileEditorManager.getInstance(CardLayoutDialog.project).openTextEditor(
                            new OpenFileDescriptor(CardLayoutDialog.project,file),
                            true // request focus to editor
                    );

                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.changesToGradle2(snippetContext);

                    addAICopilotCode(test);

                    writeOnFile(projectToSet, filesToSet, test);
                }else if(step.equals("2")){
                    VirtualFile file = CardLayoutDialog.files.get(3);
                    FileEditorManager.getInstance(CardLayoutDialog.project).openTextEditor(
                            new OpenFileDescriptor(CardLayoutDialog.project,file),
                            true // request focus to editor
                    );

                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.changesToAndroidManifest(snippetContext);

                    addAICopilotCode(test);



                    writeOnFileManifest(projectToSet, filesToSet, test);
                }else if(step.equals("3")){
                    VirtualFile file = CardLayoutDialog.files.get(3);
                    FileEditorManager.getInstance(CardLayoutDialog.project).openTextEditor(
                            new OpenFileDescriptor(CardLayoutDialog.project,file),
                            true // request focus to editor
                    );

                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.changesToAndroidManifest2(snippetContext);

                    addAICopilotCode(test);


                    //Messages.showMessageDialog("Aqui: " + newCodeH, "PASSOU AQUI", Messages.getInformationIcon());
                    writeOnFileManifest2(projectToSet, filesToSet, test);
                }else if(step.equals("4.1")){
                    VirtualFile mainActivityFile = findFileByName(project, "MainActivity");

                    if (mainActivityFile != null) {
                        // Open the MainActivity.java file in the editor
                        FileEditorManager.getInstance(project).openTextEditor(
                                new OpenFileDescriptor(project, mainActivityFile), true);

                    } else {

                        Messages.showMessageDialog("MainActivity.java not found. Proceeding with currently opened file.", "Error", Messages.getErrorIcon());
                    }


                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());


                        //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                        //obtain context of snippet
                        String snippetContext = fileContent;
                        // Call the makeApiCall method
                        String test = apiService.skuDetailsResponseListener(snippetContext, language);

                        addAICopilotCode(test);
                        writeOnFileSkuDetails(projectToSet, filesToSet, test);
                    } catch (IOException ex) {
                        fileContent = ex.getMessage().toString();
                        addAICopilotCode(fileContent);
                        throw new RuntimeException(ex);
                    }


                }else if(step.equals("4.2")){
                    VirtualFile mainActivityFile = findFileByName(project, "MainActivity");

                    if (mainActivityFile != null) {
                        // Open the MainActivity.java file in the editor
                        FileEditorManager.getInstance(project).openTextEditor(
                                new OpenFileDescriptor(project, mainActivityFile), true);

                    } else {

                        Messages.showMessageDialog("MainActivity.java not found. Proceeding with currently opened file.", "Error", Messages.getErrorIcon());
                    }


                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.queryInapps(snippetContext, language);

                    if(!test.equals("timeout")){
                        addAICopilotCode(test);
                        writeOnFileQuery(projectToSet, filesToSet, test);
                    }



                }else if(step.equals("5")){
                    VirtualFile mainActivityFile = findFileByName(project, "MainActivity");

                    if (mainActivityFile != null) {
                        // Open the MainActivity.java file in the editor
                        FileEditorManager.getInstance(project).openTextEditor(
                                new OpenFileDescriptor(project, mainActivityFile), true);

                    } else {

                        Messages.showMessageDialog("MainActivity.java not found. Proceeding with currently opened file.", "Error", Messages.getErrorIcon());
                    }


                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.startPurchase(snippetContext, language);

                    if(!test.equals("timeout")){
                        addAICopilotCode(test);
                        writeOnFileQuery(projectToSet, filesToSet, test);
                    }
                }else if(step.equals("6.1")){
                    VirtualFile mainActivityFile = findFileByName(project, "MainActivity");

                    if (mainActivityFile != null) {
                        // Open the MainActivity.java file in the editor
                        FileEditorManager.getInstance(project).openTextEditor(
                                new OpenFileDescriptor(project, mainActivityFile), true);

                    } else {

                        Messages.showMessageDialog("MainActivity.java not found. Proceeding with currently opened file.", "Error", Messages.getErrorIcon());
                    }


                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.onActivityResult(snippetContext, language);

                    if(!test.equals("timeout")){
                        addAICopilotCode(test);
                        writeOnFileQuery(projectToSet, filesToSet, test);
                    }
                }else if(step.equals("6.2")){
                    VirtualFile mainActivityFile = findFileByName(project, "MainActivity");

                    if (mainActivityFile != null) {
                        // Open the MainActivity.java file in the editor
                        FileEditorManager.getInstance(project).openTextEditor(
                                new OpenFileDescriptor(project, mainActivityFile), true);

                    } else {

                        Messages.showMessageDialog("MainActivity.java not found. Proceeding with currently opened file.", "Error", Messages.getErrorIcon());
                    }


                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.purchasesUpdatedListener(snippetContext, language);

                    if(!test.equals("timeout")){
                        addAICopilotCode(test);
                        writeOnFileQuery(projectToSet, filesToSet, test);
                    }else{
                        addAICopilotCode("Please retry again.");
                    }

                }else if(step.equals("7")){

                    VirtualFile mainActivityFile = findFileByName(project, "MainActivity");

                    if (mainActivityFile != null) {
                        // Open the MainActivity.java file in the editor
                        FileEditorManager.getInstance(project).openTextEditor(
                                new OpenFileDescriptor(project, mainActivityFile), true);

                    } else {

                        Messages.showMessageDialog("MainActivity.java not found. Proceeding with currently opened file.", "Error", Messages.getErrorIcon());
                    }


                    // Create an instance of ApiService
                    ApiService apiService = new ApiService();
                    // Step 1: Retrieve the content of the open file
                    VirtualFile currentFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                    String fileContent = "";
                    try {
                        fileContent = new String(currentFile.contentsToByteArray(), StandardCharsets.UTF_8);
                        //Messages.showMessageDialog("Data from file1: " + fileContent, "File Info", Messages.getInformationIcon());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Messages.showMessageDialog("Data from file2: " + fileContent, "File Info", Messages.getInformationIcon());
                    //obtain context of snippet
                    String snippetContext = fileContent;
                    // Call the makeApiCall method
                    String test = apiService.consumeResponseListener(snippetContext, language);

                    if(!test.equals("timeout")){
                        addAICopilotCode(test);
                        writeOnFileQuery(projectToSet, filesToSet, test);
                    }
                }


                //write filter for other parts like consume purchase listener makepurchase startconnection



            }
        });
    }

    public VirtualFile findFileByName(Project project, String fileName) {
        //VirtualFile baseDir = project.getBaseDir();
        String basePath = project.getBasePath();
        if (basePath == null) {
            basePath = "";
        }
        VirtualFile baseDir = LocalFileSystem.getInstance().findFileByPath(basePath);
        return findFileRecursively(baseDir, fileName);
    }

    private VirtualFile findFileRecursively(VirtualFile dir, String fileName) {
        if (dir == null || !dir.isDirectory()) {
            return null;
        }

        for (VirtualFile file : dir.getChildren()) {
            if (file.isDirectory()) {
                VirtualFile found = findFileRecursively(file, fileName);
                if (found != null) {
                    return found;
                }
            } else if (file.getName().startsWith(fileName)) {
                return file;
            }
        }
        return null;
    }

    private void createCodePanel(String language, String snippet, Color color) {
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextPane code = createCodeArea();
        addLanguageButton(language);

        addSnippetContent(language,snippet,color);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 6;
        codePanel.add(code, c);

        JLabel codeLabel = new JLabel("Implement Via", SwingConstants.CENTER);
        codeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Step 3: Add the JLabel to the codePanel below the JTextPane
        c.gridx = 0;
        c.gridy = 2; // Assuming the JTextPane is at gridy = 1
        c.weightx = 1;
        c.gridwidth = 6;
        c.anchor = GridBagConstraints.CENTER;
        codePanel.add(codeLabel, c);
        showLanguageSnippets(languageSnippetsMap.get(language));

        // Add the button panel to the code panel
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        codePanel.add(buttonPanel, c);

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
        implementAutomatically = new StatisticsRegisterButton("Static", action);
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
