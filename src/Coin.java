/**
 * Created by Mark on 3/18/2017.
 */
public class Coin extends Object{
    private int value,
            index;
        public Coin(int gx, int gy, int in, int value){
            //This class has a Money Value. We need a way to get it to destroy when the character touches it.
            //The world class will need to handle catch the value and adding it to the persons inventory
            //Maybe for beta purpose have a MasterClass handle spawning new coins
            this.value = value;
            setGridx(gx);
            setGridy(gy);
            updatePosOnGrid();
            setImage("nonClass/Coin.png");
            index = in;
    }
    public void setValue(int v){
        value = v;
    }
    public int getValue(){
        return value;
    }
    public int getIndex(){
        return index;
    }
}
