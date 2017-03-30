

/**
 * Created by Mark on 3/19/2017.
 */
public class Entity extends Object{

    private boolean inGrid = true;
    private int buffDir,
            health,
            agility,
            power,
            speed,
            dir = -1;
    private int[][][] map;

    public Entity(int xx, int yy, int hearts, int agl, int spe, int pow) {
        setGridx(xx);
        setGridy(yy);
        setX(xx * 16);
        setY(yy * 16);

        //Takes the input from the constructor and populates the integers
        speed = spe;
        agility = agl;
        health = hearts;
        power = pow;
    }
    public Entity(){

    }
    public boolean isInGrid(){
        return inGrid;
    }
    public void updateDir(){
        if((getX() % 16 == 0) && (getY() % 16 == 0)){
            inGrid = true;
            dir = buffDir;
        }else{
            inGrid = false;
        }
    }
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
    public void move(){
        //the first if statement hands when the character is in mid-step
        //the second if statement checks to see if the block is open to walk when the player is inGrid
        if(!inGrid || (inGrid && isClear())){
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
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDir(int dir) {
        this.dir = dir;
    }
    public int getBuffDir() {
        return buffDir;
    }
    public int getHealth() {
        return health;
    }
    public int getAgility() {
        return agility;
    }
    public int getPower() {
        return power;
    }
    public int getSpeed() {
        return speed;
    }
    public int getDir() {
        return dir;
    }
    public void setMap(int[][][] map) {
        this.map = map;
    }
    public int[][][]getMap(){
        return map;
    }
    public void setBuffDir(int dir) {
        this.buffDir = dir;
    }
    public void setHealth(int hearts){
        health = hearts;
    }
    public void update(){
        move();
        updateDir();
        updateGridPos();
    }
    public boolean isDeath(){
        if(health <= 0){
            return true;
        }
        return false;
    }
}
