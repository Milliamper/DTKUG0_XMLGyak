package SaxDTKUG01019;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class SaxDTKUG0 {
	
	private static final String FILENAME = "file:///G:/Egyéb számítógépek/DesktopPC/Dokumentumok/Miskolci Egyetem/2021-22-1/Adatkezelés XML-ben/DTKUG0_XMLGyak/DTKUG0_1019/szemelyekDTKUG0.xml";

	public static void main(String[] args) {
		
		
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			
			saxParser.parse(FILENAME, handler);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		

	}

}
