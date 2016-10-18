import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class SaveAndRestore {
	
	public int save(ArrayList widgetInfo){
		Gson gson = new Gson();
		String json = gson.toJson(widgetInfo);
		File file = new File("savedInfo.json");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			return 1;
		}
		System.out.println("Saved json object: " + json);
		return 0;
	}

	public int restore(ArrayList restoredArray){
		String json = "";
		try {
			json = new Scanner(new File("savedInfo.json")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 1;
		}
		Gson gson = new Gson();
		restoredArray = (ArrayList) gson.fromJson(json, ArrayList.class);
		System.out.println("ArrayList restored from save: " + restoredArray);
		
		return 0;
	}
	
}
