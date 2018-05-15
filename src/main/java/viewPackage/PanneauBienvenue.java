package viewPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PanneauBienvenue extends JPanel
{
    private JPanel panelImage;
    private JLabel bienvenue;
    private GifAnimationThread gifAnimationThread;
    private Image imgFond;

    public PanneauBienvenue ()
    {
        panelImage = new JPanel();
        try {
            imgFond = ImageIO.read(new File("fond.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Layout + ajout d'une police d'écriture via le label
        this.setLayout(new BorderLayout());
        bienvenue = new JLabel("Bienvenue !");
        bienvenue.setFont(new Font("Helvetica", Font.ITALIC, 100));
        bienvenue.setHorizontalAlignment(0);

        this.add(bienvenue,BorderLayout.NORTH);
        this.add(panelImage,BorderLayout.CENTER);


        //Test d'affichage du gif
        gifAnimationThread = new GifAnimationThread(this);
        gifAnimationThread.start();
    }
    public void paintComponent(Graphics g){
        g.drawImage(imgFond,0,0,PanneauBienvenue.this);
    }

}
