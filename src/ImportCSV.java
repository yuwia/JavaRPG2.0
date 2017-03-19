import java.util.Scanner;

public class ImportCSV extends FileManager{
	public static void main(String[] args) {
		
		//Comment section used to beta program internally
		@SuppressWarnings("unused")
		ImportCSV apple = new ImportCSV();
		//
	}
	public ImportCSV(){
		//From the constructor, calls the import Array for the beta process
		//Not needed for the main program. The importArray function is public
		//so can be called by the map loader
		//importArray();
	}
	public int[][][] importArray(String fileName){
		//This section of the code needs to be modified
		//The import Array needs to accept a string value for the file name
		//String fileName = "map.csv";
		//embedded process for receiving the array. 3 different functions are used
		//getFile locates the file with the given name in the scr file
		//setUpScanner sets a Scanner that is targeting the file.
		//gatherArray takes the information from the file and processes it into an array
		return gatherArray(setUpScanner(getFile(fileName)));
	}
	int[][][] gatherArray(Scanner input){
		//if statement immediately kills the array if there is no file
		if(input == null){
			return null;
		}
		
		//MultiPurposeStack is there to handle unknown length of the data being imported
		//pear is for the rows, pear 2 is for the columns
		MultiPurposeStack pear = new MultiPurposeStack(), pear2 = new MultiPurposeStack();
		//rows records the total number of rows that the csv file has.
		//index is there to direct the data when pear2 is sorting through the columns
		int rows = 0, index = 0;
		// array is the 2d array that will be return;
		int[][][] array = null;
		//gathers all the rows from the file
		while(input.hasNextLine()){
			String something = input.nextLine();
			pear.addEnd(something);
			rows++;
		}
		//initializes the number of rows into the array
		array = new int[rows][][];
		//while loop takes each node of pear1, breaks it into a char array,
		//and adds each char to pear2 is it isn't a comma
		//this only works if the integers are single digit
		//array[] is then allotted the appropriate size array for the length of pear
		//then array is populated with the values from pear2 and then pear2 is emptied
		while(!pear.isEmpty()){
			String something2 = pear.getHead().getStringValue();
			char[] some = something2.toCharArray();
			for (int i = 0; i < some.length; i++) {
				if(some[i] != ',')
					pear2.addEnd(String.valueOf(some[i]));
			}
			array[index] = new int[pear2.getLength()][];
			int microIndex = 0;
			while(!pear2.isEmpty()){
				array[index][microIndex]  = new int[5];
				array[index][microIndex][0] = Integer.parseInt(pear2.getHead().getStringValue());
				pear2.deleteBeginning();
				microIndex++;
			}
			pear.deleteBeginning();
			index++;
		}
		return array;
	}
}
