/**
 * Created by Mark on 3/19/2017.
 */
public class Monster extends Entity{

    private int index, monsterType;
    public Monster(int gx, int gy, int type, int id){
        index = id;
        setGridx(gx);
        setGridy(gy);
        monsterType = type;
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
    public void dropItems(int[][][] mapGrid, int coinId, MultiPurposeStack apple){
        int meta = mapGrid[getGridy()][getGridx()][3];
        if(meta == 0) {
            System.out.println("Here");
            Coin coin = new Coin(getGridx(), getGridy(), coinId, monsterType + 2);
            apple.addEnd(coin);
            mapGrid[getGridy()][getGridx()][3] = coinId;
        }else{
            Coin coin = apple.getNodeWithValue(mapGrid[getGridy()][getGridx()][3]).getCoinValue();
            System.out.println("Here2");
            coin.setValue(coin.getValue() + monsterType + 2);
        }
    }
}
