import java.awt.*;

public class BackgroundImage extends Object{

	// These methods have nothing to-do with the movement of the class.
	// The only possible methods are the x and y positions. WE are not moving the map yet so not an issue
	public BackgroundImage(){

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(getImage(), 3, 4, this);
	}
}
