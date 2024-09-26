package dialogs;

import actions.*;
import api.ApiService;
import com.intellij.ui.components.JBScrollPane;
import utils.ActionsSDK;
import utils.DialogColors;
import utils.MyPluginComponent;
import visual_elements.*;
import visual_elements.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import java.awt.Component;

import static java.awt.Font.getFont;
import static window_factory.BillingToolWindowFactory.snippets;

public class SDKDialogs {


    private static ImageIcon onIcon = new ImageIcon(new ImageIcon(CodeWindow.class.getClassLoader().getResource("1.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    private static ImageIcon offIcon = new ImageIcon(new ImageIcon(CodeWindow.class.getClassLoader().getResource("2.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));



    public  static JPanel changesToGradle(MyPluginComponent myPluginComponent){
        Card changesToGradle = new Card();
        JPanel changesToGradlePanel = new JPanel();
        changesToGradlePanel.setLayout(new BorderLayout(0, 20));


        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

// Create a panel to hold the toggle button and the text
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);

// Create and configure the text label
        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String changesToGradleTitle = "<br /><br />" + dialogElements.get(0);
        String changesToGradleBody = dialogElements.get(2) + "<br /><br />" + dialogElements.get(3);

        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(changesToGradleTitle, changesToGradleBody) + "</html>");
        topPanel.add(text, BorderLayout.CENTER);

// Add the top panel to the changesToGradlePanel
        changesToGradlePanel.add(topPanel, BorderLayout.NORTH);


        CodeWindow code = new CodeWindow("XML", snippets.buildGradleCodeAllprojects(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToGradle);
        code.addButtonAction(new ImplementBuildGradleAllProjectChanges(CardLayoutDialog.project,
                CardLayoutDialog.files, snippets));
        changesToGradlePanel.add(code.getPanel(), BorderLayout.CENTER);

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (!myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(!myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(!myPluginComponent.getValue() ? Color.GREEN : Color.RED);


            // Create an instance of ApiService
            ApiService apiService = new ApiService();
            // Call the makeApiCall method
            String test = apiService.makeApiCall();


            if(!myPluginComponent.getValue()) {
                code.addAICopilotCode(test);
            }else{
                code.addAICopilotCode(snippets.buildGradleCodeAllprojects());
            }

            myPluginComponent.saveValue(!myPluginComponent.getValue());
        });



        changesToGradlePanel.add(CardLayoutDialog.moreInformationLabel("",
                "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        changesToGradle.add(changesToGradlePanel);

        JBScrollPane scrollPane = new JBScrollPane(changesToGradlePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        changesToGradle.add(scrollPane, BorderLayout.CENTER);

        return changesToGradle.getPanel();
    }

    public static JPanel changesToGradle2(MyPluginComponent myPluginComponent){
        Card changesToGradle2 = new Card();
        JPanel changesToGradlePanel2 = new JPanel();
        changesToGradlePanel2.setLayout(new BorderLayout(0,20));



        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String changesToGradleTitle = "<br />" + dialogElements.get(0);
        String changesToGradleBody =  dialogElements.get(2) + "<br />" + dialogElements.get(3) +
                "<br /><br />" + dialogElements.get(4) +
                "<br><font color=#FD197C><a href=\"https://mvnrepository.com/artifact/io.catappult/android-appcoins-billing/usages\">" +
                 dialogElements.get(5) + dialogElements.get(6);

        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(changesToGradleTitle, changesToGradleBody) + "</html>");
        topPanel.add(text, BorderLayout.CENTER);

// Add the top panel to the changesToGradlePanel
        changesToGradlePanel2.add(topPanel, BorderLayout.NORTH);




        CodeWindow code = new CodeWindow("XML", snippets.appCoinsBillingDependency(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToGradle2);
        code.addButtonAction(new ImplementBuildGradleDependenciesChanges(CardLayoutDialog.project,
                CardLayoutDialog.files, snippets));
        changesToGradlePanel2.add(code.getPanel(), BorderLayout.CENTER);


        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                code.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });


        changesToGradlePanel2.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        changesToGradle2.add(changesToGradlePanel2);

        JBScrollPane scrollPane = new JBScrollPane(changesToGradlePanel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        changesToGradle2.add(scrollPane, BorderLayout.CENTER);

        return changesToGradle2.getPanel();

    }

    public static JPanel changesToAndroidManifest(MyPluginComponent myPluginComponent){
        Card changesToAndroidManifest = new Card();
        JPanel changesToAndroidManifestPanel = new JPanel();
        changesToAndroidManifestPanel.setLayout(new BorderLayout(0,20));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        String title = "<br />" + dialogElements.get(0);
        String body =  dialogElements.get(2) + "<br /><br />" + "<font color=#fff size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br /><br /><font color=#fff>"+ dialogElements.get(4) +"</font>";

        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(title, body) + "</html>");
        topPanel.add(text, BorderLayout.CENTER);
        changesToAndroidManifestPanel.add(topPanel, BorderLayout.NORTH);


        CodeWindow appCoinsSnippet = new CodeWindow("XML", snippets.androidManifestAppCoinsPermissions(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToAndroidManifest);
        appCoinsSnippet.addButtonAction(new ImplementAndroidManifestAppCoinsChanges(CardLayoutDialog.project,CardLayoutDialog.files));
        changesToAndroidManifestPanel.add(appCoinsSnippet.getPanel());

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                appCoinsSnippet.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });


        changesToAndroidManifestPanel.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);


        JBScrollPane scrollPane = new JBScrollPane(changesToAndroidManifestPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        changesToAndroidManifest.add(scrollPane, BorderLayout.CENTER);

        return changesToAndroidManifest.getPanel();
    }

    public static JPanel changesToAndroidManifest2(MyPluginComponent myPluginComponent){
        Card changesToAndroidManifest2 = new Card();
        JPanel changesToAndroidManifestPanel2 = new JPanel();
        changesToAndroidManifestPanel2.setLayout(new BorderLayout(0,20));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        String title = "<br />" + dialogElements.get(0);
        String body =   dialogElements.get(7) + "<br /><br />" + "<font color=#fff>" + dialogElements.get(8) + "</font>";


        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(title, body) + "</html>");
        topPanel.add(text, BorderLayout.CENTER);
        changesToAndroidManifestPanel2.add(topPanel, BorderLayout.NORTH);



        CodeWindow androidManifestQueries = new CodeWindow("XML", snippets.androidManifestQueries(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToAndroidManifest2);
        androidManifestQueries.addButtonAction(new ImplementAndroidManifestQueriesChanges(CardLayoutDialog.project,CardLayoutDialog.files, snippets));
        changesToAndroidManifestPanel2.add(androidManifestQueries.getPanel());

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                androidManifestQueries.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });

        changesToAndroidManifestPanel2.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(changesToAndroidManifestPanel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        changesToAndroidManifest2.add(scrollPane, BorderLayout.CENTER);

        return changesToAndroidManifest2.getPanel();
    }

    public static JPanel startingTheServiceConnection2(MyPluginComponent myPluginComponent){
        Card startingTheServiceConnection2 = new Card();
        startingTheServiceConnection2.setLayout(new BorderLayout(0,20));

        JPanel startingTheServiceConnection2Panel = new JPanel();
        startingTheServiceConnection2Panel.setLayout(new BoxLayout(startingTheServiceConnection2Panel, BoxLayout.Y_AXIS));

        JPanel startingTheServiceConnection2Panel1 = new JPanel();
        startingTheServiceConnection2Panel1.setLayout(new BorderLayout(0, 20));
        JPanel startingTheServiceConnection2Panel2 = new JPanel();
        startingTheServiceConnection2Panel2.setLayout(new BorderLayout(0, 20));
        JPanel startingTheServiceConnection2Panel3 = new JPanel();
        startingTheServiceConnection2Panel3.setLayout(new BorderLayout(0, 20));


        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(10);
        String title = dialogElements.get(0);
        String body = dialogElements.get(2) + "<br /><br />" +
                "<font color=#ffffff size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br><br><font color=#ffffff>"+ dialogElements.get(4) +"</font>";

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label1 = new JLabel("<html><br />" + topText + "</html>");
        topPanel.add(label1, BorderLayout.CENTER);
        startingTheServiceConnection2Panel1.add(topPanel, BorderLayout.NORTH);
        startingTheServiceConnection2Panel.add(startingTheServiceConnection2Panel1);



        String data[][] ={
                {"Name","Definition"},
                {"onPurchasesUpdated(responseCode,listPurchases)","This method receives the notifications for purchases updateds."}
        };
        Table table = new Table(data);
        table.getTable().setAlignmentX(Component.CENTER_ALIGNMENT);
        startingTheServiceConnection2Panel.add(table.getTable());


        JLabel label2 = new JLabel("<html>" + "<br /><br /><font color=#ffffff>"+ dialogElements.get(5) +"</font></html>" + "<br /><br />" + "<br /><br /></html>");
        startingTheServiceConnection2Panel2.add(label2, BorderLayout.NORTH);



        JPanel startingTheServiceConnection2Panel4 = new JPanel();
        startingTheServiceConnection2Panel4.setLayout(new BorderLayout(0, 20));


        CodeWindow onPurchasesUpdated = new CodeWindow(CardLayoutDialog.projLanguage, snippets.onPurchasesUpdated(),
                DialogColors.darkBlue, ActionsSDK.implementedStartingServiceConnection2);
        onPurchasesUpdated.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        onPurchasesUpdated.addButtonAction(new ImplementPurchaseFinishedListenerChanges(
                CardLayoutDialog.project,CardLayoutDialog.files,CardLayoutDialog.toolWindow));
        startingTheServiceConnection2Panel4.add(onPurchasesUpdated.getPanel(), BorderLayout.CENTER);
        startingTheServiceConnection2Panel.add(startingTheServiceConnection2Panel4);


        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                onPurchasesUpdated.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });

        startingTheServiceConnection2Panel.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(startingTheServiceConnection2Panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ensure the scroll starts from the top
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));

        startingTheServiceConnection2.add(scrollPane, BorderLayout.CENTER);

        return startingTheServiceConnection2.getPanel();
    }


    public static JPanel querySku(MyPluginComponent myPluginComponent) {
        Card querySku = new Card();
        JPanel querySkuPanel = new JPanel();
        querySkuPanel.setLayout(new BoxLayout(querySkuPanel, BoxLayout.Y_AXIS));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);
        querySkuPanel.add(topPanel, BorderLayout.NORTH);


        JPanel querySkuPanel1 = new JPanel();
        querySkuPanel1.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel2 = new JPanel();
        querySkuPanel2.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel3 = new JPanel();
        querySkuPanel3.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel4 = new JPanel();
        querySkuPanel4.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel5 = new JPanel();
        querySkuPanel5.setLayout(new BorderLayout(0, 20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(26);
        String title = dialogElements.get(0);
        String body = dialogElements.get(2) + "<br /><br />" +
                "<font color=#ffffff size=5><b>" + dialogElements.get(0) + "</b></font>" + "<br />";

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label1 = new JLabel("<html>" + topText + "</html>");
        querySkuPanel1.add(label1, BorderLayout.NORTH);
        JLabel label2 =  new JLabel("<html>" + "<br /><br /><font color=#ffffff>"+ dialogElements.get(4) +"</font></html>" + "<br /><br />" + "<br /><br /></html>");
        querySkuPanel2.add(label2, BorderLayout.NORTH);
        JLabel label3 = new JLabel("<html>" + "<br /><br /><font color=#ffffff>"+ dialogElements.get(5) +"</font></html>" + "<br /><br />" + "<br /><br /></html>");
        querySkuPanel3.add(label3, BorderLayout.NORTH);


        querySkuPanel.add(querySkuPanel1);

        String data[][] ={
                {"Name","Definition"},
                {"onSkuDetailsResponse(responseCode, skuDetailsList)","This method receives the result of a query of SKU" +
                        " details and the list of SKU details that were queried"}
        };

        Table table = new Table(data);
        table.getTable().setAlignmentX(Component.CENTER_ALIGNMENT);
        querySkuPanel.add(table.getTable(), BorderLayout.CENTER);


        querySkuPanel.add(querySkuPanel2);

        CodeWindow skuDetailsResponseListener = new CodeWindow(CardLayoutDialog.projLanguage, snippets.skuDetailsResponseListener(),
                DialogColors.darkBlue, ActionsSDK.implementedQuerySku);
        skuDetailsResponseListener.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        skuDetailsResponseListener.addButtonAction(new ImplementQueryPurchasesSkuListenerChanges(CardLayoutDialog.project,CardLayoutDialog.files));
        querySkuPanel4.add(skuDetailsResponseListener.getPanel(), BorderLayout.CENTER);
        querySkuPanel.add(querySkuPanel4);

        querySkuPanel.add(querySkuPanel3);

        CodeWindow callSkuDetails = new CodeWindow(CardLayoutDialog.projLanguage, snippets.callSkuDetails(),
                DialogColors.darkBlue);
        callSkuDetails.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        //not displaying
        callSkuDetails.addButtonAction(new ImplementQueryPurchasesExampleChanges(CardLayoutDialog.project,CardLayoutDialog.files));
        querySkuPanel5.add(callSkuDetails.getPanel(), BorderLayout.CENTER);

        querySkuPanel.add(querySkuPanel5);

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                skuDetailsResponseListener.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
                callSkuDetails.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });

        querySkuPanel.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(querySkuPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ensure the scroll starts from the top
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));

        querySku.add(scrollPane, BorderLayout.CENTER);

        return querySku.getPanel();
    }



    public static JPanel makingPurchase(MyPluginComponent myPluginComponent){
        Card makingPurchase = new Card();
        JPanel makingPurchasePanel2 = new JPanel();
        makingPurchasePanel2.setLayout(new BorderLayout(0,20));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(16);
        String title = dialogElements.get(0);
        String body =   dialogElements.get(2) + "<br /><br />" +
                "<font color=#FFFFFF>"+ dialogElements.get(4) +"</font></html>";

        JLabel text = new JLabel("<html><br />" + CardLayoutDialog.titleAndBodyHTMLFormated(title, body) + "</html>");
        topPanel.add(text, BorderLayout.CENTER);

        // Add the top panel to the changesToGradlePanel
        makingPurchasePanel2.add(topPanel, BorderLayout.NORTH);

        /**String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label = new JLabel("<html>" + topText + "</html>");
        makingPurchasePanel2.add(label, BorderLayout.NORTH);
        **/

        CodeWindow startPurchase = new CodeWindow(CardLayoutDialog.projLanguage, snippets.startPurchase(),
                DialogColors.darkBlue, ActionsSDK.implementedMakingPurchase);

        //if (CardLayoutDialog.files.containsKey(5) && CardLayoutDialog.files.containsKey(7)){;
            startPurchase.addButtonAction(new ImplementMakingAPurchaseChanges(
                    CardLayoutDialog.project,CardLayoutDialog.files,CardLayoutDialog.toolWindow));
        //}
        makingPurchasePanel2.add(startPurchase.getPanel());

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                startPurchase.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });

        makingPurchasePanel2.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(makingPurchasePanel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ensure the scroll starts from the top
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));


        makingPurchase.add(scrollPane, BorderLayout.CENTER);

        return makingPurchase.getPanel();
    }

    public static JPanel makingPurchase2(MyPluginComponent myPluginComponent){
        Card makingPurchase2 = new Card();
        JPanel makingPurchasePanel2 = new JPanel();
        makingPurchasePanel2.setLayout(new BorderLayout(0,20));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(16);
        String title = dialogElements.get(0);
        String body =   dialogElements.get(5) + "<br /><br />" +
                "<font color=#FFFFFF>"+ dialogElements.get(6) +"</font></html>";

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label = new JLabel("<html><br />" + topText + "</html>");
        topPanel.add(label, BorderLayout.CENTER);
        // Add the top panel to the changesToGradlePanel
        makingPurchasePanel2.add(topPanel, BorderLayout.NORTH);


        CodeWindow noOnActivityResult = new CodeWindow(CardLayoutDialog.projLanguage, snippets.noOnActivityResult(),
                DialogColors.darkBlue);
        makingPurchasePanel2.add(noOnActivityResult.getPanel());

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                noOnActivityResult.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });


        makingPurchasePanel2.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(makingPurchasePanel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ensure the scroll starts from the top
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));


        makingPurchase2.add(scrollPane, BorderLayout.CENTER);

        return makingPurchase2.getPanel();
    }


    public static JPanel consumePurchase(MyPluginComponent myPluginComponent){
        Card consumePurchase = new Card();

        JPanel consumePurchasePanel = new JPanel();
        consumePurchasePanel.setLayout(new BoxLayout(consumePurchasePanel, BoxLayout.Y_AXIS));

        JPanel querySkuPanel1 = new JPanel();
        querySkuPanel1.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel2 = new JPanel();
        querySkuPanel2.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel3 = new JPanel();
        querySkuPanel3.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel4 = new JPanel();
        querySkuPanel4.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel5 = new JPanel();
        querySkuPanel5.setLayout(new BorderLayout(0, 20));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(22);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);
        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label1 = new JLabel("<html>" + topText + "</html>");
        topPanel.add(label1, BorderLayout.CENTER);
        // Add the top panel to the changesToGradlePanel
        querySkuPanel1.add(topPanel, BorderLayout.NORTH);


        consumePurchasePanel.add(querySkuPanel1);

        JLabel label2 =  new JLabel("<html>" +
                "<br/><br/><font color=#ffffff size=5><b>" + dialogElements.get(0) + "</b></font>" +
                "<br/><br/><font color=#ffffff>"+ dialogElements.get(3) +"</font><br/></html>");
        querySkuPanel2.add(label2, BorderLayout.NORTH);
        consumePurchasePanel.add(querySkuPanel2);

        String data[][] ={
                {"Name","Definition"},
                {"onConsumeResponse(responseCode,purchaseToken)","Callback that notifies if a consume operation has ended."}
        };
        Table table = new Table(data);
        table.getTable().setAlignmentX(Component.CENTER_ALIGNMENT);
        consumePurchasePanel.add(table.getTable(), BorderLayout.CENTER);



        JLabel label3 =  new JLabel("<html>" +
                "<br /><br /><font color=#ffffff >" + dialogElements.get(4) + "</font><br/></html>");
        querySkuPanel3.add(label3, BorderLayout.NORTH);
        consumePurchasePanel.add(querySkuPanel3);


        CodeWindow consumePurchase1 = new CodeWindow(CardLayoutDialog.projLanguage, snippets.consumePurchase1(),
                DialogColors.darkBlue, ActionsSDK.implementedConsumePurchase);
        consumePurchase1.addButtonAction(new ImplementConsumeAPurchaseChanges(CardLayoutDialog.project, CardLayoutDialog.files));
        consumePurchasePanel.add(consumePurchase1.getPanel());
        querySkuPanel5.add(consumePurchase1.getPanel(), BorderLayout.CENTER);
        consumePurchasePanel.add(querySkuPanel5);

        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                consumePurchase1.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });


        consumePurchasePanel.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(consumePurchasePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ensure the scroll starts from the top
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));

        consumePurchase.add(scrollPane, BorderLayout.CENTER);

        return consumePurchase.getPanel();


    }

    static JPanel lastPage(MyPluginComponent myPluginComponent){
        Card lastPage = new Card();

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(29);

        JPanel successPanel = new JPanel();
        successPanel.setLayout(new BorderLayout(0,20));
        successPanel.setLayout(new BoxLayout(successPanel, BoxLayout.Y_AXIS));
        JLabel greenIcon = CardLayoutDialog.imageFromFileName("successImage.png");
        greenIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textCard1 = new JLabel("<html><br>" +
                "<font color=#FFFFFF size=6><b>"+ dialogElements.get(0) +"</b></font></html>", SwingConstants.CENTER);
        textCard1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textCard2 = new JLabel("<html>"+
                "<font color=#FFFFFF size=5>"+ dialogElements.get(2) +"</font></html>", SwingConstants.CENTER);
        textCard2.setAlignmentX(Component.CENTER_ALIGNMENT);
        successPanel.add(greenIcon);
        successPanel.add(textCard1);
        successPanel.add(textCard2);

        lastPage.add(successPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0,20));

        JLabel textCard3 = new JLabel("<html>" +
                "<font color=#FFFFFF>"+ dialogElements.get(3) +"</font>" +
                "<br>" +
                "<font color=#FD197C>"+ dialogElements.get(4) +"</font></html>" +
                "</html>");
        CardLayoutDialog.makeClickable(textCard3, "https://docs.catappult.io/docs/submission-forms");
        textCard3.setBorder(new EmptyBorder(0,5,0,0));

        panel.add(CardLayoutDialog.imageFromFileName("informationSymbol.png"), BorderLayout.WEST);
        panel.add(textCard3, BorderLayout.CENTER);

        lastPage.add(panel, BorderLayout.SOUTH);

        return lastPage.getPanel();
    }

    public static JPanel serverCheck(MyPluginComponent myPluginComponent){
        Card serverCheck = new Card();
        JPanel serverCheckPanel = new JPanel();
        serverCheckPanel.setLayout(new BoxLayout(serverCheckPanel, BoxLayout.Y_AXIS));

        JToggleButton aiCopilotToggle = new JToggleButton("");
        aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
        aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
        aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);
        aiCopilotToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiCopilotToggle.setFont(new Font(aiCopilotToggle.getFont().getName(),Font.BOLD,20)); // Set font size to 16

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(aiCopilotToggle, BorderLayout.NORTH);

        JPanel querySkuPanel1 = new JPanel();
        querySkuPanel1.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel2 = new JPanel();
        querySkuPanel2.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel3 = new JPanel();
        querySkuPanel3.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel4 = new JPanel();
        querySkuPanel4.setLayout(new BorderLayout(0, 20));
        JPanel querySkuPanel5 = new JPanel();
        querySkuPanel5.setLayout(new BorderLayout(0, 20));



        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(33);
        String title = dialogElements.get(0);
        String body = dialogElements.get(2) + "<br /><br />" +
                "<font color=#ffffff size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br /><br /><font color=#ffffff>"+ dialogElements.get(4) +"</font>";
        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label1 = new JLabel("<html>" + topText + "<br/></html>");
        topPanel.add(label1, BorderLayout.CENTER);
        // Add the top panel to the changesToGradlePanel
        querySkuPanel1.add(topPanel, BorderLayout.NORTH);



        serverCheckPanel.add(querySkuPanel1);

        CodeWindow receipt = new CodeWindow("JSON", snippets.serverCheckReceipt(),
                DialogColors.darkBlue);
        receipt.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);

        serverCheckPanel.add(receipt.getPanel());


        JLabel label2 =  new JLabel("<html>" +
                "<br/><font color=#ffffff>"+ dialogElements.get(5) +"</font><br/></html>");
        querySkuPanel2.add(label2, BorderLayout.NORTH);
        serverCheckPanel.add(querySkuPanel2);

        CodeWindow apiRequestPurchase = new CodeWindow("HTTP", snippets.serverCheckAPIRequest(),
                DialogColors.darkBlue);
        apiRequestPurchase.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        serverCheckPanel.add(apiRequestPurchase.getPanel());


        JLabel label3 = new JLabel("<html>" +
                "<br/><font color=#ffffff>"+ dialogElements.get(6) +"</font><br/></html>");
        querySkuPanel3.add(label3, BorderLayout.NORTH);
        serverCheckPanel.add(querySkuPanel3);


        CodeWindow apiRequestPurchaseSubscriptions = new CodeWindow("HTTP", snippets.serverCheckAPIRequestSubscriptions(),
                DialogColors.darkBlue);
        apiRequestPurchaseSubscriptions.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        serverCheckPanel.add(apiRequestPurchaseSubscriptions.getPanel());

        JLabel label4 = new JLabel("<html>" +
                "<br/><font color=#ffffff>"+ dialogElements.get(7) +"</font><br/></html>");
        querySkuPanel4.add(label4, BorderLayout.NORTH);
        serverCheckPanel.add(querySkuPanel4);

        CodeWindow performRequest = new CodeWindow("PHP", snippets.serverCheckRequestPHP(), DialogColors.darkBlue);
        performRequest.addSnippetContent("Java", snippets.serverCheckRequestJava(), DialogColors.darkBlue);
        performRequest.addSnippetContent("Python", snippets.serverCheckRequestPython(), DialogColors.darkBlue);
        performRequest.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        serverCheckPanel.add(performRequest.getPanel());


        JLabel label5 = new JLabel("<html>" +
                "<br/><font color=#ffffff size=5><b>" + dialogElements.get(8) + "</b><br/></font>" +
                "<font color=#ffffff>"+ dialogElements.get(9) +"</font><br/>" +
                "</html>");
        querySkuPanel5.add(label5, BorderLayout.NORTH);
        serverCheckPanel.add(querySkuPanel5);

        CodeWindow apiRequestResponse = new CodeWindow("JSON", snippets.serverCheckResponse(),
                DialogColors.darkBlue);
        apiRequestResponse.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        serverCheckPanel.add(apiRequestResponse.getPanel());



        aiCopilotToggle.addItemListener(e -> {
            aiCopilotToggle.setText("AI Copilot: " + (myPluginComponent.getValue() ? "ON" : "OFF"));
            aiCopilotToggle.setIcon(myPluginComponent.getValue() ? onIcon : offIcon);
            aiCopilotToggle.setForeground(myPluginComponent.getValue() ? Color.GREEN : Color.RED);

            myPluginComponent.saveValue(myPluginComponent.getValue());

            if(myPluginComponent.getValue()) {
                receipt.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
                apiRequestPurchase.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
                apiRequestPurchaseSubscriptions.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
                performRequest.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
                apiRequestResponse.addAICopilotCode(snippets.androidManifestGooglePlayPermissions());
            }

        });

        serverCheckPanel.add(CardLayoutDialog.moreInformationLabel("",
                        "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"),
                BorderLayout.SOUTH);

        JBScrollPane scrollPane = new JBScrollPane(serverCheckPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ensure the scroll starts from the top
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));

        serverCheck.add(scrollPane, BorderLayout.CENTER);

        return serverCheck.getPanel();
    }
}
