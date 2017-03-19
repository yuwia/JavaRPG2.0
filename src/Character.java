public class Character extends Object{

	//<editor-fold desc=".">
	//x is the x pixel location, y is the y pixel location, gridx is the x grid location,
	//gridy is the grid y location, buffdir is the limbo direction, dir is the active direction
	//</editor-fold>
	private int buffDir,
				health,
				agility,
				power,
				speed,
    			dir = -1;
	private World environment;
	//map is the terrain map for the currently loaded map
	private int[][][] map;
	//inGrid booleans is true when the character is locked into a grid square
	private boolean inGrid = true;
	//handles the active direction and the grid locking position
	//the buffDir variable allows us to hold and attempts to modify the active direction in limbo
	//until the character has locked into a grid square
	public void updateDir(){
		if((getX() % 16 == 0) && (getY() % 16 == 0)){
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
			setGridy((int)Math.floor((double)getY()/16));
			break;
		case 1:
			//sets the target grid position to the one to the right
			setGridx((int)Math.ceil((double)getX()/16));
			break;
		case 2:
			//sets the target grid position to the one below
			setGridy((int)Math.ceil((double)getY()/16));
			break;
		case 3:
			//sets the target grid position to the one to the left
			setGridx((int)Math.floor((double)getX()/16));
			break;
			default:
				break;
		}
	}
	//allows the world class to pass the terrain map
	public void setMap(int[][][] map) {
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
				setY(getY() - speed);
				break;
			case 1:
				//Character moves right
				setX(getX() + speed);
				break;
			case 2:
				//Character moves down
				setY(getY() + speed);
				break;
			case 3:
				//Character moves left
				setX(getX() - speed);
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
			return (map[getGridy() - 1][getGridx()][0] == 0);
		case 1:
			return (map[getGridy()][getGridx() + 1][0] == 0);
		case 2:
			return (map[getGridy() + 1][getGridx()][0] == 0);
		case 3:
			return (map[getGridy()][getGridx() - 1][0] == 0);
		}
		return false;
	}
	//sets the direction buffer so the class can handle in between inputs
	//This method is absolutely needed, no other implementation is possible
	public void setBuffDir(int dir) {
		this.buffDir = dir;
	}
	public Character(World planet,int xx, int yy, int hearts, int agl, int spe, int pow) {
		environment = planet;
		//updates the x and y pixel cords
		//updates the grid position in the x and the y
		setGridx(xx);
		setGridy(yy);
		setX(xx * 16);
		setY(yy * 16);


		//calls the method that loads the image
		setImage("nonClass/character.png");

		//Takes the input from the constructor and populates the integers
		speed = spe;
		agility = agl;
		health = hearts;
		power = pow;
	}

	public void checkCoins(){
		if(map[getGridy()][getGridx()][2] != 0) {
			if(environment.coins.deleteCoinIndex(map[getGridy()][getGridx()][2])){
				map[getGridy()][getGridx()][2] = 0;
			}
		}
	}
	//this method retrieves all the images for the class

	public void update(){
		move();
		updateDir();
		updateGridPos();
		checkCoins();
	}
}
