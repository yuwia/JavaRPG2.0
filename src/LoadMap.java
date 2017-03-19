import java.util.Scanner;

public class LoadMap extends FileManager{
	Scanner scan;
	MultiPurposeStack pear = new MultiPurposeStack();
	int[] 	charpos;
	int[][]	coins,
			mobs;
	public LoadMap(String fileName){
		int rows = 0;
		charpos = new int[2];
		scan = setUpScanner(getFile("" + fileName));
		try{
			while(scan.hasNextLine()){
				String something = scan.nextLine();
				pear.addEnd(something);
				rows++;
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
			}
		}
		for(int i = rows; i > 0; i--){
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
				case "Coin":
					String[] data = tokens[1].split(";");
					coins = new int[data.length][];
					int coinindex = 0;
					for(String apple: data) {
						coins[coinindex] = new int[3];
						String[] banana = apple.split(",");
						coins[coinindex][0] = Integer.parseInt(banana[0]);
						coins[coinindex][1] = Integer.parseInt(banana[1]);
						coins[coinindex][2] = Integer.parseInt(banana[2]);
						coinindex++;
					}
					break;
				case "Mobs":
					String[] horde = tokens[1].split(";");
					mobs = new int[horde.length][];
					int mobindex = 0;
					for(String apple: horde) {
						mobs[mobindex] = new int[3];
						String[] banana = apple.split(",");
						mobs[mobindex][0] = Integer.parseInt(banana[0]);
						mobs[mobindex][1] = Integer.parseInt(banana[1]);
						mobs[mobindex][2] = Integer.parseInt(banana[2]);
						mobindex++;
					}
					break;
				default:
					System.out.println("You have some data in the files that isn/'t being read");
					break;
			}
			pear.deleteBeginning();
		}
	}
	public int[] getCharPos(){
		return charpos;
	}
	public int[][] getCoins(){
		return coins;
	}
	public int[][] getMobs(){
		return  mobs;
	}
}
