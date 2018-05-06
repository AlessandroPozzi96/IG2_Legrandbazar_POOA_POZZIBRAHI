package viewPackage;

import java.awt.*;

public class GifAnimationThread extends Thread
{
    private PanneauGIF panneauGIF;
    private Container container;

    public GifAnimationThread(Container container) {
        super("Gif animation");
        this.container = container;
        panneauGIF = new PanneauGIF();
        this.container.add(panneauGIF);
        this.container.validate();
    }

    public void run ()
    {
        while (true) {
            this.container.revalidate();
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