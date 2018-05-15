package viewPackage;

import javax.swing.*;

public class GifAnimationThread extends Thread
{
    private JPanel panel;
    private PanneauGIF panneauGIF;
    private int x = 30, y = 150, vX = 1, vY = 1;
    public GifAnimationThread(JPanel panel) {
        super("Gif animation");
        this.panel = panel;
        panneauGIF = new PanneauGIF();
        this.panel.add(panneauGIF);
    }

    //Test pour faire bouger le gif à l'intérieur de la fenêtre sans dépasser les bords
    public void run ()
    {

        while (true) {
            try {
                //Le gif va rebondir sur les paroi du panneau bienvenu
                if (x >= (panel.getWidth() - panneauGIF.getWidthIcon()) || x <= 0 )
                {
                    vX *= -1;
                }
                if (y >= (panel.getHeight() - panneauGIF.getHeightIcon()) || y <= panel.getHeight()/4)
                {
                    vY *= -1;
                }

                x += vX;
                y += vY;
                panneauGIF.setBounds(x, y, panneauGIF.getWidthIcon(), panneauGIF.getHeightIcon());
                panneauGIF.validate();
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}