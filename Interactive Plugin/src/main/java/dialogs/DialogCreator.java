package dialogs;

import actions.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import snipets.Snippets;
import utils.DialogColors;
import utils.JKeyComponent;
import window_factory.BillingToolWindowFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class DialogCreator {

    static private final Color backgroundColor = UIManager.getColor ( "Panel.background" );
    static private final Snippets SNIPPETS = BillingToolWindowFactory.snippets;
    public static JPanel cardLayout(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        JPanel panel = null;
        try {
            panel = new CardLayoutDialog(project, files, toolWindow, new BillingToolWindowFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return panel;
    }

    public static JScrollPane introduction(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow) {
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(0);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        JComponent textPanel = getText(dialogElements.get(2));
        textPanel.setPreferredSize(new Dimension(300,300));
        panel.add(textPanel,c);
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=4;
        JButton button = new JButton(new StartImplementation(project,files,toolWindow));
        panel.add(button,c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel goToBuildGradle(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(1);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        panel.add(getTitle("TESTE 1 " + dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;


        JButton button = new JButton(new GoToBuildGradle(project, files, toolWindow));
        panel.add(button,c);

        return panel;
    }

    public static JScrollPane buildGradleDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridwidth=3;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        URL imageURL = GoToBuildGradle.class.getClassLoader().getResource("buildgradle.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.ipady = 10;
        c.weightx=0.33;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(new JButton(new ImplementBuildGradleDependenciesChanges(project,files, SNIPPETS)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.weightx=0.34;
        c.gridx = 1;
        c.gridy = 5;
        c.ipady = 10;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.weightx=0.33;
        c.gridx = 2;
        c.gridy = 5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,0)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane buildGradleManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(3);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridwidth=2;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        String text1 = SNIPPETS.buildGradleCodeAllprojects();
        panel.add(getCode("\n"+text1),c);
        c.gridx=0;
        c.gridy=4;
        panel.add(new JButton(new CopyToClipboard(text1)),c);
        c.gridx=0;
        c.gridy=5;
        panel.add(getText(dialogElements.get(3)),c);
        c.gridx=0;
        c.gridy=6;
        String text2 = SNIPPETS.appCoinsBillingDependency();
        panel.add(getCode("\n"+text2),c);
        c.gridx=0;
        c.gridy=7;
        panel.add(new JButton(new CopyToClipboard(text2)),c);
        c.gridx=0;
        c.gridy=8;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=9;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,1)),c);
        c.gridx=1;
        c.gridy=9;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,1)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel buildGradleConfirmation (Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(4);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoBuildGradleChanges(project,files,toolWindow,oldContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,1)),c);
        return panel;
    }

    public static JPanel goToAndroidManifest(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(5);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new GoToAndroidManifest(project,files,toolWindow)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        panel.add(new JButton(new BackButton(project,files,toolWindow,1)),c);
        return panel;
    }

    public static JScrollPane androidManifestDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=3;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx = 0;
        c.gridy = 4;
        URL imageURL = GoToAndroidManifest.class.getClassLoader().getResource("androidmanifest.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        imageURL = GoToAndroidManifest.class.getClassLoader().getResource("queries.png");
        image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.gridx=0;
        c.gridy=6;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.33;
        panel.add(new JButton(new ImplementAndroidManifestAppCoinsChanges(project,files)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=6;
        c.weightx=0.34;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=6;
        c.weightx=0.33;
        panel.add(new JButton(new BackButton(project,files,toolWindow,1)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane androidManifestManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(7);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridwidth=2;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        String text1 = SNIPPETS.androidManifestAppCoinsPermissions();
        panel.add(getCode("\n"+text1),c);
        c.gridx=0;
        c.gridy=4;
        panel.add(new JButton(new CopyToClipboard(text1)),c);
        c.gridx=0;
        c.gridy=5;
        panel.add(getText(dialogElements.get(3)),c);
        c.gridx=0;
        c.gridy=6;
        String text2 = SNIPPETS.androidManifestQueries();
        panel.add(getCode("\n"+text2),c);
        c.gridx=0;
        c.gridy=7;
        panel.add(new JButton(new CopyToClipboard(text2)),c);
        c.gridx=0;
        c.gridy=8;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=9;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,2)),c);
        c.gridx=1;
        c.gridy=9;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,2)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel androidManifestConfirmation (Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(8);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoAndroidManifestChanges(project,files,toolWindow,oldContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,2)),c);
        return panel;
    }

    public static JScrollPane goToStartingServiceConnection(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(9);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new GoToStartingServiceConnection(project,files,toolWindow)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        panel.add(new JButton(new BackButton(project,files,toolWindow,2)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane noOnCreate(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(10);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridwidth=1;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth=2;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("appcoinsbillingstatelistener.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth=2;
        imageURL = ConfirmChanges.class.getClassLoader().getResource("purchasesupdatedlistener.png");
        image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,3)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=6;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,2)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane startingServiceConnectionDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(11);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=6;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth=6;
        URL imageURL = GoToStartingServiceConnection.class.getClassLoader().getResource("appcoinsbillingstatelistener.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth=6;
        imageURL = GoToStartingServiceConnection.class.getClassLoader().getResource("purchasesupdatedlistener.png");
        image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        c.ipady = 10;
        c.gridwidth=2;
        c.weightx=0.33;
        panel.add(new JButton(new ImplementStartingServiceConnectionChanges(project,files)),c);
        c.gridx=2;
        c.gridy=6;
        c.weightx=0.34;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,3)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=4;
        c.gridy=6;
        c.weightx=0.33;
        panel.add(new JButton(new BackButton(project,files,toolWindow,2)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane startingServiceConnectionManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(12);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridwidth=2;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        String text1 = SNIPPETS.appCoinsBillingStateListener();
        panel.add(getCode("\n"+text1),c);
        c.gridx=0;
        c.gridy=5;
        panel.add(new JButton(new CopyToClipboard(text1)),c);
        c.gridx=0;
        c.gridy=6;
        panel.add(getText(dialogElements.get(3)),c);
        c.gridx=0;
        c.gridy=7;
        String text2 = SNIPPETS.onPurchasesUpdated();
        panel.add(getCode("\n"+text2),c);
        c.gridx=0;
        c.gridy=8;
        panel.add(new JButton(new CopyToClipboard(text2)),c);
        c.gridx=0;
        c.gridy=9;
        panel.add(getText(dialogElements.get(4)),c);
        c.gridx=0;
        c.gridy=10;
        String text3 = SNIPPETS.onCreate();
        panel.add(getCode("\n"+text3),c);
        c.gridx=0;
        c.gridy=11;
        panel.add(new JButton(new CopyToClipboard(text3)),c);
        c.gridx=0;
        c.gridy=12;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,3)),c);
        c.gridx=1;
        c.gridy=12;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,3)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane getKey(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(13);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        JKeyComponent keyComponent = new JKeyComponent(backgroundColor);
        panel.add(keyComponent.getPanel(),c);
        /*c.gridwidth=1;
        c.weightx=0.5;
        centerPanel.add(getLabel("Insert public key: "),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        centerPanel.add(key, c);
        */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth=2;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        URL imageURL = GoToStartingServiceConnection.class.getClassLoader().getResource("key.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.gridx=0;
        c.ipady = 10;
        c.gridy=6;
        c.weightx=0.5;
        c.gridwidth=1;
        panel.add(new JButton(new ContinueStartingServiceConnectionChanges(project,files,toolWindow,keyComponent.getKey())),c);
        c.gridx=1;
        c.gridy=6;
        panel.add(new JButton(new BackButton(project,files,toolWindow,3)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 1150,750));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel startingServiceConnectionConfirmation (Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(14);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoStartingServiceConnectionChanges(project,files,toolWindow,oldContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,3)),c);
        return panel;
    }

    public static JScrollPane goToMakingAPurchase(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(15);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new GoToMakingAPurchase(project,files,toolWindow)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        panel.add(new JButton(new BackButton(project,files,toolWindow,3)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane noMakingAPurchase(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(16);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth=2;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("MakingAPurchase.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,4)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=5;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,3)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane makingAPurchaseDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(17);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=3;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("MakingAPurchase.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.33;
        panel.add(new JButton(new ImplementMakingAPurchaseChanges(project,files,toolWindow)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=5;
        c.weightx=0.33;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,4)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=5;
        c.weightx=0.33;
        panel.add(new JButton(new BackButton(project,files,toolWindow,4)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel makingAPurchaseConfirmation(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(18);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoSimpleMakingAPurchaseChanges(project,files,toolWindow,oldContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,4)),c);
        return panel;
    }

    public static JPanel makingAPurchaseConfirmation(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldReferenceContent, String oldDeclarationContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(18);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoMakingAPurchaseChanges(project,files,toolWindow,oldReferenceContent,oldDeclarationContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,4)),c);
        return panel;
    }

    public static JScrollPane makingAPurchaseManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(19);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridwidth=2;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        String text = SNIPPETS.startPurchase();
        panel.add(getCode("\n"+text),c);
        c.gridx=0;
        c.gridy=5;
        panel.add(new JButton(new CopyToClipboard(text)),c);
        c.gridx=0;
        c.gridy=6;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,4)),c);
        c.gridx=1;
        c.gridy=6;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,4)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane goToOnActivityResult(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(19);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("OnActivityResult.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new GoToOnActivityResult(project,files,toolWindow)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,4)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane onActivityResultManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(20);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        c.ipady=20;
        String text = SNIPPETS.onActivityResultManual();
        panel.add(getCode("\n"+text),c);
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        panel.add(new JButton(new CopyToClipboard(text)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,5)),c);
        c.gridx=1;
        c.gridy=6;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,5)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane noOnActivityResult(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(21);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.ipady = 20;
        c.gridy=4;
        String text = SNIPPETS.noOnActivityResult();
        panel.add(getCode("\n"+text),c);
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        panel.add(new JButton(new CopyToClipboard(text)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,5)),c);
        c.gridx=1;
        c.gridy=6;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,5)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane consumeAPurchaseDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(22);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=3;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("ConsumeAPurchase.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.33;
        panel.add(new JButton(new ImplementConsumeAPurchaseChanges(project,files)),c);
        c.gridx=1;
        c.gridy=5;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,5)),c);
        c.gridx=2;
        c.gridy=5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,5)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane consumeAPurchaseNoMainActivity(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(23);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("ConsumeAPurchase.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=5;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,5)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=5;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,5)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane consumeAPurchaseManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(24);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        String text1 = SNIPPETS.consumePurchase1();
        panel.add(getCode("\n"+text1),c);
        c.gridx=0;
        c.gridy=5;
        panel.add(new JButton(new CopyToClipboard(text1)),c);
        c.gridx=0;
        c.gridy=6;
        panel.add(getText(dialogElements.get(3)),c);
        c.gridx=0;
        c.gridy=7;
        String text2 = SNIPPETS.consumePurchase2();
        panel.add(getCode("\n"+text2),c);
        c.gridx=0;
        c.gridy=8;
        panel.add(new JButton(new CopyToClipboard(text2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=9;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,6)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=9;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,6)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel consumeAPurchaseConfirmation(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(25);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoConsumeAPurchaseChanges(project,files,toolWindow,oldContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,5)),c);
        return panel;
    }

    public static JScrollPane queryPurchasesDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(26);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=3;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        URL imageURL = ConfirmChanges.class.getClassLoader().getResource("SkuDetailsResponseListener.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        imageURL = ConfirmChanges.class.getClassLoader().getResource("CallSkuDetails.png");
        image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JLabel(new ImageIcon(image)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        c.gridwidth=1;
        c.weightx=0.33;
        panel.add(new JButton(new ImplementQueryPurchasesExampleChanges(project,files)),c);
        c.gridx=1;
        c.gridy=6;
        panel.add(new JButton(new StartManualChanges(project,files,toolWindow,6)),c);
        c.gridx=2;
        c.gridy=6;
        panel.add(new JButton(new BackButton(project,files,toolWindow,6)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JScrollPane queryPurchasesManual(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(27);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(getLink(),c);
        c.gridx=0;
        c.gridy=4;
        String text1 = SNIPPETS.skuDetailsResponseListener();
        panel.add(getCode("\n"+text1),c);
        c.gridx=0;
        c.gridy=5;
        panel.add(new JButton(new CopyToClipboard(text1)),c);
        c.gridx=0;
        c.gridy=6;
        panel.add(getText(dialogElements.get(3)),c);
        c.gridx=0;
        c.gridy=7;
        String text2 = SNIPPETS.callSkuDetails();
        panel.add(getCode("\n"+text2),c);
        c.gridx=0;
        c.gridy=8;
        panel.add(new JButton(new CopyToClipboard(text2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=9;
        c.ipady = 10;
        c.gridwidth=1;
        c.weightx=0.5;
        panel.add(new JButton(new FinishManualChanges(project,files,toolWindow,7)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=9;
        c.weightx=0.5;
        panel.add(new JButton(new BackButton(project,files,toolWindow,7)),c);
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 800,300));
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        return scrollFrame;
    }

    public static JPanel queryPurchasesConfirmation(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(28);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getStep(dialogElements.get(1)),c);
        c.gridx=0;
        c.gridy=2;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new UndoQueryPurchasesChanges(project,files,toolWindow,oldContent)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        panel.add(new JButton(new ConfirmChanges(project,files,toolWindow,6)),c);
        return panel;
    }

    public static JPanel conclusion(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(29);
        JPanel panel= new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBag gridbag = new GridBag()
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL)
                .setDefaultInsets(new Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        panel.add(getTitle(dialogElements.get(0)),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(getText(dialogElements.get(2)),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        panel.add(new JButton(new BackButton(project,files,toolWindow,7)),c);
        return panel;
    }
    private static JComponent getText(String text){
        JTextPane textPane = new JTextPane();
        StyledDocument documentStyle = textPane.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        textPane.setText(text);
        textPane.setFont(new Font("SansSerif",Font.PLAIN,20));
        textPane.setForeground(Color.LIGHT_GRAY);
        textPane.setBackground(backgroundColor);
        textPane.setBorder(JBUI.Borders.empty(0,5,2,0));
        textPane.setEditable(false);
        return textPane;
    }

    private static JComponent getCode(String text){
        JTextArea label = new JTextArea(text);
        label.setFont(new Font("Monospaced",Font.PLAIN,15));
        label.setForeground(Color.LIGHT_GRAY);
        label.setBackground(backgroundColor);
        label.setBorder(JBUI.Borders.empty(0,5,2,0));
        label.setEditable(false);
        return label;
    }

    private static JComponent getTitle(String text){
        JTextField title = new JTextField(text);
        title.setFont(new Font("Serif",Font.BOLD,30));
        title.setForeground(Color.WHITE);
        title.setBackground(backgroundColor);
        title.setBorder(JBUI.Borders.empty(0,5,2,0));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setEditable(false);
        return title;
    }

    private static JComponent getLink() {
        JTextField link = new JTextField("More information in https://docs.catappult.io/docs/native-android-billing-sdk");
        link.setFont(new Font("SansSerif",Font.PLAIN,20));
        link.setForeground(Color.LIGHT_GRAY);
        link.setBackground(backgroundColor);
        link.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    //5 Nov 2024 . this page doenst exist
                    //Desktop.getDesktop().browse(new URI("https://docs.catappult.io/docs/native-android-sdk-edit"));
                    Desktop.getDesktop().browse(new URI("#"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e2) {
                    e2.printStackTrace();
                }
            }
        });
        link.setEditable(false);
        link.setHorizontalAlignment(JTextField.CENTER);
        return link;
    }
    private static JComponent getStep(String step){
        JTextField title = new JTextField("Step "+step+" of 7");
        title.setFont(new Font("SansSerif",Font.BOLD,15));
        title.setForeground(Color.WHITE);
        title.setBackground(backgroundColor);
        title.setBorder(JBUI.Borders.empty(0,5,2,0));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setEditable(false);
        return title;
    }
}
