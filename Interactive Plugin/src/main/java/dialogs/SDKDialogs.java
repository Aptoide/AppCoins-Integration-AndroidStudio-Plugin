package dialogs;

import actions.*;
import utils.ActionsSDK;
import utils.DialogColors;
import visual_elements.Card;
import visual_elements.CodeWindow;
import visual_elements.Panel;
import visual_elements.Table;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static window_factory.BillingToolWindowFactory.snippets;

public class SDKDialogs {

    public static JPanel changesToGradle(){
        Card changesToGradle = new Card();
        changesToGradle.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        changesToGradle.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard = new JLabel("<html>" +
                "<font color=#220F67>" + dialogElements.get(3) + "</font></html>");
        textCard.setHorizontalAlignment(JLabel.LEFT);

        CodeWindow code = new CodeWindow("XML", snippets.buildGradleCodeAllprojects(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToGradle);
        code.addButtonAction(new ImplementBuildGradleAllProjectChanges(CardLayoutDialog.project,CardLayoutDialog.files, snippets));

        panel.add(textCard);
        panel.addRigidArea(new Dimension(0, 10));
        panel.add(code.getPanel());

        changesToGradle.add(panel.getPanel(), BorderLayout.CENTER);

        return changesToGradle.getPanel();
    }

    public static JPanel changesToGradle2(){
        Card changesToGradle = new Card();
        changesToGradle.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(2);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        changesToGradle.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>" + dialogElements.get(4) + "</font>" +
                "<br><font color=#FD197C><a href=\"https://mvnrepository.com/artifact/io.catappult/android-appcoins-billing/usages\">"
                + dialogElements.get(5) + "</a></font>" +
                "<font color=#220F67>"+ dialogElements.get(6) +"</font></html>");
        CardLayoutDialog.makeClickable(textCard, "https://mvnrepository.com/artifact/io.catappult/android-appcoins-billing/usages");

        CodeWindow code = new CodeWindow("XML", snippets.appCoinsBillingDependency(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToGradle2);
        code.addButtonAction(new ImplementBuildGradleDependenciesChanges(CardLayoutDialog.project,CardLayoutDialog.files, snippets));

        panel.add(textCard);
        panel.addRigidArea(new Dimension(0, 10));
        panel.add(code.getPanel());

        changesToGradle.add(panel.getPanel(), BorderLayout.CENTER);

        return changesToGradle.getPanel();
    }

    public static JPanel changesToAndroidManifest(){
        Card changesToAndroidManifest = new Card();

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        changesToAndroidManifest.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-sdk#1-setup-connection-with-catappult-billing-sdk"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(3) + "</b></font>" +
                "<br><br><font color=#220F67>"+ dialogElements.get(4) +"</font></html>");
        JLabel textCard1 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(6) + "</b></font></html>");

        panel.add(textCard);
        panel.addRigidArea(new Dimension(0, 30));
        panel.add(textCard1);

        CodeWindow appCoinsSnippet = new CodeWindow("XML", snippets.androidManifestAppCoinsPermissions(),
                DialogColors.darkBlue, ActionsSDK.implementedChangesToAndroidManifest);
        appCoinsSnippet.addButtonAction(new ImplementAndroidManifestAppCoinsChanges(CardLayoutDialog.project,CardLayoutDialog.files));

        panel.addRigidArea(new Dimension(0, 10));
        panel.addRigidArea(new Dimension(20, 0));
        panel.add(appCoinsSnippet.getPanel());

        changesToAndroidManifest.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return changesToAndroidManifest.getPanel();
    }

    public static JPanel changesToAndroidManifest2(){
        Card startingTheServiceConnection = new Card();
        startingTheServiceConnection.setLayout(new BorderLayout(0,20));

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(6);
        String title = dialogElements.get(0);
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
}
