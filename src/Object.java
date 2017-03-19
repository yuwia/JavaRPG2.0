import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Mark on 3/19/2017.
 */
public class Object {
    private BufferedImage image;
    private int x = 0,
            y = 0,
            gridx = 0,
            gridy = 0;
    public Object(){

    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(String location) {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(location));
        }
        catch(IOException e)
        {
            //interrupt statement if the image can't be found
            //contingency plans need to be put here
            e.printStackTrace();
        }
    }
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
    public int getGridx() {
        return gridx;
    }
    public void setGridx(int gridx) {
        this.gridx = gridx;
    }
    public int getGridy() {
        return gridy;
    }
    public void setGridy(int gridy) {
        this.gridy = gridy;
    }
    public void updatePosOnGrid(){
        x = gridx*16;
        y = gridy*16;
    }
}
