package viewPackage;

import controllerPackage.ApplicationController;

import javax.swing.*;
import java.awt.*;

public class PanneauRecherche2 extends JPanel
{
    private JPanel panneauRecherche2, panneauBoutons;
    private ApplicationController controller;
    private JLabel dateDebut, dateFin;
    private JButton retour, validation, nouvRecherche;
    private PanneauBienvenue panneauBienvenue;
    private PanneauFiller panneauFiller;

    public PanneauRecherche2() {
        setController(new ApplicationController());
        //On ajoute les différents panneaux et les layouts
        this.setLayout(new BorderLayout());
        panneauRecherche2 = new JPanel();
        panneauRecherche2.setLayout(new FlowLayout());
        this.add(panneauRecherche2, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);
    }

    public ApplicationController getController() {
        return controller;
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }
}
