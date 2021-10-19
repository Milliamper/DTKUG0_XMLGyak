package SaxDTKUG01019;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler{

	private int indent = 0;

	private String formatAttributes(Attributes attributes) {
		int attrLength = attributes.getLength();
		if (attrLength == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder(", {");
		for (int i = 0; i < attrLength; i++) {
			sb.append(attributes.getLocalName(i) + ":" + attributes.getValue(i));
			if (i < attrLength - 1) {
				sb.append(" ,");
			}
		}
		sb.append("}");
		return sb.toString();

	}

	private void indent() {
		for (int i = 0; i < indent; i++) {
			System.out.print("");
		}
	}
	
	//Esemény kezelõ metódusok létrehozása, startElement metódust újraimplementáljuk
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		indent++;
		indent();
		System.out.println(qName + formatAttributes(attributes)+ " start");
	}
	
	public void endElement(String uri, String localName, String qName) {
		indent();
		indent--;
		System.out.println(qName + " end");
	}
	
	public void characters (char ch[], int start, int length) {
		String chars = new String(ch, start, length).trim();
		if (!chars.isEmpty()) {
			indent++;
			indent();
			indent--;
			System.out.println(chars);
		}
	}

}
