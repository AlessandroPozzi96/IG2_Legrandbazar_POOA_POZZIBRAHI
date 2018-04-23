package viewPackage;

import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;

public class FonctionEcouteurs
{
    //Fonctions pour éviter de duppliquer le code écouteurs dans chaque panneau
    public void afficherOrdre(OrdrePreparation ordrePreparation) {
        String ordre = "<html>" +
                "<h1> Date de création :" + ordrePreparation.getDate() + " Numéro séquentiel :" + ordrePreparation.getNumeroSequentiel() + "</h1>" +
                "<h2>" + ordrePreparation.getNom() + "</h2>" +
                "</html>";
        JOptionPane.showMessageDialog(null, ordre, "Ordre de préparation", JOptionPane.INFORMATION_MESSAGE);
    }

}
