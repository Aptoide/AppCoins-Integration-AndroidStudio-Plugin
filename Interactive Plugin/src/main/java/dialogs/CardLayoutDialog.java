package dialogs;

import actions.ContinueStartingServiceConnectionChanges;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import snipets.Snippets;
import utils.*;
import visual_elements.Panel;
import visual_elements.*;
import window_factory.BillingToolWindowFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static dialogs.XmlDialogParser.getTableByIndex;

public class CardLayoutDialog extends JPanel {
    private final int PUBLICK_KEY_PAGE = 15;
    private final int SDK_PAGES_LIMIT = 14;
    private final int OSP_PAGES_LIMIT = 5;
    private String currFlow = "";
    private int currCard = 1;
    private int step = 1;
    private JProgressBar progressBar;
    private JLabel stepLabel;
    private CardLayout cObjl;
    private JPanel cPanel;
    public static Project project;
    public static Map<Integer, VirtualFile> files;
    public static ToolWindow toolWindow;
    private Snippets snippets;
    public static String projLanguage;
    private JPanel progressPanel;
    private JPanel btnPanel;
    private JButton nextButton;
    private int pagesLimit;
    private MetricsClient httpRequest;
    public CardLayoutDialog(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, BillingToolWindowFactory billingToolWindowFactory) throws IOException {
        this.project = project;
        this.files = files;
        this.toolWindow = toolWindow;
        this.snippets = billingToolWindowFactory.snippets;
        this.projLanguage = billingToolWindowFactory.projectLanguage;
        this.httpRequest = new MetricsClient();


        setSize(390, 250);
        cPanel = new JPanel();
        cObjl = new CardLayout();
        cPanel.setLayout(cObjl);
        cPanel.add(methodChoosingPage(), "sdk1");
        cPanel.add(startImplementation(), "sdk2");
        cPanel.add(SDKDialogs.changesToGradle(), "sdk3");
        cPanel.add(SDKDialogs.changesToGradle2(), "sdk4");
        cPanel.add(permissions(), "sdk5");
        cPanel.add(SDKDialogs.changesToAndroidManifest(),"sdk6");
        cPanel.add(SDKDialogs.changesToAndroidManifest2(),"sdk7");
        cPanel.add(startingTheServiceConnection(),"sdk8");
        cPanel.add(SDKDialogs.querySku(),"sdk9");
        cPanel.add(SDKDialogs.makingPurchase(),"sdk10");
        cPanel.add(SDKDialogs.makingPurchase2(),"sdk11");
        cPanel.add(SDKDialogs.startingTheServiceConnection2(),"sdk12");
        cPanel.add(SDKDialogs.consumePurchase(),"sdk13");
        cPanel.add(SDKDialogs.lastPage(),"sdk14");
        cPanel.add(publicKey(),"sdk15");

        cPanel.add(methodChoosingPage(), "osp1");
        cPanel.add(OSPDialogs.generateOSPUrl(), "osp2");
        cPanel.add(OSPDialogs.createIntent(), "osp3");
        cPanel.add(OSPDialogs.createWebServiceEndpoint(),"osp4");
        cPanel.add(SDKDialogs.lastPage(),"osp5");

        httpRequest.registerAction(ActionsSDK.start);

        this.setLayout(new BorderLayout());
        this.add(catappultLogoPanel(), BorderLayout.NORTH);
        this.add(cPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public JPanel catappultLogoPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(0,40,0,0));
        panel.setBackground(DialogColors.lightBlue);
        panel.add(imageFromFileName("logo-catappult.png"), BorderLayout.LINE_START);
        panel.add(imageFromFileName("circles-catappult.png"), BorderLayout.LINE_END);
        return panel;
    }
    public void previousCard(){
        if (currCard > 1) {
            currCard = currCard - 1;
            cObjl.show(cPanel, currFlow + (currCard));
            registerActionOfFlow(currFlow, currCard - 1);
            stepManager();
        }
    }

    public void nextCard(){
        if (currCard == 1){
            if (currFlow == "sdk"){
                addProgressBar(7);
            } else {
                addProgressBar(3);
            }
        }

        if (currCard < pagesLimit)
        {
            currCard = currCard + 1;
            cObjl.show(cPanel, currFlow + (currCard));
            registerActionOfFlow(currFlow, currCard - 1);
            stepManager();
        }
    }

    public void setStep(int s){
        this.step = s;
        progressBar.setValue(this.step);
        stepLabel.setText("Step " + this.step);
    }
    public boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    private void stepManager(){
        if (currFlow == "sdk"){
            if (isBetween(currCard, 1, 4)) {
                setStep(1);
            } else if (isBetween(currCard, 5, 6)) {
                setStep(2);
            } else if (isBetween(currCard, 7, 7)) {
                setStep(3);
            } else if (isBetween(currCard, 8, 9)) {
                setStep(4);
            } else if (isBetween(currCard, 10, 10)) {
                setStep(5);
            } else if (isBetween(currCard, 11, 12)) {
                setStep(6);
            } else if (isBetween(currCard, 13, 13)) {
                setStep(7);
            }
        } else {
            if (isBetween(currCard, 1, 2)) {
                setStep(1);
            } else if (isBetween(currCard, 3, 3)) {
                setStep(2);
            } else if (isBetween(currCard, 4, 4)) {
                setStep(3);
            }
        }
    }
    public JButton createGoBackButton(){
        JButton previousButton = createColoredButton("Go back", DialogColors.lightBlack);
        previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                previousCard();
            }
        });
        return previousButton;
    }

    public JButton createNextCardButton() {
        nextButton = createColoredButton("Continue", DialogColors.pink);
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                nextCard();
            }
        });

        return nextButton;
    }
    public JPanel addControlButtons(){
        btnPanel = new JPanel(new GridLayout(0,2));
        btnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnPanel.add(createGoBackButton());
        btnPanel.add(createNextCardButton());
        setBackgroundColor(btnPanel, DialogColors.black);
        return btnPanel;
    }

    public void addProgressBar(int limit){
        progressPanel = new JPanel(new GridBagLayout());
        progressPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        stepLabel = new JLabel("Step " + this.step);
        progressBar = new JProgressBar(0, limit);
        progressBar.setValue(this.step);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.gridwidth = 2;
        progressPanel.add(progressBar,c);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        progressPanel.add(stepLabel,c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 4;
        progressPanel.add(addControlButtons(),c);
        setBackgroundColor(progressPanel, DialogColors.black);

        this.add(progressPanel, BorderLayout.SOUTH);
    }

    public JPanel methodChoosingPage(){
        Card lastPage = new Card();

        Panel successPanel = new Panel(DialogColors.lightBlue);
        successPanel.setLayout(new BoxLayout(successPanel.getPanel(), BoxLayout.Y_AXIS));
        JLabel textCard1 = new JLabel("<html><br>" +
                "<font color=#FFFFFF size=6><b> Faster development with one single integration </b></font>" +
                "</html>", JLabel.CENTER);
        textCard1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textCard2 = new JLabel("<html>"+
                "<font color=#FFFFFF size=5> Developers should spend their time creating the best games and apps and not integrating SDKs. </font></html>", JLabel.CENTER);
        textCard2.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(textCard1);
        successPanel.add(textCard2);
        successPanel.addRigidArea(new Dimension(0, 20));

        String data[][] = getTableByIndex(2);
        JTable table = new TableComparation(data).getTable();
        successPanel.add(table);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.setBackground(DialogColors.lightBlue);

        JLabel label1 = new JLabel("<html><div style='text-align:center;'>"
                + "<span style='font-size:14px;font-weight:bold;color:#FFFFFF;'>"
                + "One-Step Payment</span><br>"
                + "<span style='font-size:10px;color:#FFFFFF;'>"
                + "Easy and simple way</span></div></html>");

        JLabel label2 = new JLabel("<html><div style='text-align:center;'>"
                + "<span style='font-size:14px;font-weight:bold;color:#FFFFFF;'>"
                + "          SDK          </span><br>"
                + "<span style='font-size:10px;color:#FFFFFF;'>"
                + "Traditional way</span></div></html>");

        JPanel buttonLeft = new JPanel(new GridBagLayout());
        buttonLeft.setBackground(DialogColors.lightBlue);
        selectFlowOnClick(buttonLeft, "osp");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton languageButton = new JButton("Recommended");
        languageButton.setForeground(DialogColors.white);
        languageButton.setBackground(DialogColors.pink);
        languageButton.setUI(new BestChoiceTab());
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        buttonLeft.add(languageButton, c);

        JPanel btn1 = new JPanel();
        c.insets = new Insets(0,0,0,10);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.3;
        c.gridwidth = 3;
        btn1.setBorder(BorderFactory.createLineBorder(DialogColors.pink));
        btn1.setBackground(DialogColors.lightBlue);
        btn1.add(label1);
        buttonLeft.add(btn1, c);

        JPanel buttonRight = new JPanel();
        buttonRight.setLayout(new GridBagLayout());
        buttonRight.setBackground(DialogColors.lightBlue);
        selectFlowOnClick(buttonRight, "sdk");

        JPanel btn2 = new JPanel();
        c.insets = new Insets(22,0,0,0);
        btn2.setBorder(BorderFactory.createLineBorder(DialogColors.pink));
        btn2.setBackground(DialogColors.lightBlue);
        btn2.add(label2);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.gridwidth = 1;
        buttonRight.add(btn2,c);

        panel.add(buttonLeft);
        panel.add(buttonRight);
        successPanel.add(panel);

        lastPage.add(successPanel.getPanel(), BorderLayout.CENTER);

        return lastPage.getPanel();
    }

    public JPanel startImplementation(){
        Card startImplementation = new Card();
        Panel panel = new Panel(DialogColors.darkBlue);
        panel.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(title, body) + "</html>");
        panel.add(text, BorderLayout.CENTER);

        JButton startImplementationButton = CardLayoutDialog.createColoredButton("Go to build.gradle", DialogColors.pink, ActionsSDK.goToBuildGradleButton);

        startImplementationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                VirtualFile file = CardLayoutDialog.files.get(2);
                FileEditorManager.getInstance(CardLayoutDialog.project).openTextEditor(
                        new OpenFileDescriptor(CardLayoutDialog.project,file),
                        true // request focus to editor
                );
                nextCard();
            }

        });

        panel.add(startImplementationButton, BorderLayout.SOUTH);
        startImplementation.add(panel.getPanel());

        return startImplementation.getPanel();
    }

    public JPanel permissions(){
        Card permissions = new Card();
        Panel panel = new Panel(DialogColors.darkBlue);
        panel.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(5);
        String title = dialogElements.get(0);
        String body = dialogElements.get(2);

        JLabel text = new JLabel("<html>" +
                "<font color=#ffffff size=6><B>" + title + "</B></font>" +
                "<br><br>" +
                "<font color=#ffffff>" + body + "</font></html>");
        panel.add(text, BorderLayout.CENTER);

        JButton goToAndroidManifestButton = CardLayoutDialog.createColoredButton("Go to Android Manifest", DialogColors.pink, ActionsSDK.goToAndroidManifest);
        goToAndroidManifestButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                VirtualFile file = CardLayoutDialog.files.get(3);
                FileEditorManager.getInstance(CardLayoutDialog.project).openTextEditor(
                        new OpenFileDescriptor(CardLayoutDialog.project,file),
                        true // request focus to editor
                );
                nextCard();
            }

        });

        CardLayoutDialog.setFileToOpen(goToAndroidManifestButton, CardLayoutDialog.files.get(3));

        panel.add(goToAndroidManifestButton, BorderLayout.SOUTH);
        permissions.add(panel.getPanel());

        return permissions.getPanel();
    }

    public JPanel startingTheServiceConnection(){
        Card changesToAndroidManifest = new Card();
        changesToAndroidManifest.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(9);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        changesToAndroidManifest.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#starting-the-service-connection"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br><br><font color=#220F67>"+ dialogElements.get(4) +"</font></html>");

        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(5) +"</font></html>");

        String data[][] ={
                {"Name","Definition"},
                {"onBiilingSetupFinished(responseCode)","This method is called when the billing setup process is complete."},
                {"onBiilingServiceDisconnected()","This method is called when the billing connection is lost."}
        };
        Table table = new Table(data);

        panel.add(textCard2);
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(table.getTable());
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard3);

        CodeWindow appCoinsBillingStateListener = new CodeWindow(CardLayoutDialog.projLanguage, snippets.appCoinsBillingStateListener(),
                DialogColors.darkBlue, ActionsSDK.implementedStartingServiceConnection);
        appCoinsBillingStateListener.setImplementAutomaticallyButtonText("Partially implement changes automatically");

        if(CardLayoutDialog.files.get(4)!=null){
            appCoinsBillingStateListener.addButtonActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    VirtualFile file = CardLayoutDialog.files.get(4);
                    FileEditorManager.getInstance(CardLayoutDialog.project).openTextEditor(
                            new OpenFileDescriptor(CardLayoutDialog.project,file),
                            true // request focus to editor
                    );
                    cObjl.show(cPanel, "sdk" + (PUBLICK_KEY_PAGE));
                }
            });
        }

        panel.addRigidArea(new Dimension(0, 20));
        panel.add(appCoinsBillingStateListener.getPanel());

        changesToAndroidManifest.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return changesToAndroidManifest.getPanel();
    }

    public RoundedScrollablePanelBorder publicKey(){
        Card publicKey = new Card();
        publicKey.setLayout(new BorderLayout(20,0));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(13);
        String title = dialogElements.get(0);
        String body = dialogElements.get(2);

        Panel panel = new Panel(DialogColors.lightBlue);
        panel.setLayout(new BoxLayout(panel.getPanel(), BoxLayout.Y_AXIS));

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label = CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#4-process-the-purchase-and-give-item-to-user");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#FFFFFF>"+ dialogElements.get(3) +"</font></html>");
        panel.addRigidArea(new Dimension(0, 15));
        panel.add(textCard2);

        JTextField key = new JTextField();
        key.setBackground(DialogColors.white);
        new TextPrompt("Your public key here…", key);


        JButton implementPublicKey = CardLayoutDialog.createColoredButton("Implement", DialogColors.pink, ActionsSDK.implementedPublicKey);
        implementPublicKey.setAction(new ContinueStartingServiceConnectionChanges(
                CardLayoutDialog.project, CardLayoutDialog.files, CardLayoutDialog.toolWindow, key));
        implementPublicKey.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCard();
            }
        });

        JPanel row = new JPanel(new GridBagLayout());
        row.setBackground(DialogColors.lightBlue);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.gridwidth = 2;
        row.add(key, c);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        row.add(implementPublicKey, c);
        panel.add(row);

        JLabel picLabel = imageFromFileName("apiKey.png");
        picLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        publicKey.add(panel.getScrollablePanel(), BorderLayout.NORTH);
        publicKey.add(picLabel, BorderLayout.CENTER);

        return publicKey.getScrollablePanel();
    }
    private static void setBackgroundColor(JComponent comp, Color color){
        comp.setBackground(color);
    }

    private static void setButtonMargin(JButton button){
        button.setMargin(new Insets(40, 40, 40, 40));
    }
    private JButton createColoredButton(String text, Color color){
        JButton button = new JButton(text);
        button.setForeground(Color.decode("#FFFFFF"));
        setBackgroundColor(button, color);
        button.setUI(new RoundedButton());
        setButtonMargin(button);
        return button;
    }

    static JButton createColoredButton(String text, Color color, ActionsSDK action){
        JButton button = new StatisticsRegisterButton(text, action);
        button.setForeground(Color.decode("#FFFFFF"));
        setBackgroundColor(button, color);
        button.setUI(new RoundedButton());
        setButtonMargin(button);
        return button;
    }

    static String titleAndBodyHTMLFormated(String title, String body){
        return "<font color=#ffffff size=6><B>" + title + "</B></font>" +
                "<br><br>" +
                "<font color=#ffffff>" + body + "</font>";
    }

    static void makeClickable(JLabel label, String url) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
    }

    static JLabel turnTextIntoLeftAlignedJLabel(String text){
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    static JLabel moreInformationLabel(String text, String link){

        JLabel label = new JLabel("<html>" + text +
                "<br><br>" +
                "<font color=#FFFFFF>More information in </font>" +
                "<font color=#FD197C> https://docs.catappult.io/docs/native-android-sdk </font>" +
                "<br>" +
                "<font color=#FFFFFF>If you need help send us an email: </font>" +
                "<font color=#FD197C>support@catappult.io</font>" +
                "</html>");
        makeClickable(label, link);
        return label;
    }

    static void setFileToOpen(JButton button, VirtualFile file){
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileEditorManager.getInstance(project).openTextEditor(
                        new OpenFileDescriptor(project,file),
                        true // request focus to editor
                );
            }

        });
    }

    static JLabel imageFromFileName(String name){
        URL url = CodeWindow.class.getClassLoader().getResource(name);
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JLabel(new ImageIcon(image));
    }

    private void selectFlowOnClick(JPanel btn, String flow){
        btn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                currFlow = flow;
                if (currFlow == "sdk"){
                    pagesLimit = SDK_PAGES_LIMIT;

                } else {
                    pagesLimit = OSP_PAGES_LIMIT;
                }
                nextCard();
            }
        });
    }

    private void registerActionOfFlow(String flow, int position){
        if (flow == "sdk"){
            httpRequest.registerAction(ActionsSDK.values()[position]);
        } else {
            httpRequest.registerAction(ActionsOSP.values()[position]);
        }
    }
}
