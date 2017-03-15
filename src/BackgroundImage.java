import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundImage {
	int x = 0;                     //x position
    int y = 0;                     //y position
	private BufferedImage image;
	// These methods have nothing to-do with the movement of the class.
	// The only possible methods are the x and y positions. WE are not moving the map yet so not an issue
	public BackgroundImage(){
		image();
	}
	//returns the image for drawing purposes possible to change to a local method
	public BufferedImage getImage() {
		return image;
	}
	void image(){
		try
        {
			image = ImageIO.read(getClass().getResourceAsStream("map1.png"));
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
	}
	//getters and setters for the x position, mainly used by to be paint
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
