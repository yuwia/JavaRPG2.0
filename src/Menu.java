import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Menu extends JPanel
{
    JFrame frame;
    private BufferedImage image;

    public Menu(JFrame frm)
    {
        frame = frm;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("nonClass/menuBackground.png"));
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addButtons()
    {
        //Set Background image
        frame.setContentPane(new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 512, 512, this);
            }
        });

        //Set so buttons are aligned on y axis
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //Create Rigid Area to format buttons location
        Component rigidArea = Box.createRigidArea(new Dimension(150,150));
        frame.getContentPane().add(rigidArea);

        //Add Play Button
        playButtonAction plyBttAct = new playButtonAction();
        JButton playButton = new JButton(null, createImageIcon("nonClass/startButton.png"));
        plyBttAct.setFrame(frame);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(playButton);
        playButton.addActionListener(plyBttAct);

        //Add Credits Button
        creditButtonAction crdtBttAct = new creditButtonAction();
        JButton creditButton = new JButton(null, createImageIcon("nonClass/creditsButton.png"));
        crdtBttAct.setFrame(frame);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(creditButton);
        creditButton.addActionListener(crdtBttAct);

        //Add Quit Button
        JButton quitButton = new JButton(null, createImageIcon("nonClass/exitButton.png"));
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(quitButton);
        quitButton.addActionListener(new quitButtonAction());

        frame.setVisible(true);
    }

    protected static ImageIcon createImageIcon(String path)
    {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    static class playButtonAction implements ActionListener
    {
        JFrame frame;

        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        public void actionPerformed(ActionEvent e)
        {
            frame.getContentPane().removeAll();

            World w = new World();
            frame.getContentPane().add(w);

            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
        }
    }

    static class creditButtonAction extends JPanel implements ActionListener
    {
        JFrame frame;

        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        protected static ImageIcon createImageIcon(String path)
        {
            java.net.URL imgURL = Menu.class.getResource(path);
            if (imgURL != null)
            {
                return new ImageIcon(imgURL);
            }
            else
            {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        }

        public void actionPerformed(ActionEvent e)
        {
            //Clear Components from frame
            frame.getContentPane().removeAll();

            //Add rigid area
            Component rigidArea = Box.createRigidArea(new Dimension(150,100));
            frame.getContentPane().add(rigidArea);

            //Add Back Button
            backButtonAction bckBttAct = new backButtonAction();
            JButton backButton = new JButton(null, createImageIcon("nonClass/backButton.png"));
            bckBttAct.setFrame(frame);
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            frame.getContentPane().add(backButton);
            backButton.addActionListener(bckBttAct);

            //Add rigid area
            Component rigidArea2 = Box.createRigidArea(new Dimension(150,10));
            frame.getContentPane().add(rigidArea2);


            //Add Credits Text
            JLabel text = new JLabel("Credits");
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            frame.getContentPane().add(text);

            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
        }
    }

    static class quitButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    static class backButtonAction implements ActionListener
    {
        JFrame frame;

        public void setFrame(JFrame frm)
        {
            frame = frm;
        }

        public void actionPerformed(ActionEvent e)
        {
            //Clear Components from frame
            frame.getContentPane().removeAll();

            Menu men = new Menu(frame);
            men.addButtons();

            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
        }
    }
}
