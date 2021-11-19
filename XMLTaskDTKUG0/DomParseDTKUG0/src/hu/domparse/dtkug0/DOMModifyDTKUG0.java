package hu.domparse.dtkug0;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyDTKUG0 {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		File xmlFile = new File("XMLdtkug0.xml"); // XML fajl bekerese
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // olvasas lehetove tetele
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		System.out.println("XML fajl modositasa");
		System.out.println("Adja meg mit szeretne modositani: ");
		System.out.println("1. -> Muhely modositasa");
		System.out.println("2. -> Raktar modositasa");
		System.out.println("3. -> Auto modositasa");
		System.out.println("4. -> Tulajdonos modositasa");
		Modify(doc);
	}

	public static void ModifyXML(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("XMLdtkug0.xml"));
		transformer.transform(source, result);
	}

	public static void Modify(Document doc) throws TransformerException {
		int muhelyekSzama = doc.getElementsByTagName("muhely").getLength();
		int raktarakSzama = doc.getElementsByTagName("raktar").getLength();
		int autokSzama = doc.getElementsByTagName("auto").getLength();
		int tulajdonosokSzama = doc.getElementsByTagName("tulajdonos").getLength();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Adja meg a sorszamot: ");
		int readCategory = scanner.nextInt();
		switch (readCategory) {
		case 1:
			ModifyMuhely(doc, muhelyekSzama);
			break;
		case 2:
			ModifyRaktar(doc, raktarakSzama);
			break;
		case 3:
			ModifyAuto(doc, autokSzama);
			break;
		case 4:
			ModifyTulajdonos(doc, tulajdonosokSzama);
			break;
		}
	}

	public static String ReadId() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nAdja meg az ID-t :");
		String id = scanner.nextLine();
		return id;

	}

	private static void ModifyMuhely(Document doc, int muhelyekszama) throws TransformerException {

		System.out.println(muhelyekszama + " db muhely erhete el. Melyik muhely adatait szeretne modositani?");
		for (int i = 1; i < muhelyekszama + 1; i++) {
			System.out.println();
			System.out.println(i + ". muhely:");
			DOMReadDTKUG0.ReadMuhelyByIdWithoutCeg(doc, String.valueOf(i));
			System.out.print("__________________________\n");
		}
		String id = ReadId();
		// Adat bekerese
		Scanner scan = new Scanner(System.in);
		System.out.print("Javitando auto: ");
		String masikJavitandoAuto = scan.nextLine();

		NodeList nodeList = doc.getElementsByTagName("muhely");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("mid");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("javitando_auto").item(0);
					node1.setTextContent(masikJavitandoAuto);

					System.out.println("Sikeres modositas!");
				}
			}
		}
		ModifyXML(doc); // XML letrehozasa
	}

	private static void ModifyRaktar(Document doc, int raktarakszama) throws TransformerException {

		System.out.println(raktarakszama + " db raktar erheto el. Melyik raktar adatait szeretne modositani?");
		for (int i = 1; i < raktarakszama + 1; i++) {
			System.out.println();
			System.out.println(i + ". raktar:");
			DOMReadDTKUG0.ReadRaktarById(doc, String.valueOf(i));
			System.out.print("__________________\n");
		}
		String id = ReadId();
		// Az uj adat bekerese
		Scanner scanner = new Scanner(System.in);
		System.out.print("Alkatresz : ");
		String masikAlkatresz = scanner.nextLine();

		NodeList nodeList = doc.getElementsByTagName("raktar");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("cikkszam");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("alkatreszek").item(0);
					node1.setTextContent(masikAlkatresz);

					System.out.println("Sikeres modositas");
				}
			}
		}
		ModifyXML(doc);
	}

	private static void ModifyAuto(Document doc, int autokszama) throws TransformerException {

		System.out.println(autokszama + " db auto erheto el. Melyik auto adatait szeretne modositani?");

		DOMReadDTKUG0.ReadAutoWithoutTulajdonos(doc);
		System.out.print("__________________\n");

		String id = ReadId();
		// Adat bekerese
		Scanner scanner = new Scanner(System.in);
		System.out.print("Marka: ");
		String masikMarka = scanner.nextLine();
		System.out.print("Tipus: ");
		String masikTipus = scanner.nextLine();
		System.out.print("Km ora allasa: ");
		String masikKmOra = scanner.nextLine();

		NodeList nodeList = doc.getElementsByTagName("auto");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("ugyfelid");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("marka").item(0);
					node1.setTextContent(masikMarka);
					Node node2 = element.getElementsByTagName("tipus").item(0);
					node2.setTextContent(masikTipus);
					Node node3 = element.getElementsByTagName("km_ora_allasa").item(0);
					node3.setTextContent(masikKmOra);

					System.out.println("Sikeres modositas");
				}
			}
		}
		ModifyXML(doc);
	}

	private static void ModifyTulajdonos(Document doc, int tulajokszama) throws TransformerException {

		System.out.println(tulajokszama + " db tulajdonos erheto el. Melyik tulajdonos adatait szeretne modositani?");
		for (int i = 1; i < tulajokszama + 1; i++) {
			System.out.println();
			System.out.println(i + ". tulajdonos:");
			DOMReadDTKUG0.ReadTulajdonosById(doc, String.valueOf(i));
			System.out.print("__________________\n");
		}
		String id = ReadId();
		// Bekerjuk az uj adatot
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nev: ");
		String masikNev = scanner.nextLine();
		System.out.print("Cim: ");
		String masikCim = scanner.nextLine();
		System.out.print("Szuletesi ido: ");
		String masikSzulIdo = scanner.nextLine();

		NodeList nodeList = doc.getElementsByTagName("tulajdonos");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("ugyfelid");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("nev").item(0);
					node1.setTextContent(masikNev);
					Node node2 = element.getElementsByTagName("cim").item(0);
					node2.setTextContent(masikCim);
					Node node3 = element.getElementsByTagName("szulido").item(0);
					node3.setTextContent(masikSzulIdo);

					System.out.println("Sikeres modositas");
				}
			}
		}
		ModifyXML(doc); // XML letrehozasa
	}

}
