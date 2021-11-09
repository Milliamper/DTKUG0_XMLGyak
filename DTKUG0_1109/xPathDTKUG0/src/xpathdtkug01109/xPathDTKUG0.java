package xpathdtkug01109;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathDTKUG0 {

	public static void main(String[] args) {

		try {
			File xmlFile = new File("studentDTKUG0.xml"); // XML f�jl bek�r�se
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Objektumf�k el��ll�t�sa XML
																					// dokumentumb�l
			DocumentBuilder dBuilder = factory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile); // XML dokumentum �tad�sa �s egy DOM Document objektum l�trehoz�sa
			doc.getDocumentElement().normalize(); // szomsz�dos �s �res text node-ok elt�vol�t�s�ra szolg�l

			// xPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();

			// meg kell adni az el�r�si �t kifejez�st �s a csom�pont list�t
			
			/* 1) */ String expression = "/class/student";
			/* 2) */ String expression2 = "//student[@id = '1']";
			/* 3) */ String expression3 = "//student";
			/* 4) */ String expression4 = "/class/student[2]";
			/* 5) */ String expression5 = "/class/student[last()]";
			/* 6) */ String expression6 = "/class/student[last()-1]";
			/* 7) */ String expression7 = "/class/student[position()<3]";
			/* 8) */ String expression8 = "/class/*";
			/* 9) */ String expression9 = "/class/student/@*";
			/* 10) */ String expression10 = "/*/*";
			/* 11) */ String expression11 = "/class/student[kor>20]";
			

			NodeList nodeList = (NodeList) xPath.compile(expression9).evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().equals("student")) {
					Element eElement = (Element) nNode;
					System.out.println("Student ID: " + eElement.getAttribute("id"));
					System.out.println(
							"Keresztn�v : " + eElement.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println(
							"Vezet�kn�v : " + eElement.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out
							.println("Becen�v : " + eElement.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor : " + eElement.getElementsByTagName("kor").item(0).getTextContent());
				}

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
