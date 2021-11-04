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
			File xmlFile = new File("XMLdtkug0.xml"); // XML f�jl bek�r�se
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Objektumf�k el��ll�t�sa XML
																					// dokumentumb�l
			DocumentBuilder dBuilder = factory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile); // XML dokumentum �tad�sa �s egy DOM Document objektum l�trehoz�sa
			doc.getDocumentElement().normalize(); // szomsz�dos �s �res text node-ok elt�vol�t�s�ra szolg�l
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

		NodeList nList = doc.getElementsByTagName("cegjegyzek"); // cegjegyzek tag-el rendelkez� elemek lek�r�se

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�

			// attrib�tumok lek�r�se majd a defin�lt met�dusok megh�v�sa
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String cegid = element.getAttribute("cegid");
				String cegjegyzekid = element.getAttribute("cegjegyzekid");
				String ugyfelid = element.getAttribute("ugyfelid");
				
				String bejegyzettCegek = element.getElementsByTagName("bejegyzett_cegek").item(0).getTextContent();

				System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
				System.out.println("------");
				System.out.println("Current Element :" + node.getNodeName());
				System.out.println("C�g azonos�t� : " + cegid);
				System.out.println("C�gjegyz�k ID : " + cegjegyzekid);
				System.out.println("Bejegyzett c�gek - " + (i + 1) + ".c�g: " + bejegyzettCegek);
				
				ReadCegById(doc, cegid);
				ReadAutoById(doc, ugyfelid);
			}
		}
	}
	
	public static void ReadCegById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("ceg"); // ceg tag-el rendelkez� elemek lek�r�se
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String tel = element.getElementsByTagName("telefonszam").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("C�g azonos�t� : " + cegid);
					System.out.println("C�gn�v : " + nev);
					System.out.println("Sz�khely : " + cim +"\nTelefonsz�m: " + tel);
					
					ReadMuhelyById(doc, cegid);
					
				}
			}
		}
	}
	
	public static void ReadMuhelyById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("muhely"); // muhely tag-el rendelkez� elemek lek�r�se
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("cegid").equals(id)) {
					String cegid = element.getAttribute("cegid");
					String mid = element.getAttribute("mid");
					String javitandoAuto = element.getElementsByTagName("javitando_auto").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("C�g azonos�t� : " + cegid);
					System.out.println("M�helyID : " + mid);
					System.out.println("Jav�tand� aut� : " + javitandoAuto);
					
				}
			}
		}
	}
	
	public static void ReadRaktarById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("raktar"); // muhely tag-el rendelkez� elemek lek�r�se
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("raktar").equals(id)) {
					String cikkszam = element.getAttribute("cikkszam");
					String alkatreszek = element.getElementsByTagName("alkatreszek").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cikksz�m : " + cikkszam);
					System.out.println("Alkatr�sz : " + alkatreszek);
					
				}
			}
		}
	}
	
	public static void ReadAutoById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("auto"); // muhely tag-el rendelkez� elemek lek�r�se
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�
			
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
					System.out.println("�gyf�lID : " + ugyfelid);
					System.out.println("Alv�zsz�m : " + alvazszam);
					System.out.println("M�rka : " + marka);
					System.out.println("T�pus : " + tipus);
					System.out.println("Km �ra �ll�sa : " + km);
					
				}
			}
		}
	}
	
	public static void ReadTulajdonosById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("tulajdonos"); // muhely tag-el rendelkez� elemek lek�r�se
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("tulajdonos").equals(id)) {
					String ugyfelid = element.getAttribute("ugyfelid");
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String szulido = element.getElementsByTagName("szulido").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("�gyf�lID : " + ugyfelid);
					System.out.println("N�v : " + nev);
					System.out.println("C�m : " + cim);
					System.out.println("Sz�let�si id� : " + szulido);
					
				}
			}
		}
	}
	
	public static void ReadFutarById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("futar"); // muhely tag-el rendelkez� elemek lek�r�se
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i); // lista aktu�lis elemeinek lek�r�se
			Element element = (Element) node; // konvert�l�s elementekk�
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(element.getAttribute("futar").equals(id)) {
					String mid = element.getAttribute("mid");
					String cikkszam = element.getAttribute("cikkszam");
					String varhatoErkezes = element.getElementsByTagName("varhato_erk").item(0).getTextContent();
					
					System.out.println("\nRoot Element :" + doc.getDocumentElement().getNodeName());
					System.out.println("------");
					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Cikksz�m : " + cikkszam);
					System.out.println("M�helyID : " + mid);
					System.out.println("M�helyID : " + varhatoErkezes);
					
				}
			}
		}
	}

}
