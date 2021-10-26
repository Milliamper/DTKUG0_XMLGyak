package domdtkug01026;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DomWriteDTKUG0 {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		// dokumentum k�sz�t� gy�rb�l egy new document builder j�n l�tre
		Document doc = dBuilder.newDocument();
		// dokumentum k�sz�t�b�l �j dokumentumot hozunk l�tre
		Element root = doc.createElementNS("domdtkug0", "users");
		doc.appendChild(root);
		// l�trehozunk egy gy�k�relement, �s hozz�adjuk a dokumentumhoz
		
		root.appendChild(createUser(doc, "1", "Fanni", "Kiss", "programmer"));
		root.appendChild(createUser(doc, "2", "�d�m", "T�th", "writer"));
		root.appendChild(createUser(doc, "3", "Tibor", "Nagy", "teacher"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("users1DTKUG0.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		//�rni fogunk egy konzolba �s egy f�jlba
		//StreamResult transzform�ci�s eredm�ny birtokosa
		
		transf.transform(source, console);
		transf.transform(source, file);
		
		
		
		
		
		
		
		
	}
	
	private static void CreateUSer(Document doc, String id, String FirstName, String LastName, String profession) {
		Element user = doc.createElement("user");
		user.setAttribute("id", id);

		user.appendChild(createUserElement(doc, "firstname", FirstName));
		user.appendChild(createUserElement(doc, "lastname", LastName));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	}
	
	public static CreateUserElement(Document doc, String firstname, )

}
