import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//This is a super class used by the LoadMap Class and by the ImportCSV class. This class contains
//two methods. getFile finds the file with the apporiate file name and returns a File class for it.
//setUpScanner sets up a scanner that targets the selected file 
public class FileManager {
	//empty constructor
	public FileManager(){
		
	}
	//This method tries to find a file with the name of the string parameter
	File getFile(String fileName){
		//initiates a null File class
		File file = null;
		//Not sure what this line of code does honestly
		ClassLoader classLoader = getClass().getClassLoader();
		//tries to locate said file. If the file isn't found file remains null
		try{
			//tries to locate the file
			file = new File(classLoader.getResource(fileName).getFile());
		} catch(NullPointerException e){
			//debugging error message
			System.out.println("File wasn't found2");
		}
		return file;
	}
	//this method sets up a scanner to read from the target file
	Scanner setUpScanner(File file){
		//initiates a null Scanner class
		Scanner input = null;
		if(file != null){
			//tries to point the scanner to the file
			try {
				//tries to point the scanner to the file
				input = new Scanner(file);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			//this else isn't necessarily worth being here
			//this is just an backup case
			//might change it to load a default map
			System.out.println("File wasn't found1");
			System.out.println("Please enter a valid file name");
			Scanner felix = new Scanner(System.in);
			file = getFile(felix.nextLine());
			try {
				input = new Scanner(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				felix.close();
				return null;
			}
			//closes the scanner
			felix.close();
		}
		return input;
	}
}
