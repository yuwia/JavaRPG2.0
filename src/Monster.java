/**
 * Created by Mark on 3/19/2017.
 */
public class Monster extends Entity{

    int index;
    public Monster(int gx, int gy, int type, int id){
        index = id;
        setGridx(gx);
        setGridy(gy);
        switch (type) {
            case 0:
                setImage("/nonClass/EnemyLevel1.png");
                setPower(0);
                setAgility(0);
                setSpeed(0);
                setHealth(1);
                break;
            case 1:
                setImage("/nonClass/EnemyLevel1.png");
                setPower(1);
                setAgility(2);
                setSpeed(0);
                setHealth(1);
                break;
            case 2:
                setImage("/nonClass/EnemyLevel2.png");
                setPower(2);
                setAgility(2);
                setSpeed(0);
                setHealth(1);
                break;
        }
    }
    public int getId(){
        return index;
    }
}
