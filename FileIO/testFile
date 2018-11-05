import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	public static final String fileSeparator = System.getProperty("file.separator");
	public static final String lineSeparator = System.getProperty("line.separator");

	
	public static ArrayList<String> readFile(String filename){

		Scanner in = null;
		try {	
			ArrayList<String> fileData = new ArrayList<String>();
			FileReader reader = new FileReader(filename);
			in = new Scanner(reader);

			while(in.hasNextLine()) {
				String data = in.nextLine();
				fileData.add(data);
			}
			return fileData;

		} catch(FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			if (in != null) {
				in.close();
			}

		}

		return null;

	}
	
	public static void writeFile(String filename, ArrayList<String> fileData){

		FileWriter writer = null;
		
		try {	
			
			
			writer = new FileWriter(filename);

			for (String line:fileData) {
				writer.write(line);
				writer.write(lineSeparator);
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if ( writer!= null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}


	}
	
	
	


}
