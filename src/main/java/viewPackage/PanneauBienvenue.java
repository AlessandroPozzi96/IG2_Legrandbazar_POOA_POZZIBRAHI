package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue extends JPanel
{
    private JLabel bienvenue;
    private GifAnimationThread gifAnimationThread;

    public PanneauBienvenue ()
    {
        //Layout + ajout d'une police d'écriture via le label
        this.setLayout(new BorderLayout());
        bienvenue = new JLabel("Bienvenue !");
        bienvenue.setFont(new Font("Helvetica", Font.ITALIC, 100));
        bienvenue.setHorizontalAlignment(0);
        this.setBackground(Color.RED);
        this.add(bienvenue, BorderLayout.NORTH);

        //Test d'affichage du gif
        gifAnimationThread = new GifAnimationThread(this);
        gifAnimationThread.start();
    }
}
