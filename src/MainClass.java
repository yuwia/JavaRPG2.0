import javax.swing.JFrame;

public class MainClass
{
    public static void main(String args [])
    {
        JFrame f = new JFrame("Java RPG");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(512, 512);
        f.setResizable(true);
        Menu menu = new Menu(f);
        menu.addButtons();
    }
}
