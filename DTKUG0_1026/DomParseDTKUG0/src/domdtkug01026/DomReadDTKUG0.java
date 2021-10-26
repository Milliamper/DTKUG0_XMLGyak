package domdtkug01026;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadDTKUG0 {


	public static void main(String[] args) {

		try {
			File xmlFile = new File("usersDTKUG0.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			Read(doc);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static void Read(Document doc) {

		NodeList nList = doc.getElementsByTagName("user");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			Element element = (Element) node;

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String id = element.getAttribute("id");
				String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
				String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
				String profession = element.getElementsByTagName("profession").item(0).getTextContent();

				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("Id : " + id);
				System.out.println("First Name : " + firstname);
				System.out.println("Last Name : " + lastname);
				System.out.println("Profession : " + profession);
			}
		}
	}

}
