package de.vogella.json.directoryreader;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DirectoryToJson {
	private JSONObject main = new JSONObject();

	public static void main(String[] args) {
		DirectoryToJson test = new DirectoryToJson();
		test.write();
	}

	public void write() {

		try {
			main.put("label", "name");
			main.put("identifier", "name");
			JSONArray items = new JSONArray();
			main.put("items", items);

			File startFile = new File("C:/temp/");
			
			processDirectory(items, startFile);

			System.out.println(main.toString(2));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void processDirectory(JSONArray array, File startFile)
			throws JSONException {
		File[] list = startFile.listFiles();
		for (File file : list) {
			JSONObject fileJson = new JSONObject();
			String type = null;
			fileJson.put("name", file.getName());
			if (file.isDirectory()) {
				type = "folder";
			} else {
				type = "file";
				fileJson.put("path", file.getPath());
			}

			fileJson.put("type", type);
			array.put(fileJson);
			if (file.isDirectory()) {
				JSONArray newArray = new JSONArray();
				fileJson.put("children", newArray);
				processDirectory(newArray, file);
			}

		}

	}
}
