package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauInsertion extends JPanel {
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel prenomLabel;
    private JTextField prenomText;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;

    public PanneauInsertion()
    {
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauFormulaire = new JPanel();
        panneauFormulaire.setLayout(new GridLayout(1, 2, 3, 3));
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Création des labels dans la grille
    }
}
