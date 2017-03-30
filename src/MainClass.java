import javax.swing.JFrame;

public class MainClass
{
    public static void main(String args [])
    {
        JFrame f = new JFrame("Java RPG");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //<editor-fold desc="Sets the size of the screen">
        //The Height and width values have been set so that they match the grid perfectly.
        f.setSize(502, 525);
        //</editor-fold>
        f.setResizable(false);
        Menu menu = new Menu(f);
        menu.addButtons();
    }
}
