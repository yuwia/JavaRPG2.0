import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Mark on 3/19/2017.
 */
public class Object extends JComponent {
    private BufferedImage image;
    private int x = 0;
    private int y = 0;
    private int xImageOffset = 0;
    private int yImageOffset = 0;
    private int gridx = 0;
    private int gridy = 0;

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
        return x - xImageOffset;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y - yImageOffset;
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
    public void setxImageOffset(int xImageOffset) {
        this.xImageOffset = xImageOffset;
    }
    public void setyImageOffset(int yImageOffset) {
        this.yImageOffset = yImageOffset;
    }
    public int getxImageOffset(){
        return  xImageOffset;
    }
    public int getyImageOffset(){
        return xImageOffset;
    }
    public void updatePosOnGrid(){
        x = gridx*16;
        y = gridy*16;
    }
}
