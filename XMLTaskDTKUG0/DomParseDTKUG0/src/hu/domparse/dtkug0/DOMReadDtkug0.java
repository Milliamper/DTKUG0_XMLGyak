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

public class DOMReadDTKUG0 {

	public static void main(String[] args) {

		try {
			
			// XML fajl bekerese
			File xmlFile = new File("XMLdtkug0.xml");
			
			// Objektumfak eloallitasa a dokumentumbol
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder dBuilder = factory.newDocumentBuilder();

			// XML dokumentum atadasa es egy DOM Document objektum letrehozasa
			Document doc = dBuilder.parse(xmlFile); 
			
			// szomszedos es ures text node-ok eltavolitasara szolgal
			doc.getDocumentElement().normalize();
			
			Read(doc);
			ReadCegjegyzek(doc);
			ReadAuto(doc);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static void ReadCegjegyzek(Document doc) {

		// cegjegyzek tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("cegjegyzek"); 

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke
			// attributomok lekérése majd a definiált metódusok meghívása
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String cegid = element.getAttribute("cegid");
				String cegjegyzekid = element.getAttribute("cegjegyzekid");

				String bejegyzettCegek = element.getElementsByTagName("bejegyzett_cegek").item(0).getTextContent();

				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("Ceg azonosito : " + cegid);
				System.out.println("Cegjegyzek ID : " + cegjegyzekid);
				System.out.println("Bejegyzett cegek - " + (i + 1) + ".ceg: " + bejegyzettCegek);

			}
		}
	}

	public static void ReadCegById(Document doc, String id) {
		// ceg tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("ceg");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String tel = element.getElementsByTagName("telefonszam").item(0).getTextContent();

					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Ceg azonosito : " + cegid);
					System.out.println("Cegnev : " + nev);
					System.out.println("Szekhely : " + cim + "\nTelefonszam: " + tel);

				}
			}
		}
	}

	public static void ReadMuhelyById(Document doc, String id) {
		
		// muhely tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("muhely");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String mid = element.getAttribute("mid");
					String javitandoAuto = element.getElementsByTagName("javitando_auto").item(0).getTextContent();

					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Ceg azonosito : " + cegid);
					System.out.println("MuhelyID : " + mid);
					System.out.println("Javitando auto : " + javitandoAuto);

					ReadCegById(doc, cegid);

				}
			}
		}
	}

	public static void ReadMuhelyByIdWithoutCeg(Document doc, String id) {
		
		// muhely tag-el rendelkezo elemek lekerese, a ceg olvasasa nelkul
		NodeList nList = doc.getElementsByTagName("muhely");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String mid = element.getAttribute("mid");
					String javitandoAuto = element.getElementsByTagName("javitando_auto").item(0).getTextContent();

					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Ceg azonosito : " + cegid);
					System.out.println("MuhelyID : " + mid);
					System.out.println("Javitando auto : " + javitandoAuto);

				}
			}
		}
	}

	public static void ReadRaktarById(Document doc, String id) {
		
		// raktar tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("raktar");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; //konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("cikkszam").equals(id)) {
					String cikkszam = element.getAttribute("cikkszam");
					String alkatreszek = element.getElementsByTagName("alkatreszek").item(0).getTextContent();

					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cikkszam : " + cikkszam);
					System.out.println("Alkatresz : " + alkatreszek);

				}
			}
		}
	}

	public static void ReadAuto(Document doc) {
		
		// auto tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("auto");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				String ugyfelid = element.getAttribute("ugyfelid");
				String alvazszam = element.getAttribute("alvazszam");
				String marka = element.getElementsByTagName("marka").item(0).getTextContent();
				String tipus = element.getElementsByTagName("tipus").item(0).getTextContent();
				String km = element.getElementsByTagName("km_ora_allasa").item(0).getTextContent();

				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("UgyfelID : " + ugyfelid);
				System.out.println("Alvazszam : " + alvazszam);
				System.out.println("Marka : " + marka);
				System.out.println("Tipus : " + tipus);
				System.out.println("Km ora allasa : " + km);

				ReadTulajdonosById(doc, ugyfelid);

			}
		}
	}

	public static void ReadAutoWithoutTulajdonos(Document doc) {
		
		// auto tag-el rendelkezo elemek lekerese, tulajdonos nelkul
		NodeList nList = doc.getElementsByTagName("auto"); 
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				String ugyfelid = element.getAttribute("ugyfelid");
				String alvazszam = element.getAttribute("alvazszam");
				String marka = element.getElementsByTagName("marka").item(0).getTextContent();
				String tipus = element.getElementsByTagName("tipus").item(0).getTextContent();
				String km = element.getElementsByTagName("km_ora_allasa").item(0).getTextContent();

				System.out.println(i + 1 + ". aut� : ");
				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("UgyfelID : " + ugyfelid);
				System.out.println("Alvazszam : " + alvazszam);
				System.out.println("Marka : " + marka);
				System.out.println("Tipus : " + tipus);
				System.out.println("Km ora allasa : " + km);
			}
		}

	}

	public static void ReadTulajdonosById(Document doc, String id) {
		
		// tulajdonos tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("tulajdonos"); 
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("ugyfelid").equals(id)) {
					String ugyfelid = element.getAttribute("ugyfelid");
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String szulido = element.getElementsByTagName("szulido").item(0).getTextContent();

					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("UgyfelID : " + ugyfelid);
					System.out.println("Nev : " + nev);
					System.out.println("Cim : " + cim);
					System.out.println("Szuletesi ido : " + szulido);

				}
			}
		}
	}

	public static void Read(Document doc) {
		
		// futar tag-el rendelkezo elemek lekerese
		NodeList nList = doc.getElementsByTagName("futar");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktualis elemeinek lekerese
			Element element = (Element) node; // konvertalas elementekke

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				String mid = element.getAttribute("mid");
				String cikkszam = element.getAttribute("cikkszam");
				String varhatoErkezes = element.getElementsByTagName("varhato_erk").item(0).getTextContent();

				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("Cikkszam : " + cikkszam);
				System.out.println("MuhelyID : " + mid);
				System.out.println("Varhato erkezes : " + varhatoErkezes);

				ReadRaktarById(doc, cikkszam);
				ReadMuhelyById(doc, mid);

			}
		}
	}

}

