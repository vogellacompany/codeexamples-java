package de.vogella.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTest {
	public static void main(String[] args) throws IOException, JSONException {
		JsonTest jsonTest = new JsonTest();
		jsonTest.write();
	}
	public void read()throws IOException, JSONException {
		BufferedReader reader = new BufferedReader(new FileReader("json.txt"));
		String returnValue = "";
		String line = "";
		while ((line = reader.readLine()) != null) {
			returnValue += line + "\n";
		}
		JSONObject jObject = new JSONObject(returnValue);
		JSONObject menuObject = jObject.getJSONObject("menu");
		String attributeId = menuObject.getString("id");
		String attributeValue = menuObject.getString("value");
		JSONObject popupObject = menuObject.getJSONObject("popup");
		JSONArray menuitemArray = popupObject.getJSONArray("menuitem");
		System.out.println(returnValue);
		for (int i = 0; i < 3; i++) {
			System.out.println(menuitemArray.getJSONObject(i)
					.getString("value").toString());
			System.out.println(menuitemArray.getJSONObject(i)
					.getString("onclick").toString());
		}

	}
	
	public void write () throws JSONException {
		JSONObject master = new JSONObject();
		master.put("name", "testing");
		master.put("identifier", "testing2");
		JSONArray jsonarray = new JSONArray();
		master.put("items", jsonarray);
		
		JSONObject object = new JSONObject();
		object.put("name", "de.vogella.json");
		object.put("type", "folder");
		jsonarray.put(object);
		JSONArray subArray = new JSONArray();
		object.put("children", subArray);
		JSONObject file = new JSONObject();
		file.put("name", "Model.java");
		file.put("type", "file");
		file.put("path", "./src/model/Model.html");
		subArray.put(file);
		
		System.out.println(master.toString(2));
	}
}
