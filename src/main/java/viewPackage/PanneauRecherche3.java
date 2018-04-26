package viewPackage;

import controllerPackage.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauRecherche3 extends JPanel
{
    private JPanel panneauRecherche3, panneauBoutons;
    private ApplicationController controller;
    private JButton retour, validation, nouvRecherche;
    private PanneauFiller panneauFiller;

    public PanneauRecherche3()
    {
        setController(new ApplicationController());
        //On va définir les layouts et composants à ajouter au panneau
        this.setLayout(new BorderLayout());

        panneauFiller = new PanneauFiller("<html><h3>Listing des articles achetés par un client</h3></html>");
        this.add(panneauFiller, BorderLayout.NORTH);

        panneauRecherche3 = new JPanel();
        panneauRecherche3.setLayout(new FlowLayout());
        this.add(panneauRecherche3, BorderLayout.CENTER);

        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Ajout des boutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Validation");
        panneauBoutons.add(validation);
        nouvRecherche = new JButton("Nouvelle recherche");
        panneauBoutons.add(nouvRecherche);
    }

    private class ButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public ApplicationController getController() {
        return controller;
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }
}
