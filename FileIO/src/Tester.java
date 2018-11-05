import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String workingDir = System.getProperty("user.dir");

		JFileChooser chooser = new JFileChooser(workingDir);
//		FileNameExtensionFilter filter = new FileNameExtensionFilter(
//				"JPG & GIF Images", "jpg", "gif");
//		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null); //would usually pass in a window
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
			ArrayList<String> fileData = FileIO.readFile(chooser.getSelectedFile().getAbsolutePath());
			System.out.println(fileData);
			
			FileIO.writeFile("testFile", fileData);
			
		}

	}

}
