package domdtkug01026;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryDTKUG0 {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {

		File xmlFile = new File("carsDTKUG0.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		System.out.println("------------------");
		LoadQuery(doc);

	}

	public static void LoadQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("supercars");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Element element = (Element) nNode;
			int db = element.getElementsByTagName("carname").getLength();
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (element.getAttribute("company").equals("Ferrari")) {
					QueryXML(db, doc, element);

				}
				if (element.getAttribute("company").equals("Lamborghini")) {
					QueryXML(db, doc, element);

				}
			}
		}

	}

	public static void QueryXML(int db, Document doc, Element element) {
		String company = element.getAttribute("company");

		System.out.println("\nCurrent element:");
		System.out.println("supercarscompany: " + company);
		for (int i = 0; i < db; i++) {
			NodeList nodeList = element.getElementsByTagName("carname");
			Node nNode = nodeList.item(i);
			Element carElement = (Element) nNode;

			String name = element.getElementsByTagName("carname").item(i).getTextContent();
			String type = carElement.getAttribute("type");
			System.out.println("car name: " + name + "\ncar type: " + type);
		}
	}

}
