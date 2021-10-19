package SaxDTKUG01019;

import org.xml.sax.Attributes;

public class SaxHandler {

	private int indent = 0;

	private String formatAttributes(Attributes attributes) {
		int attrLength = attributes.getLength();
		if (attrLength == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder(", {");
		for (int i = 0; i < attrLength; i++) {
			sb.append(attributes.getLocalName(i) + "i" + attributes.getValue(i));
			if (i < attrLength - 1) {
				sb.append(" ,");
			}
		}
		sb.append("}");
		return sb.toString();

	}

	private void indent() {
		for (int i = 0; i < indent; i++) {
			System.out.println(" ");
		}
	}

}
