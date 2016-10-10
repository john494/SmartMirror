import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.google.gson.Gson;


public class IndexSaveRestore {
	
	static String writeLines;
	static ArrayList startEnd;
	static ArrayList finalWrite;
	
	
	public void start(){
		//readIndex();
		//saveIndexInfo();
		restoreIndex();
	}
	
	public void readIndex(){
		startEnd = new ArrayList();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("index.html"));
			String line;
			int lineCount = 0;
			while((line = reader.readLine()) != null){
				lineCount++;
				if(line.contains("id=")){
					if(line.contains("N1") || line.contains("N2") || line.contains("L1") || line.contains("R1") || line.contains("L2") || line.contains("R2") || line.contains("L3") || line.contains("R3")){
						//System.out.println("FOUND IT");
						startEnd.add(Integer.toString(lineCount+1));
						String lineIn;
						//System.out.println(line);
						int openDiv = 1;
						//int closeDiv = 0;
						int newLineCount = lineCount+1;
						BufferedReader reader2 = new BufferedReader(new FileReader("index.html"));
						int count = 1;
						while((lineIn = reader2.readLine()) != null){
							
							//break;
							//if(newLineCount > 35){ break; }
							if(count < newLineCount){ count++; continue; }
							//System.out.println(lineIn);
							
							if(openDiv == 0){ 
								startEnd.add(Integer.toString(newLineCount-2));
								break; 
							}
							if(lineIn.contains("</div")){ openDiv--; }
							if(lineIn.contains("<div")){ openDiv++; }
							
							count++;
							newLineCount++;
							//System.out.println("test");
						}
						reader2.close();
					}
				}
			}
			reader.close();
			//System.out.println(startEnd);
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveIndexInfo(){
		try {
			//BufferedReader reader = new BufferedReader(new FileReader("index.html"));
			int iterator = 0;
			while(iterator < startEnd.size()){
				//System.out.println("test");
				String startLineString = (String) startEnd.get(iterator);
				int startLine = Integer.parseInt(startLineString);
				String endLineString = (String) startEnd.get(iterator+1);
				int endLine = Integer.parseInt(endLineString);
				//System.out.println(startLineString + " : " + startLine);
				//System.out.println(endLineString + " : " + endLine);
				int count = 1;
				String line;
				BufferedReader reader2 = new BufferedReader(new FileReader("index.html"));
				while((line = reader2.readLine()) != null){
					//if(count > endLine){ break; }
					String temp = "";
					if(count >= startLine && count <= endLine){
						if(!line.endsWith("\n")){
							temp = line + "\n";
						}
						else { temp = line; }
						writeLines = writeLines + temp;
						//System.out.println(temp);
					}
					count++;
				}
				System.out.println(writeLines);
				//finalWrite.add(writeLines);
				writeSave();
				writeLines = "";
				iterator += 2;
				reader2.close();
			}
			//reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		Gson gson = new Gson();
		String json = gson.toJson(finalWrite);
		File file = new File("indexInfo.json");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			//return 1;
		}
		System.out.println("Saved json object: " + json);
		//return 0;
		*/
	}
	
	public void readBlankIndex(){
		startEnd = new ArrayList();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("blankIndex.html"));
			String line;
			int lineCount = 0;
			while((line = reader.readLine()) != null){
				lineCount++;
				if(line.contains("id=")){
					if(line.contains("N1") || line.contains("N2") || line.contains("L1") || line.contains("R1") || line.contains("L2") || line.contains("R2") || line.contains("L3") || line.contains("R3")){
						//System.out.println("FOUND IT");
						startEnd.add(Integer.toString(lineCount+1));
						/*
						String lineIn;
						//System.out.println(line);
						int openDiv = 1;
						//int closeDiv = 0;
						int newLineCount = lineCount+1;
						BufferedReader reader2 = new BufferedReader(new FileReader("index.html"));
						int count = 1;
						while((lineIn = reader2.readLine()) != null){
							
							//break;
							//if(newLineCount > 35){ break; }
							if(count < newLineCount){ count++; continue; }
							//System.out.println(lineIn);
							
							if(openDiv == 0){ 
								startEnd.add(Integer.toString(newLineCount-2));
								break; 
							}
							if(lineIn.contains("</div")){ openDiv--; }
							if(lineIn.contains("<div")){ openDiv++; }
							
							count++;
							newLineCount++;
							//System.out.println("test");
						}
						reader2.close();
						*/
					}
				}
			}
			reader.close();
			System.out.println(startEnd);
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeSave(){
		File file = new File("indexInfo.txt");
		//FileWriter writer = null;
		try {
			/*
			writer = new FileWriter(file);
			writer.write(writeLines + "\n\n");
			writer.close();
			*/
			FileWriter fw = new FileWriter("indexInfo.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    out.write(writeLines + "\nENDDDDD\n");
		    out.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
			//return 1;
		}
		
	}
	
	public void writeBackIndex(String finalStringIndex){
		try {
			/*
			writer = new FileWriter(file);
			writer.write(writeLines + "\n\n");
			writer.close();
			*/
			FileWriter fw = new FileWriter("newindex.html", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    out.write(finalStringIndex);
		    out.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
			//return 1;
		}
	}
	
	public void restoreIndex(){
		if(startEnd != null){
			startEnd.clear();
		}
		//startEnd.clear();
		readBlankIndex();
		String finalStringIndex = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader("blankIndex.html"));
			String line;
			int count = 1;
			int iterator = 0;
			while((line = reader.readLine()) != null){
				int insertSpot = 0;
				if(iterator < startEnd.size()){
					String insertSpotString = (String)startEnd.get(iterator);
					insertSpot = Integer.parseInt(insertSpotString);
				}
				if(count == insertSpot){
					//iterator++;
					BufferedReader reader2 = new BufferedReader(new FileReader("indexInfo.txt"));
					String line2;
					int endCounter = 0;
					while((line2 = reader2.readLine()) != null){
						if(line2.contains("ENDDDDD")){ endCounter++; }
						else if(endCounter == iterator){
							String temp = line2;
							if(line2.contains("null")){
								temp = "";
								temp = line2.replace("null", "");
							}
							finalStringIndex = finalStringIndex + temp;
							//break;
						}
					}
					reader2.close();
					iterator++;
					
				}
				finalStringIndex = finalStringIndex + line;
				count++;
			}
			
			reader.close();
			//System.out.println(finalStringIndex);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeBackIndex(finalStringIndex);
	}

}
