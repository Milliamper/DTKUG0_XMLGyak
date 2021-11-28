package hu.domparse.dtkug0;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryDTKUG0 {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		File xmlFile = new File("XMLdtkug0.xml"); // XML fajl bekerese
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // olvasas lehetove tetele
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		// XML dokumentum atadasa es egy DOM Document objektum letrehozasa
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		System.out.println("_____________________________");
		System.out.println("Cegjegyzek listazasa: ");
		LoadCegjegyzekQuery(doc);
	}

	// van ertekellenorzes, vagyis ha rossz szamot adunk meg, vagy esetleg nem
	// szamot, akkor hibat kapunk
	private static int readCegSorszam() {
		Scanner input = new Scanner(System.in);
		int cegSorszam;
		System.out.print("Irja be annak a cegnek a sorszamat, amelynek az adatait meg szeretne jeleniteni: ");
		while (!input.hasNextInt()) {
			System.out.println("Nem szamott adott meg!\nAdjon meg uj sorszamot:");
			input.next();
		}
		cegSorszam = input.nextInt();
		return cegSorszam;
	}

	public static void LoadCegjegyzekQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("cegjegyzek"); // Cegjegyzek elemeinek listazasa
		int ceg = 0; // ceg sorszamainak letrehozott valtozo
		Element element = null;
		Node nNode = null;
		int countCegekSzama = 0; // Cegek megszamolasa, hogy ellenorizni lehessen hany ceg van osszesen -> csak a
									// megfelelo sorszamot fogadjuk el
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			String bejegyzettCeg = element.getElementsByTagName("bejegyzett_cegek").item(0).getTextContent();
			System.out.println((i + 1) + ". ceg: " + bejegyzettCeg);
			countCegekSzama++;
		}
		// Ceg kivalasztasa, sorszam ellenorzott beolvasasa
		ceg = readCegSorszam();
		if (ceg < 1 || ceg > countCegekSzama) {
			System.out.println("Nem megfelelo sorszam!");
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				switch (ceg) {
				case 1: {
					LoadCegQuery(doc, "1");
					break;
				}
				case 2: {
					LoadCegQuery(doc, "2");
					break;
				}
				case 3: {
					LoadCegQuery(doc, "3");
					break;
				}

				}
				break;
			}
		}
	}

	// Kivalasztott ceg adatai
	public static void LoadCegQuery(Document doc, String id) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("ceg");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Element element = (Element) nNode;
			String cegid = element.getAttribute("cegid");
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (id.equals(cegid)) {
					System.out.println("\nA valasztott ceg adatai: ");
					DOMReadDTKUG0.ReadCegById(doc, id);
				}
			}
		}
	}
}
