package dialogs;

import actions.*;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBScrollPane;
import utils.ActionsSDK;
import utils.DialogColors;
import visual_elements.*;
import visual_elements.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static window_factory.BillingToolWindowFactory.snippets;

public class SDKDialogs {

    public static JPanel changesToGradle(){
        Card changesToGradle = new Card();
        JPanel changesToGradlePanel = new JPanel();
        changesToGradlePanel.setLayout(new BorderLayout(0,20));


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String changesToGradleTitle = dialogElements.get(0);
        String changesToGradleBody =  dialogElements.get(2) + "<br /><br />" + dialogElements.get(3);

        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(changesToGradleTitle, changesToGradleBody) + "</html>");
        changesToGradlePanel.add(text, BorderLayout.NORTH);


        CodeWindow code = new CodeWindow("XML", snippets.buildGradleCodeAllprojects(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToGradle);
        code.addButtonAction(new ImplementBuildGradleAllProjectChanges(CardLayoutDialog.project,
                CardLayoutDialog.files, snippets));
        changesToGradlePanel.add(code.getPanel(), BorderLayout.CENTER);


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

    public static JPanel changesToGradle2(){
        Card changesToGradle2 = new Card();
        JPanel changesToGradlePanel2 = new JPanel();
        changesToGradlePanel2.setLayout(new BorderLayout(0,20));


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String changesToGradleTitle = dialogElements.get(0);
        String changesToGradleBody =  dialogElements.get(2) + "<br />" + dialogElements.get(3) +
                "<br /><br />" + dialogElements.get(4) +
                "<br><font color=#FD197C><a href=\"https://mvnrepository.com/artifact/io.catappult/android-appcoins-billing/usages\">" +
                 dialogElements.get(5) + dialogElements.get(6);




        JLabel text = new JLabel("<html>" + CardLayoutDialog.titleAndBodyHTMLFormated(changesToGradleTitle, changesToGradleBody) + "</html>");
        CardLayoutDialog.makeClickable(text, "https://mvnrepository.com/artifact/io.catappult/android-appcoins-billing/usages");
        changesToGradlePanel2.add(text, BorderLayout.NORTH);


        CodeWindow code = new CodeWindow("XML", snippets.appCoinsBillingDependency(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToGradle2);
        code.addButtonAction(new ImplementBuildGradleDependenciesChanges(CardLayoutDialog.project,
                CardLayoutDialog.files, snippets));
        changesToGradlePanel2.add(code.getPanel(), BorderLayout.CENTER);


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

    public static JPanel changesToAndroidManifest(){
        Card changesToAndroidManifest = new Card();

        JPanel changesToAndroidManifestPanel = new JPanel();
        changesToAndroidManifestPanel.setLayout(new BorderLayout(0,20));


        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2) + "<br /><br />" + "<font color=#fff size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br /><br /><font color=#fff>"+ dialogElements.get(4) +"</font>";


        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        JLabel label = new JLabel("<html>" + topText + "<br /><br /></html>");
        changesToAndroidManifestPanel.add(label, BorderLayout.NORTH);


        CodeWindow appCoinsSnippet = new CodeWindow("XML", snippets.androidManifestAppCoinsPermissions(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToAndroidManifest);
        appCoinsSnippet.addButtonAction(new ImplementAndroidManifestAppCoinsChanges(CardLayoutDialog.project,CardLayoutDialog.files));
        changesToAndroidManifestPanel.add(appCoinsSnippet.getPanel());




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

    public static JPanel changesToAndroidManifest2(){
        Card startingTheServiceConnection = new Card();
        startingTheServiceConnection.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        String title = "<h1>TESTE 313</h1>"+dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        startingTheServiceConnection.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(7) + "</b></font>" +
                "<br><br><font color=#220F67>"+ dialogElements.get(8) +"</font></html>");

        panel.add(textCard);
        panel.addRigidArea(new Dimension(0, 30));

        CodeWindow androidManifestQueries = new CodeWindow("XML", snippets.androidManifestQueries(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToAndroidManifest2);
        androidManifestQueries.addButtonAction(new ImplementAndroidManifestQueriesChanges(CardLayoutDialog.project,CardLayoutDialog.files, snippets));

        panel.add(androidManifestQueries.getPanel());

        startingTheServiceConnection.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return startingTheServiceConnection.getPanel();
    }

    public static JPanel startingTheServiceConnection2(){
        Card startingTheServiceConnection2 = new Card();
        startingTheServiceConnection2.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(10);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        startingTheServiceConnection2.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#starting-the-service-connection"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br><br><font color=#220F67>"+ dialogElements.get(4) +"</font></html>");
        textCard2.setHorizontalAlignment(JLabel.LEFT);

        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(5) +"</font></html>");
        textCard3.setHorizontalAlignment(JLabel.LEFT);

        String data[][] ={
                {"Name","Definition"},
                {"onPurchasesUpdated(responseCode,listPurchases)","This method receives the notifications for purchases updateds."}
        };
        Table table = new Table(data);

        panel.add(textCard2);
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(table.getTable());
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard3);
        panel.addRigidArea(new Dimension(0, 20));

        CodeWindow onPurchasesUpdated = new CodeWindow(CardLayoutDialog.projLanguage, snippets.onPurchasesUpdated(),
                DialogColors.darkBlue, ActionsSDK.implementedStartingServiceConnection2);
        onPurchasesUpdated.addButtonAction(new ImplementPurchaseFinishedListenerChanges(
                CardLayoutDialog.project,CardLayoutDialog.files,CardLayoutDialog.toolWindow));
        panel.add(onPurchasesUpdated.getPanel());

        startingTheServiceConnection2.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return startingTheServiceConnection2.getPanel();
    }

    public static JPanel querySku(){
        Card querySku = new Card();
        querySku.setLayout(new BorderLayout(20,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(26);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        querySku.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#2-query-in-app-products"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(0) + "</b></font</html>");

        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(4) +"</font></html>");

        JLabel textCard4 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(5) +"</font></html>");

        String data[][] ={
                {"Name","Definition"},
                {"onSkuDetailsResponse(responseCode, skuDetailsList)","This method receives the result of a query of SKU" +
                        " details and the list of SKU details that were queried"}
        };
        Table table = new Table(data);

        panel.add(textCard2);
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(table.getTable());
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard3);

        CodeWindow skuDetailsResponseListener = new CodeWindow(CardLayoutDialog.projLanguage, snippets.skuDetailsResponseListener(),
                DialogColors.darkBlue, ActionsSDK.implementedQuerySku);
        skuDetailsResponseListener.addButtonAction(new ImplementQueryPurchasesSkuListenerChanges(CardLayoutDialog.project,CardLayoutDialog.files));
        panel.add(skuDetailsResponseListener.getPanel());

        panel.addRigidArea(new Dimension(0, 30));
        panel.add(textCard4);
        panel.addRigidArea(new Dimension(0, 10));

        CodeWindow callSkuDetails = new CodeWindow(CardLayoutDialog.projLanguage, snippets.callSkuDetails(),
                DialogColors.darkBlue);
        callSkuDetails.addButtonAction(new ImplementQueryPurchasesExampleChanges(CardLayoutDialog.project,CardLayoutDialog.files));
        panel.add(callSkuDetails.getPanel());

        querySku.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return querySku.getPanel();
    }

    public static JPanel makingPurchase(){
        Card makingPurchase = new Card();
        makingPurchase.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(16);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        makingPurchase.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#3-launch-the-appcoins-wallet"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(4) +"</font></html>");

        CodeWindow startPurchase = new CodeWindow(CardLayoutDialog.projLanguage, snippets.startPurchase(),
                DialogColors.darkBlue, ActionsSDK.implementedMakingPurchase);

        if (CardLayoutDialog.files.containsKey(5) && CardLayoutDialog.files.containsKey(7)){;
            startPurchase.addButtonAction(new ImplementMakingAPurchaseChanges(
                    CardLayoutDialog.project,CardLayoutDialog.files,CardLayoutDialog.toolWindow));
        }

        panel.add(textCard2);
        panel.addRigidArea(new Dimension(0, 10));
        panel.add(startPurchase.getPanel());

        makingPurchase.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return makingPurchase.getPanel();
    }

    public static JPanel makingPurchase2(){
        Card makingPurchase = new Card();
        makingPurchase.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(16);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(5);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        makingPurchase.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#3-launch-the-appcoins-wallet"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(6) +"</font></html>");

        CodeWindow noOnActivityResult = new CodeWindow(CardLayoutDialog.projLanguage, snippets.noOnActivityResult(),
                DialogColors.darkBlue);

        panel.add(textCard);
        panel.addRigidArea(new Dimension(0, 10));
        panel.add(noOnActivityResult.getPanel());

        makingPurchase.add(panel.getPanel(), BorderLayout.CENTER);

        return makingPurchase.getPanel();
    }

    public static JPanel consumePurchase(){
        Card consumePurchase = new Card();
        consumePurchase.setLayout(new BorderLayout(20,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(22);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        consumePurchase.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#4-process-the-purchase-and-give-item-to-user"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(0) + "</b></font>" +
                "<br><br><font color=#220F67>"+ dialogElements.get(3) +"</font></html>");

        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(4) +"</font></html>");

        String data[][] ={
                {"Name","Definition"},
                {"onConsumeResponse(responseCode,purchaseToken)","Callback that notifies if a consume operation has ended."}
        };
        Table table = new Table(data);

        panel.add(textCard2);
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 10)));
        panel.add(table.getTable());
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 30)));
        panel.add(textCard3);

        CodeWindow consumePurchase1 = new CodeWindow(CardLayoutDialog.projLanguage, snippets.consumePurchase1(),
                DialogColors.darkBlue, ActionsSDK.implementedConsumePurchase);
        consumePurchase1.addButtonAction(new ImplementConsumeAPurchaseChanges(CardLayoutDialog.project, CardLayoutDialog.files));
        panel.add(consumePurchase1.getPanel());

        consumePurchase.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return consumePurchase.getPanel();
    }

    static JPanel lastPage(){
        Card lastPage = new Card();

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(29);

        Panel successPanel = new Panel(DialogColors.lightBlue);
        successPanel.setLayout(new BorderLayout(0,20));
        successPanel.setLayout(new BoxLayout(successPanel.getPanel(), BoxLayout.Y_AXIS));
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

        lastPage.add(successPanel.getPanel(), BorderLayout.CENTER);

        Panel panel = new Panel(DialogColors.darkBlue);
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

        lastPage.add(panel.getPanel(), BorderLayout.SOUTH);

        return lastPage.getPanel();
    }

    public static RoundedScrollablePanelBorder serverCheck(){
        Card serverCheck = new Card();

        serverCheck.setLayout(new BorderLayout(20,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(33);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        serverCheck.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#2-query-in-app-products"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(3) + "</b></font</html>");
        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(4) +"</font></html>");
        JLabel textCard4 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(5) +"</font></html>");
        JLabel textCard5 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(6) +"</font></html>");
        JLabel textCard6 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(7) +"</font></html>");
        JLabel textCard7 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(8) + "</b></font</html>");
        JLabel textCard8 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(9) +"</font></html>");

        panel.add(textCard2);
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard3);
        panel.addRigidArea(new Dimension(0, 10));

        CodeWindow receipt = new CodeWindow("JSON", snippets.serverCheckReceipt(),
                DialogColors.darkBlue);
        panel.add(receipt.getPanel());

        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard4);
        panel.addRigidArea(new Dimension(0, 10));

        CodeWindow apiRequestPurchase = new CodeWindow("HTTP", snippets.serverCheckAPIRequest(),
                DialogColors.darkBlue);
        panel.add(apiRequestPurchase.getPanel());
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard5);
        panel.addRigidArea(new Dimension(0, 10));
        CodeWindow apiRequestPurchaseSubscriptions = new CodeWindow("HTTP", snippets.serverCheckAPIRequestSubscriptions(),
                DialogColors.darkBlue);
        panel.add(apiRequestPurchaseSubscriptions.getPanel());
        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard6);
        panel.addRigidArea(new Dimension(0, 10));

        CodeWindow performRequest = new CodeWindow("PHP", snippets.serverCheckRequestPHP(), DialogColors.darkBlue);
        performRequest.addSnippetContent("Java", snippets.serverCheckRequestJava(), DialogColors.darkBlue);
        performRequest.addSnippetContent("Python", snippets.serverCheckRequestPython(), DialogColors.darkBlue);

        panel.add(performRequest.getPanel());

        panel.addRigidArea(new Dimension(0, 20));
        panel.add(textCard7);
        panel.addRigidArea(new Dimension(0, 10));
        panel.add(textCard8);
        panel.addRigidArea(new Dimension(0, 20));

        CodeWindow apiRequestResponse = new CodeWindow("JSON", snippets.serverCheckResponse(),
                DialogColors.darkBlue);
        panel.add(apiRequestResponse.getPanel());

        serverCheck.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return serverCheck.getScrollablePanel();
    }
}
