import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character {

	//Character image
	private BufferedImage image;
	private int speed = 2;
	//x is the x pixel location, y is the y pixel location, gridx is the x grid location, 
	//gridy is the grid y location, buffdir is the limbo direction, dir is the active direction 
	private int x = 0,
		y = 0,
		gridx = 0,
    	gridy = 0,
    	buffDir,
    	dir;
	//map is the terrain map for the currently loaded map
	private int[][] map;
	//inGrid booleans is true when the character is locked into a grid square
	private boolean inGrid = true;
	//handles the active direction and the grid locking position
	//the buffDir variable allows us to hold and attempts to modify the active direction in limbo
	//until the character has locked into a grid square
	public void updateDir(){
		if((x % 16 == 0) && (y % 16 == 0)){
			inGrid = true;
			dir = buffDir;
		}else{
			inGrid = false;
		}
	}
	//updates the grid position for the character
	public void updateGridPos(){
		//changes the current gird position to the grid position that the character is moving to
		switch(dir){
		case 0:
			//sets the target grid position to the one above
			gridy = (int)Math.floor((double)y/16);
			break;
		case 1:
			//sets the target grid position to the one to the right
			gridx = (int)Math.ceil((double)x/16);
			break;
		case 2:
			//sets the target grid position to the one below
			gridy = (int)Math.ceil((double)y/16);
			break;
		case 3:
			//sets the target grid position to the one to the left
			gridx = (int)Math.floor((double)x/16);
			break;
			default:
				break;
		}
		//this line of code is here for debugging purposes
		System.out.println(gridx + ", " + gridy);
	}
	//allows the world class to pass the terrain map
	public void setMap(int[][] map) {
		this.map = map;
	}
	public void move(){
		//the first if statement hands when the character is in mid-step
		//the second if statement checks to see if the block is open to walk when the player is inGrid
		if(inGrid == false || (inGrid == true && isClear() == true)){
			switch(dir){
			case -1:
				//Character doesn't move
				break;
			case 0:
				//Character moves upward
				y-= speed;
				break;
			case 1:
				//Character moves right
				x+= speed;
				break;
			case 2:
				//Character moves down
				y+= speed;
				break;
			case 3:
				//Character moves left
				x-= speed;
				break;
			}
		}
	}
	//checks to see if the block in from of the character is walkable
	public boolean isClear(){
		//not 100% sure how this segment of code works
		//Further adaptation needs to be done to take into account the size of the image
		//the character and most mobs will be one block size, but bosses and mini-bosses will be larger
		//and it would be convenient to have them run through the same superclass
		switch(dir){
		case 0:
			//there is an offset because of the pictures height. this needs to be fixed ASAP.
			//this goes for all the comparison statements in this method
			return (map[gridy][gridx] == 0);
		case 1:
			return (map[gridy + 1][gridx + 1] == 0);
		case 2:
			return (map[gridy + 2][gridx] == 0);
		case 3:
			return (map[gridy + 1][gridx - 1] == 0);
		}
		return false;
	}
	//sets the direction buffer so the class can handle in between inputs
	//This method is absolutely needed, no other implementation is possible
	public void setBuffDir(int dir) {
		this.buffDir = dir;
	}
	//returns the x cords so they can be painted
	public int getX() {
		return x;
	}
	//returns the y cords so they can be painted
	public int getY() {
		return y;
	}
	//returns the image to the world class to draw it out
	//this could be implemented in a local method to cut down on methods
	public BufferedImage getImage() {
		return image;
	}
	//constructor that allows us to set the x and y positions for the character and updates its grid position
	public Character(int xx, int yy){
		//updates the x and y pixel cords
		x = xx;
		y = yy;
		//updates the grid position in the x and the why
		gridx= xx/16;
		gridy= yy/16;
		//calls the method that loads the image
		image();
	}
	//this method retrieves all the images for the class
	void image(){
		try
        {
			//tries to grab the image for the character
			image = ImageIO.read(getClass().getResourceAsStream("Character1.png"));
        }
		catch(IOException e)
        {
			//interrupt statement if the image can't be found
			//contingency plans need to be put here
            e.printStackTrace();
        }
	}
}
