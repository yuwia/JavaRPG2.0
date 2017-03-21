import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel implements ActionListener
{
    //<editor-fold desc=".">
    //Dictates the period of the ActionListener
    //</editor-fold>
	int timerSpeed = 10;
    //<editor-fold desc=".">
    //Defines the timer to call the method ActionPerformed
    //</editor-fold>
    Timer t = new Timer(timerSpeed, this);
    //<editor-fold desc=".">
    //The details grid of the map. As of present, the grid reads [y][x] instead of [x][y]
    //</editor-fold>

    //<editor-fold desc=".">
    /**
     * Item Arrays for the map. Contains:
     * mapGrid: Data For the Map
     * coins: Loose Coins on the map that can be collected
     * mobs: monsters on the map.
     */
    //</editor-fold>
    int[][][] mapGrid;
    MultiPurposeStack   coins = new MultiPurposeStack(),
                        mobs = new MultiPurposeStack();
    int                 coinId = 1,
                        mobsId = 1;
    //<editor-fold desc=".">
    //Class that contains the background image
    //</editor-fold>
    BackgroundImage back = new BackgroundImage();
    //<editor-fold desc=".">
    //Class that contains the Character
    //</editor-fold>
    Character me;
    //<editor-fold desc=".">
    //Class that opens and reads the file holding all the data about the map
    //</editor-fold>
    LoadMap map = new LoadMap("nonClass/map1.txt");
    //<editor-fold desc=".">
    //Linked-list for the keys
    //Could be changed to an array stack to handle less processing power
    //</editor-fold>
    MultiPurposeStack keyArrayWASD;
    //<editor-fold desc=".">
    //Master listener for handling the keystrokes form the key board
    //presently handles only the wasd keys but nothing else.
    //can be expanded for other keys input types
    //</editor-fold>
    MasterKeyListener master;
    
    //Constructor
    public World()
    {
        setFocusTraversalKeysEnabled(false);		//not sure what this crap does
        
        //Class that is based around importing from csv files
        ImportCSV importer = new ImportCSV();
        //imports the terrain data for the map.
        mapGrid = importer.importArray("nonClass/map1.csv");
        int midabcde[][] = map.getCoins();
        for(int i = 0; i < midabcde.length; i++){
            addCoin(midabcde[i][0],midabcde[i][1],midabcde[i][2]);
        }
        midabcde = map.getMobs();
        for(int i = 0; i < midabcde.length; i++){
            addMob(midabcde[i][0],midabcde[i][1],midabcde[i][2]);
        }

        spawnPlayer();
        //Allows to the master to attach the keystroke listener to the jpanel
        master = new MasterKeyListener(this);
        //assigns the pointer for the KeyArrayWASD so we can handel wasd input
        keyArrayWASD = master.getKeyArrayWASD();
        t.start();
    }
    //paints all the pretty pictures
    public void paint(Graphics g){
    	//Called so we don't break anything
    	super.paint(g);
    	//sets the background color to red so it stands out if we are breaking anything
    	//Will set to black in the final iteration
    	setBackground(Color.RED);
    	//draws the background image at its location
    	//will try and turn this into a class-local method if possible
        g.drawImage(back.getImage(), back.getX(), back.getY(), null);
        //draws the character image at its location
        //will try and turn this into a class-local method if possible
        Node inQuestion = coins.getHead();
        while(inQuestion != coins.getFoot()){
            g.drawImage(inQuestion.getCoinValue().getImage(), inQuestion.getCoinValue().getX(), inQuestion.getCoinValue().getY(), null);
            inQuestion = inQuestion.getChild();
        }
        if(inQuestion != null)
        g.drawImage(inQuestion.getCoinValue().getImage(), inQuestion.getCoinValue().getX(), inQuestion.getCoinValue().getY(), null);

        inQuestion = mobs.getHead();
        while(inQuestion != mobs.getFoot()){
            g.drawImage(inQuestion.getMonsterValue().getImage(), inQuestion.getMonsterValue().getX(), inQuestion.getMonsterValue().getY(), null);
            inQuestion = inQuestion.getChild();
        }
        if(inQuestion != null)
            g.drawImage(inQuestion.getMonsterValue().getImage(), inQuestion.getMonsterValue().getX(), inQuestion.getMonsterValue().getY(), null);

        g.drawImage(me.getImage(),me.getX(),me.getY(),null);

    }
    //Method called by the ActionListener every time the timer completes its cycle
    //Still needs to be touched up
    public void actionPerformed(ActionEvent e){
    	//checks to see if there are any keys that in the linked-list keyArrayWASD
        /**
         * move the code after this into the Character Class
         */
    	if(!keyArrayWASD.isEmpty()){
    		//switch statement gets the last key that was pressed and passes
    		//the direction info to the Character
    		switch(keyArrayWASD.getHead().getIntValue()){
    		case 0:
    			me.setBuffDir(0);
    			break;
    		case 1:
    			me.setBuffDir(1);
    			break;
    		case 2:
    			me.setBuffDir(2);
    			break;
    		case 3:
    			me.setBuffDir(3);
    			break;
    		default:
    			me.setBuffDir(-1);
    		}
    	//Default case that clears the direction if the linked-list is empty
    	}else if(keyArrayWASD.isEmpty()){
			me.setBuffDir(-1);
		}
        cheackDeath();
    	repaint();
    }
    public void addCoin(int gx, int gy, int value){
        Coin apple = new Coin(coinId);
        mapGrid[gy][gx][2] = coinId;
        apple.setGridy(gy);
        apple.setGridx(gx);
        apple.setValue(value);
        apple.updatePosOnGrid();
        coins.addEnd(apple);
        coinId++;
    }
    public void addMob(int gx, int gy, int type){
        Monster monster = new Monster(gx, gy, type, mobsId);
        mapGrid[gy][gx][1] = mobsId;
        monster.updatePosOnGrid();
        mobs.addBeginning(monster);
        mobsId++;
    }
    public void spawnPlayer(){
        // gets the starting position on the map for the player
        int[] charPos = map.getCharPos();
        me = new Character("nonClass/character.png",this, charPos[0], charPos[1], 2, 2, 1, 1);
        //passes the terrain statistics to the character for movement
        me.setMap(mapGrid);
    }

     public void cheackDeath(){
        if(!me.isDeath()) {
            me.update();
        }else{
            spawnPlayer();
        }
        Node inQuestion = null;
        inQuestion = mobs.getHead();
        Monster monster = null;
        while(inQuestion != mobs.getFoot()){
            monster = inQuestion.getMonsterValue();
            if(monster.isDeath()){
                mapGrid[monster.getGridy()][monster.getGridx()][1] = 0;
                inQuestion = inQuestion.getChild();
                mobs.deleteNode(inQuestion.getParent());
            }else{
                inQuestion = inQuestion.getChild();
            }
        }

        if (mobs.getFoot().getMonsterValue().isDeath()) {
            mapGrid[inQuestion.getMonsterValue().getGridy()][inQuestion.getMonsterValue().getGridx()][1] = 0;
            mobs.deleteNode(inQuestion);
        }
    }

}