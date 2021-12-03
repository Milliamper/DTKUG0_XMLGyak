package json;

import org.json.simple.JSONArray;

public class ListDTKUG0 {

	public static void main(String[] args) {
		JSONArray list = new JSONArray();

		list.add("BL");
		list.add(new Double(1_000_000));
		list.add(21);

		System.out.println(list);
	}

}
