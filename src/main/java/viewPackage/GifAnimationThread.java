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
                if (y >= (panel.getHeight() - panneauGIF.getHeightIcon()) || y <= 0)
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

/* repaint() - this method can't be overridden. It controls the update() -> paint() cycle.
You should call this method to get a component to repaint itself. If you have done anything to change the look of the component, but not it's size ( like changing color, animating, etc. ) then call this method.

validate() - This tells the component to lay itself out again and repaint itself.
If you have done anything to change the size of the component or any of it's children(adding, removing, resizing children), you should call this method...
I think that calling revalidate() is preferred to calling validate() in Swing, though...

updateUI() - Call this method if you have changed the pluggable look & feel for a component after it has been made visible.
*/