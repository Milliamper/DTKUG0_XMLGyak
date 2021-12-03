package json;

import org.json.simple.JSONObject;

public class ObjectDTKUG0 {

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Fizetes", new Double(1000000));
		jsonObject.put("Nev", "BLaszlo");
		jsonObject.put("Kor", 21);
		System.out.println(jsonObject);
	}

}
