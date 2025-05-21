package dialogs;

import actions.ImplementAndroidManifestQueriesChanges;
import actions.ImplementCreateOSPIntent;
import utils.ActionsOSP;
import utils.DialogColors;
import visual_elements.Card;
import visual_elements.CodeWindow;
import visual_elements.Panel;
import visual_elements.Table;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static dialogs.XmlDialogParser.getTableByIndex;
import static window_factory.BillingToolWindowFactory.snippets;

public class OSPDialogs {
    public static JPanel generateOSPUrl(){
        Card consumePurchase = new Card();

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(30);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        consumePurchase.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/native-android-billing-sdk#4-process-the-purchase-and-give-item-to-user"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(0) + "</b></font>" +
                "<br><br>" +
                "<font color=#220F67>"+ dialogElements.get(3) +"</font>" +
                "<font color=#FD187C> "+ dialogElements.get(4) +"</font>" +
                "<font color=#220F67> "+ dialogElements.get(5) +"</font>" +
                "</html>");

        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(6) +"</font></html>");

        JLabel textCard4 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(7) +"</font>" +
                "<font color=#FD187C> "+ dialogElements.get(8) +"</font>" +
                "<br><br>" +
                "<font color=#220F67>"+ dialogElements.get(9) +"</font>" +
                "<br><br>" +
                "<font color=#220F67>"+ dialogElements.get(10) +"</font>" +
                "</html>");
        CardLayoutDialog.makeClickable(textCard4, "https://docs.catappult.io/docs/secret-key-management");

        JLabel textCard5 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(11) +"</font>" +
                "<font color=#FD187C> "+ dialogElements.get(12) +"</font>" +
                "<br><font color=#220F67> "+ dialogElements.get(13) +"</font>" +
                "</html>");
        CardLayoutDialog.makeClickable(textCard5, "https://catappult.io/easy-integrations");

        String data[][] = getTableByIndex(0);

        CodeWindow query = new CodeWindow("Text", snippets.ospQuery(), DialogColors.darkBlue);

        CodeWindow generateUrl = new CodeWindow("PHP", snippets.ospPHP(), DialogColors.darkBlue);
        generateUrl.addSnippetContent("JavaScript", snippets.ospJavaScript(), DialogColors.darkBlue);
        generateUrl.addSnippetContent("Python", snippets.ospPython(), DialogColors.darkBlue);

        Table table = new Table(data);

        panel.add(textCard2);
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 10)));
        panel.add(table.getTable());
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 20)));
        panel.add(textCard3);
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 10)));
        panel.add(query.getPanel());
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 20)));
        panel.add(textCard4);
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 10)));
        panel.add(generateUrl.getPanel());
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 10)));
        panel.add(textCard5);

        consumePurchase.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return consumePurchase.getPanel();
    }

    public static JPanel createIntent(){
        Card createIntent = new Card();

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(31);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        createIntent.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/osp#2-create-an-intent-to-process-the-payment"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>" + dialogElements.get(3) + "</font></html>");

        panel.add(textCard);
        panel.addRigidArea(new Dimension(0, 30));

        CodeWindow ospIntent = new CodeWindow(CardLayoutDialog.projLanguage, snippets.ospIntent(),
                DialogColors.darkBlue, ActionsOSP.implementCreateOSPIntent, "");
        ospIntent.addButtonAction(new ImplementCreateOSPIntent(
                CardLayoutDialog.project,CardLayoutDialog.files,CardLayoutDialog.toolWindow));
        panel.add(ospIntent.getPanel());

        panel.addRigidArea(new Dimension(0, 20));

        JLabel textCard1 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>" + dialogElements.get(4) + "</font></html>");
        panel.add(textCard1);

        panel.addRigidArea(new Dimension(0, 10));

        CodeWindow androidManifest = new CodeWindow(CardLayoutDialog.projLanguage, snippets.androidManifestQueries(),
                DialogColors.darkBlue, ActionsOSP.changesToAndroidManifest2, "");
        androidManifest.addButtonAction(new ImplementAndroidManifestQueriesChanges(CardLayoutDialog.project,CardLayoutDialog.files, snippets));
        panel.add(androidManifest.getPanel());

        panel.addRigidArea(new Dimension(0, 20));

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>" + dialogElements.get(5) + "</font></html>");
        panel.add(textCard2);

        createIntent.add(panel.getScrollablePanel(), BorderLayout.CENTER);

        return createIntent.getPanel();
    }

    public static JPanel createWebServiceEndpoint(){
        Card createWebServiceEndpoint = new Card();

        ArrayList<String> dialogElements = XmlDialogParser.getPageDialogElementsByIndex(32);
        String title = dialogElements.get(0);
        String body =  dialogElements.get(2);

        String topText = CardLayoutDialog.titleAndBodyHTMLFormated(title, body);
        createWebServiceEndpoint.add(CardLayoutDialog.moreInformationLabel(topText, "https://docs.catappult.io/docs/osp#3-create-a-web-service-endpoint-to-be-used-as-the-callback-url"), BorderLayout.NORTH);

        Panel panel = new Panel(DialogColors.white);

        JLabel textCard2 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67 size=5><b>" + dialogElements.get(0) + "</b></font>" +
                "<br><br>" +
                "<font color=#220F67>"+ dialogElements.get(3) +"</font>" +
                "</html>");

        JLabel textCard3 = CardLayoutDialog.turnTextIntoLeftAlignedJLabel("<html>" +
                "<font color=#220F67>"+ dialogElements.get(4) +"</font>" +
                "<font color=#FD187C> "+ dialogElements.get(5) +"</font>" +
                "<font color=#220F67>"+ dialogElements.get(6) +"</font>" +
                "<font color=#FD187C> "+ dialogElements.get(7) +"</font>" +
                "<font color=#220F67>"+ dialogElements.get(8) +"</font>" +
                "<br>" +
                "<font color=#220F67>"+ dialogElements.get(9) +"</font>" +
                "</html>");
        CardLayoutDialog.makeClickable(textCard3, "https://api.catappult.io/broker/8.20220927/transactions/2DtyvTOSShc1xT9C");

        String data[][] = getTableByIndex(1);
        Table table = new Table(data);

        panel.add(textCard2);
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 10)));
        panel.add(table.getTable());
        panel.add((JComponent) Box.createRigidArea(new Dimension(0, 20)));
        panel.add(textCard3);

        createWebServiceEndpoint.add(panel.getScrollablePanel());

        return createWebServiceEndpoint.getPanel();
    }
}
