package SaxDTKUG01019;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XsdValidation {
	
	public static final String XMLPATH_LAPTOP = "G:/Egyéb számítógépek/DesktopPC/Dokumentumok/Miskolci Egyetem/2021-22-1/Adatkezelés XML-ben/DTKUG0_XMLGyak/DTKUG0_1019/szemelyekDTKUG0.xml";
	public static final String XSDPATH_LAPTOP = "G:/Egyéb számítógépek/DesktopPC/Dokumentumok/Miskolci Egyetem/2021-22-1/Adatkezelés XML-ben/DTKUG0_XMLGyak/DTKUG0_1019/szemelyekDTKUG0.xsd";
	public static final String XMLPATH_PC = "D:\\Dokumentumok\\Miskolci Egyetem\\2021-22-1\\Adatkezelés XML-ben\\DTKUG0_XMLGyak\\DTKUG0_1019\\szemelyekDTKUG0.xml";
	public static final String XSDPATH_PC = "D:\\Dokumentumok\\Miskolci Egyetem\\2021-22-1\\Adatkezelés XML-ben\\DTKUG0_XMLGyak\\DTKUG0_1019\\szemelyekDTKUG0.xsd";
	
	
	
	public static void main(String[] args) {

		File schemaFile = new File(XSDPATH_PC); // etc.
		Source xmlFile = new StreamSource(new File(XMLPATH_PC));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println("XSD Validation successful");
		} catch (SAXException e) {
			System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
		} catch (IOException e) {
		}

	}

}
