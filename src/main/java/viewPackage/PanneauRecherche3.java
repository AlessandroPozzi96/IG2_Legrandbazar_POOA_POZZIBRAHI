package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.GeneralException;
import exceptionPackage.ModelException;
import modelPackage.Recherche3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class PanneauRecherche3 extends JPanel
{
    private JPanel panneauRecherche3, panneauBoutons;
    private ApplicationController controller;
    private JButton retour, validation, nouvRecherche;
    private PanneauFiller panneauFiller;
    private PanneauBienvenue panneauBienvenue;
    private JLabel clientsLabel;
    private ArrayList<String> clients;
    private JComboBox<String> clientsCombox;
    private Integer numClient;
    private ArrayList<Recherche3> recherches3;
    private GetRecherche3Model getRecherche3Model;
    private JTable jTable;

    public PanneauRecherche3()
    {
        setController(new ApplicationController());
        //On va définir les layouts et composants à ajouter au panneau
        this.setLayout(new BorderLayout());

        panneauFiller = new PanneauFiller("<html><h3>Affichage des articles achetés par un client</h3></html>");
        this.add(panneauFiller, BorderLayout.NORTH);

        panneauRecherche3 = new JPanel();
        panneauRecherche3.setLayout(new FlowLayout());
        this.add(panneauRecherche3, BorderLayout.CENTER);

        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Combobox qui affiche la liste des clients
        clientsLabel = new JLabel("Liste des clients :");
        clientsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        clientsLabel.setToolTipText("Permettre de savoir quels articles a acheté un client");
        panneauRecherche3.add(clientsLabel);

        clients = new ArrayList<>();
        try
        {
            clients = getController().getClients();
        }
        catch (GeneralException e)
        {
            System.out.println(e.getMessage());
        }

        clientsCombox = new JComboBox<>();
        for (String client : clients)
        {
            clientsCombox.addItem(client);
        }
        panneauRecherche3.add(clientsCombox);

        //Ajout des boutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Validation");
        panneauBoutons.add(validation);
        nouvRecherche = new JButton("Nouvelle recherche");
        panneauBoutons.add(nouvRecherche);

        //Ecouteurs
        ItemsListener itemsListener = new ItemsListener();
        clientsCombox.addItemListener(itemsListener);
        ButtonsListener buttonsListener = new ButtonsListener();
        retour.addActionListener(buttonsListener);
        validation.addActionListener(buttonsListener);
        nouvRecherche.addActionListener(buttonsListener);
    }

    private class ButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour)
            {
                PanneauRecherche3.this.removeAll();
                PanneauRecherche3.this.panneauBienvenue = new PanneauBienvenue();
                PanneauRecherche3.this.add(panneauBienvenue);
                PanneauRecherche3.this.repaint();
                PanneauRecherche3.this.validate();
            }
            else
                {
                if (e.getSource() == nouvRecherche)
                {
                    PanneauRecherche3.this.removeAll();
                    PanneauRecherche3.this.add(new PanneauRecherche3());
                    PanneauRecherche3.this.validate();
                }
                else
                {
                    if (e.getSource() == validation)
                    {
                        if (!clients.isEmpty()) {
                            String clientSelection;
                            clientSelection = clientsCombox.getSelectedItem().toString();
                            String [] motSepare = clientSelection.split(" ");
                            numClient = Integer.parseInt(motSepare[0]);

                            recherches3 = new ArrayList<>();
                            try {
                                recherches3 = getController().getRecherche3(numClient);
                            } catch (GeneralException eG) {
                                System.out.println(eG.getMessage());
                            } catch (ModelException eM) {
                                System.out.println(eM.getMessage());
                            }
                            if (!recherches3.isEmpty())
                            {
                                getRecherche3Model = new GetRecherche3Model(recherches3);
                                jTable = new JTable(getRecherche3Model);
                                jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                                JScrollPane jScrollPane = new JScrollPane(jTable);

                                //On remplace le panneau de la recherche 3 par la JTable
                                panneauRecherche3.removeAll();
                                panneauRecherche3.setLayout(new BorderLayout());
                                panneauRecherche3.add(jScrollPane);
                                panneauRecherche3.repaint();
                                panneauRecherche3.validate();
                            }
                        }
                    }
                }
            }
        }
    }

    public ApplicationController getController() {
        return controller;
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private class ItemsListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {

        }
    }
}
