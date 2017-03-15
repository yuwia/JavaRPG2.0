import javax.swing.JFrame;

public class MainClass
{
    public static void main(String args [])
    {
        JFrame f = new JFrame();
        World s = new World();
        f.add(s);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(512, 512);
        f.setResizable(false);
    }
}
