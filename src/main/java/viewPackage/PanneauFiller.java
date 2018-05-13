package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauFiller extends JPanel
{
    private JTextPane jTextPane;

    public PanneauFiller(String texte) {
        this.setLayout(new BorderLayout());
        String htmlText = new String("<html><h3>" + texte + "</h3></html>");
        jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        jTextPane.setText(htmlText);
        jTextPane.setFont(new Font("Apple Casual", Font.PLAIN, 20));
        this.add(jTextPane, BorderLayout.CENTER);
    }

}
