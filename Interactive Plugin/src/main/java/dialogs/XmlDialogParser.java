package dialogs;

import actions.ConfirmChanges;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XmlDialogParser {

    private static Document dialogsParsed = parseXmlDialogs();
    private static NodeList pageElementsOfXml = dialogsParsed.getElementsByTagName("page");
    private static NodeList tableElementsOfXml = dialogsParsed.getElementsByTagName("table");

    public static Document parseXmlDialogs() {
        return parseXml("/dialogs.xml");
    }

    public static Document parseXml(String filePath){
        try {
            InputStream xmlFileStream = ConfirmChanges.class.getResourceAsStream(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFileStream);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<String> getPageBodyDialogs(Element element){
        ArrayList<String> bodyStrings = new ArrayList<String>();
        NodeList bodyNodes = element.getElementsByTagName("body");
        for (int i = 0; i < bodyNodes.getLength(); i++) {
            bodyStrings.add(bodyNodes.item(i).getTextContent());
        }
        return bodyStrings;
    }

    public static ArrayList<String> getPageDialogElementsByIndex(int index){
        Node node = pageElementsOfXml.item(index);
        Element element = (Element) node;
        ArrayList<String> dialogs = new ArrayList<String>();
        String title = element.getElementsByTagName("title").item(0).getTextContent();
        String step = element.getElementsByTagName("step").item(0).getTextContent();
        ArrayList<String> bodies = getPageBodyDialogs(element);
        dialogs.add(title);
        dialogs.add(step);
        dialogs.addAll(bodies);
        return dialogs;
    }

    public static String[][] getTableByIndex(int index){
        Document doc = parseXml("/dialogs.xml");
        Node node = tableElementsOfXml.item(index);
        Element element = (Element) node;

        NodeList nList = element.getElementsByTagName("row");

        int numRows = nList.getLength();
        int numCells = 0;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                NodeList cells = eElement.getElementsByTagName("cell");
                numCells = cells.getLength();
            }
        }

        String[][] array = new String[numRows][numCells];

        for (int i = 0; i < numRows; i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                NodeList cells = eElement.getElementsByTagName("cell");
                for (int j = 0; j < numCells; j++) {
                    Node cell = cells.item(j);
                    if (cell.getNodeType() == Node.ELEMENT_NODE) {
                        Element cellElement = (Element) cell;
                        array[i][j] = cellElement.getTextContent();
                    }
                }
            }
        }
        return array;
    }
}
