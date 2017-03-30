public class Character extends Entity{

	//<editor-fold desc=".">
	//x is the x pixel location, y is the y pixel location, gridx is the x grid location,
	//gridy is the grid y location, buffdir is the limbo direction, dir is the active direction
	//</editor-fold>

	private World environment;
	private int sightRange = 0;
	//checks to see if the block in from of the character is walkable
	//sets the direction buffer so the class can handle in between inputs
	//This method is absolutely needed, no other implementation is possible
	//This Constructor is for the Modified program
	public Character(String image, World planet, int gridx, int gridy, int health, int speed, int agility, int power, int sight){
        environment = planet;
        sightRange = sight;
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
		if(getMap()[getGridy()][getGridx()][3] != 0) {
			if(environment.coins.deleteCoinIndex(getMap()[getGridy()][getGridx()][3])){
                getMap()[getGridy()][getGridx()][3] = 0;
			}
		}
	}
	public void attackMonster(){
	    if(getMap()[getGridy()][getGridx()][2] != 0){
            attack(environment.mobs.getNodeWithValue(getMap()[getGridy()][getGridx()][2]).getMonsterValue());
            switch (getDir()){
                case 0:
                    setY(getY() + 16);
                    break;
                case 1:
                    setX(getX() - 16);
                    break;
                case 2:
                    setY(getY() - 16);
                    break;
                case 3:
                    setX(getX() + 16);
                    break;
                default:
                    System.out.print("This should never ever run");
                    break;
            }
        }
    }
	@Override
    public void update(){
	    super.update();
	    environment.updateOffset();
	    checkCoins();
	    attackMonster();
    }
    public void attack(Monster monster) {
        if (monster.getAgility() > getAgility()) {
            setHealth(getHealth()- monster.getPower());
            if(!isDeath()) {
                monster.setHealth(monster.getHealth() - getPower());
            }
        }else if(monster.getAgility() < getAgility()){
            monster.setHealth(monster.getHealth() - getPower());
            if(!monster.isDeath()) {
                setHealth(getHealth() - monster.getPower());
            }
        }else{
            monster.setHealth(monster.getHealth() - getPower());
            setHealth(getHealth()- monster.getPower());
        }
    }
    public int getSightRange(){
        return sightRange;
    }
    public void setSightRange(int sight){
        sightRange = sight;
    }
}
