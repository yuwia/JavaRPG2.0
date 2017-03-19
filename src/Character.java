public class Character extends Entity{

	//<editor-fold desc=".">
	//x is the x pixel location, y is the y pixel location, gridx is the x grid location,
	//gridy is the grid y location, buffdir is the limbo direction, dir is the active direction
	//</editor-fold>


	private World environment;
	//checks to see if the block in from of the character is walkabl
	//sets the direction buffer so the class can handle in between inputs
	//This method is absolutely needed, no other implementation is possible
	//This Constructor is for the Modified program
	public Character(String image, World planet, int gridx, int gridy, int health, int speed, int agility, int power){
        environment = planet;
        setImage(image);
        setGridx(gridx);
        setGridy(gridy);
        setHealth(health);
        setSpeed(speed);
        setAgility(agility);
        setPower(power);
        updatePosOnGrid();
    }
	public void checkCoins(){
		if(getMap()[getGridy()][getGridx()][2] != 0) {
			if(environment.coins.deleteCoinIndex(getMap()[getGridy()][getGridx()][2])){
                getMap()[getGridy()][getGridx()][2] = 0;
			}
		}
	}
	@Override
    public void update(){
	    super.update();
	    checkCoins();
    }
}
