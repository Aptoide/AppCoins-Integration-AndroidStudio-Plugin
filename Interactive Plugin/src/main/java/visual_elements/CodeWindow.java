package visual_elements;

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

public class CodeWindow {
    JPanel codePanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JTextPane tPane;
    Map<String, JButton> languageButtonsMap = new HashMap<String, JButton>();
    Map<String, ArrayList<Snippet>> languageSnippetsMap = new HashMap<String, ArrayList<Snippet>>();

    JButton implementAutomatically = new JButton("Implement automatically");;

    public CodeWindow(String language, String snippet, Color color) {
        createCodePanel(language, snippet, color);
    }

    public CodeWindow(String language, String snippet, Color color, Actions action) {
        createCodePanel(language, snippet, color);
        addImplementAutomaticallyButton(action);
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
        } else {
            languageButton.setBackground(DialogColors.lightGray);
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
        implementAutomatically = new JButton();//new StatisticsRegisterButton("Implement automatically", action);
        implementAutomatically.setUI(new ImplementAutomaticallyButton());
        implementAutomatically.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DialogColors.lightGray, 1, true),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        JPanel grayRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //grayRow.setBackground(DialogColors.lightGray);
        grayRow.add(implementAutomatically);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        codePanel.add(grayRow, c);
        implementAutomatically.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                implementAutomatically.setOpaque(false);
                implementAutomatically.setForeground(DialogColors.green);
                implementAutomatically.setBorder(BorderFactory.createEmptyBorder());
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
        //parentPanel.setBackground(DialogColors.white);
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
