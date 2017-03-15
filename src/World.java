import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel implements ActionListener
{
	//Dictates the period of the ActionListener
	int timerSpeed = 10;
	//Defines the timer to call the method ActionPerformed
    Timer t = new Timer(timerSpeed, this);
    //The details grid of the map. As of present, the grid reads [y][x] instead of [x][y]
    int[][] mapGrid;
    //Class that contains the background image
    BackgroundImage back = new BackgroundImage();
    //Class that contains the Character
    Character me = new Character(0,0);
    //Class that opens and reads the file holding all the data about the map
    LoadMap map = new LoadMap("map1.txt");
    //Linked-list for the keys
    //Could be changed to an array stack to handle less processing power
    MultiPurposeStack keyArrayWASD;
    //Master listener for handling the keystrokes form the key board
    //presently handles only the wasd keys but nothing else.
    // can be expanded for other keys input types
    MasterKeyListener master;
    
    //Constructor
    public World()
    {
        setFocusTraversalKeysEnabled(false);		//not sure what this crap does
        
        //Class that is based around importing from csv files
        ImportCSV importer = new ImportCSV();
        //imports the terrain data for the map.
        mapGrid = importer.importArray("map1.csv");
        // gets the starting position on the map for the player
        int[] charPos = map.getCharPos();
        me = new Character(charPos[0] * 16, charPos[1] * 16);
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
        g.drawImage(me.getImage(),me.getX(),me.getY(),null);
    }
    //Method called by the ActionListner
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
    	//Calls the method in the character to handle direction
    	me.updateDir();
    	//Calls the method to handle character movement
    	me.move();
    	//Calls the method to handle grid position for the charater
    	me.updateGridPos();
    	//Forces the System to redraw the screen
    	repaint();
    }    
}