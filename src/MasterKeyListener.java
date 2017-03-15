import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class MasterKeyListener{
	MultiPurposeStack 	keyArrayWASD = new MultiPurposeStack(),
						keyArrayArrows = new MultiPurposeStack();
    WASDKeyManager manager = new WASDKeyManager();
	public static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    public static final String 	MOVE_UP = "move up",
    							MOVE_LEFT = "move left",
    							MOVE_RIGHT = "move right",
    							MOVE_DOWN = "move down",
    							MOVE_UPR = "stop up",
    	    					MOVE_LEFTR = "stop left",
    	    					MOVE_RIGHTR = "stop right",
    	    					MOVE_DOWNR = "stop down";
	public MultiPurposeStack getKeyArrayWASD() {
		return keyArrayWASD;
	}
	public MasterKeyListener(World target){
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), MOVE_UP);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), MOVE_DOWN);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), MOVE_LEFT);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), MOVE_RIGHT);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), MOVE_UPR);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), MOVE_DOWNR);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), MOVE_LEFTR);
		target.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), MOVE_RIGHTR);
		target.getActionMap().put(MOVE_UP, new Listener(keyArrayWASD, "W", true, manager));
		target.getActionMap().put(MOVE_DOWN, new Listener(keyArrayWASD, "S", true, manager));
		target.getActionMap().put(MOVE_LEFT, new Listener(keyArrayWASD, "A", true, manager));
		target.getActionMap().put(MOVE_RIGHT, new Listener(keyArrayWASD, "D", true, manager));
		target.getActionMap().put(MOVE_UPR, new Listener(keyArrayWASD, "W", false, manager));
		target.getActionMap().put(MOVE_DOWNR, new Listener(keyArrayWASD, "S", false, manager));
		target.getActionMap().put(MOVE_LEFTR, new Listener(keyArrayWASD, "A", false, manager));
		target.getActionMap().put(MOVE_RIGHTR, new Listener(keyArrayWASD, "D", false, manager));
	}
}
@SuppressWarnings("serial")
class Listener extends AbstractAction{
	String Key = null;
	MultiPurposeStack banana = null;
	boolean add = false;
	WASDKeyManager manager;
	public Listener(MultiPurposeStack b, String key, boolean keyPressed, WASDKeyManager manage){
		banana = b;
		Key = key;
		add = keyPressed;
		manager = manage;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(add){
			switch(Key){
			case "D":
				if(!manager.isKey_d()){
					manager.setKey_d(true);
					banana.addBeginning(1);
				}
				break;
			case "A":
				if(!manager.isKey_a()){
					manager.setKey_a(true);
					banana.addBeginning(3);
				}
				break;
			case "S":
				if(!manager.isKey_s()){
					manager.setKey_s(true);
					banana.addBeginning(2);
				}
				break;
			case "W":
				if(!manager.isKey_w()){
					manager.setKey_w(true);
					banana.addBeginning(0);
				}
				break;
			}
		}else{
			switch(Key){
			case "D":
				if(manager.isKey_d()){
					manager.setKey_d(false);
					banana.deleteValue(1);
				}
				break;
			case "A":
				if(manager.isKey_a()){
					manager.setKey_a(false);
					banana.deleteValue(3);
				}
				break;
			case "S":
				if(manager.isKey_s()){
					manager.setKey_s(false);
					banana.deleteValue(2);
				}
				break;
			case "W":
				if(manager.isKey_w()){
					manager.setKey_w(false);
					banana.deleteValue(0);
				}
				break;
			}
		}
	}
}
//this class handles weather the wasd keys are up or down
class WASDKeyManager{
	boolean key_w,
			key_s,
			key_a,
			key_d;
	public boolean isKey_w() {
		return key_w;
	}
	public void setKey_w(boolean key_w) {
		this.key_w = key_w;
	}
	public boolean isKey_s() {
		return key_s;
	}
	public void setKey_s(boolean key_s) {
		this.key_s = key_s;
	}
	public boolean isKey_a() {
		return key_a;
	}
	public void setKey_a(boolean key_a) {
		this.key_a = key_a;
	}
	public boolean isKey_d() {
		return key_d;
	}
	public void setKey_d(boolean key_d) {
		this.key_d = key_d;
	}
}