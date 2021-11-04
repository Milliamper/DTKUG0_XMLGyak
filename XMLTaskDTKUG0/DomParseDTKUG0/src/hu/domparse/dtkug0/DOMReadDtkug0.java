package hu.domparse.dtkug0;

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

public class DOMReadDtkug0 {

	public static void main(String[] args) {

		try {
			File xmlFile = new File("XMLdtkug0.xml"); // XML fájl bekérése
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Objektumfák elõállítása XML
																					// dokumentumból
			DocumentBuilder dBuilder = factory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile); // XML dokumentum átadása és egy DOM Document objektum létrehozása
			doc.getDocumentElement().normalize(); // szomszédos és üres text node-ok eltávolítására szolgál
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

		NodeList nList = doc.getElementsByTagName("cegjegyzek"); // cegjegyzek tag-el rendelkezõ elemek lekérése

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké

			// attribútumok lekérése majd a definált metódusok meghívása
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String cegid = element.getAttribute("cegid");
				String cegjegyzekid = element.getAttribute("cegjegyzekid");
				String ugyfelid = element.getAttribute("ugyfelid");
				
				String bejegyzettCegek = element.getElementsByTagName("bejegyzett_cegek").item(0).getTextContent();

				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("Cég azonosító : " + cegid);
				System.out.println("Cégjegyzék ID : " + cegjegyzekid);
				System.out.println("Bejegyzett cégek - " + (i + 1) + ".cég: " + bejegyzettCegek);
				
				ReadCegById(doc, cegid);
				ReadAutoById(doc, ugyfelid);
			}
		}
	}
	
	public static void ReadCegById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("ceg"); // ceg tag-el rendelkezõ elemek lekérése
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String tel = element.getElementsByTagName("telefonszam").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cég azonosító : " + cegid);
					System.out.println("Cégnév : " + nev);
					System.out.println("Székhely : " + cim +"\nTelefonszám: " + tel);
					
					ReadMuhelyById(doc, cegid);
					
				}
			}
		}
	}
	
	public static void ReadMuhelyById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("muhely"); // muhely tag-el rendelkezõ elemek lekérése
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String mid = element.getAttribute("mid");
					String javitandoAuto = element.getElementsByTagName("javitando_auto").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cég azonosító : " + cegid);
					System.out.println("MûhelyID : " + mid);
					System.out.println("Javítandó autó : " + javitandoAuto);
					
				}
			}
		}
	}
	
	public static void ReadRaktarById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("raktar"); // muhely tag-el rendelkezõ elemek lekérése
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("raktar").equals(id)) {
					String cikkszam = element.getAttribute("cikkszam");
					String alkatreszek = element.getElementsByTagName("alkatreszek").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cikkszám : " + cikkszam);
					System.out.println("Alkatrész : " + alkatreszek);
					
				}
			}
		}
	}
	
	public static void ReadAutoById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("auto"); // muhely tag-el rendelkezõ elemek lekérése
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("auto").equals(id)) {
					String ugyfelid = element.getAttribute("ugyfelid");
					String alvazszam = element.getAttribute("alvazszam");
					String marka = element.getElementsByTagName("marka").item(0).getTextContent();
					String tipus = element.getElementsByTagName("tipus").item(0).getTextContent();
					String km = element.getElementsByTagName("km_ora_allasa").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("ÜgyfélID : " + ugyfelid);
					System.out.println("Alvázszám : " + alvazszam);
					System.out.println("Márka : " + marka);
					System.out.println("Típus : " + tipus);
					System.out.println("Km óra állása : " + km);
					
				}
			}
		}
	}
	
	public static void ReadTulajdonosById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("tulajdonos"); // muhely tag-el rendelkezõ elemek lekérése
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("tulajdonos").equals(id)) {
					String ugyfelid = element.getAttribute("ugyfelid");
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String szulido = element.getElementsByTagName("szulido").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("ÜgyfélID : " + ugyfelid);
					System.out.println("Név : " + nev);
					System.out.println("Cím : " + cim);
					System.out.println("Születési idõ : " + szulido);
					
				}
			}
		}
	}
	
	public static void ReadFutarById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("futar"); // muhely tag-el rendelkezõ elemek lekérése
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktuális elemeinek lekérése
			Element element = (Element) node; // konvertálás elementekké
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("futar").equals(id)) {
					String mid = element.getAttribute("mid");
					String cikkszam = element.getAttribute("cikkszam");
					String varhatoErkezes = element.getElementsByTagName("varhato_erk").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cikkszám : " + cikkszam);
					System.out.println("MûhelyID : " + mid);
					System.out.println("MûhelyID : " + varhatoErkezes);
					
				}
			}
		}
	}

}
