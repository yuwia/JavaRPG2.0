import java.util.Scanner;

public class LoadMap extends FileManager{
	Scanner scan;
	MultiPurposeStack pear = new MultiPurposeStack();
	int[] charpos;
	public LoadMap(String fileName){
		int rows = 0;
		charpos = new int[2];
		scan = setUpScanner(getFile(fileName));
		try{
			while(scan.hasNextLine()){
				String something = scan.nextLine();
				pear.addEnd(something);
				rows++;
				
				System.out.println(something);
				
			}
		}catch(NullPointerException e){
			boolean modify = true;
			while(modify){
				if(scan.nextLine() == null){
					modify = false;
				}
				String something = scan.nextLine();
				pear.addEnd(something);
				rows++;
				
				System.out.println(something);
				
			}
		}
		for(int i = rows; i >0; i--){
			String working = pear.getHead().getStringValue();
			String[] tokens = working.split(":");
			switch(tokens[0]){
			case "Start":
				String[] pos = tokens[1].split(",");
				charpos[0] = Integer.parseInt(pos[0]);
				charpos[1] = Integer.parseInt(pos[1]);
				break;
			case "//":
				//This area is for comments.
				break;
			}
		}
	}
	public int[] getCharPos(){
		return charpos;
	}
}
