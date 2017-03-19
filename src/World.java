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
    int[][][] mapGrid;
    Coin[] abcde;
    MultiPurposeStack coins = new MultiPurposeStack();
    int coinId = 1;
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
        abcde = new Coin[midabcde.length];
        for(int i = 0; i < midabcde.length; i++){
            Coin banana = new Coin(i + 1);
            banana.setGridx(midabcde[i][0]);
            banana.setGridy(midabcde[i][1]);
            banana.setValue(midabcde[i][2]);
            banana.updatePosOnGrid();
            mapGrid[midabcde[i][1]][midabcde[i][0]][2] = i + 1;
            coins.addEnd(banana);
            abcde[i] = banana;
            coinId++;
        }


        // gets the starting position on the map for the player
        int[] charPos = map.getCharPos();
        me = new Character(this, charPos[0], charPos[1], 1, 1, 2, 1);
        //passes the terrain statistics to the character for movement
        me.setMap(mapGrid);
        //Allows to the master to attach the keystroke listener to the jpanel
        master = new MasterKeyListener(this);
        //assigns the pointer for the KeyArrayWASD so we can handel wasd input
        keyArrayWASD = master.getKeyArrayWASD();
        t.start();
    }
    //paints all the pretty pictures
    public void paint(Graphics g)
    {
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

        g.drawImage(me.getImage(),me.getX(),me.getY(),null);

    }
    //Method called by the ActionListener every time the timer completes its cycle
    //Still needs to be touched up
    public void actionPerformed(ActionEvent e)
    {
    	//checks to see if there are any keys that in the linked-list keyArrayWASD
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

    	me.update();
    	//Forces the System to redraw the screen
    	repaint();
    }
    public void addCoin(int gx, int gy, int value){
        Coin apple = new Coin(coinId);
        coinId++;
        apple.setGridy(gy);
        apple.setGridx(gx);
        apple.setValue(value);
        apple.updatePosOnGrid();
        coins.addEnd(apple);
    }
}