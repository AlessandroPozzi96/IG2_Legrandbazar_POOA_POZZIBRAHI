package viewPackage;

import javax.swing.*;
import java.awt.*;

public class FonctionEcouteurs
{
    //Fonctions pour éviter de duppliquer le code écouteurs dans chaque panneau
    public void setTextesChamps (JTextField... champs)
    {
        for (JTextField champ : champs)
        {
            champ.setText("");
        }
    }

    public Boolean champStringEstValide (String champ)
    {
        if (!champ.isEmpty())
            return true;
        else
            return false;
    }

    public Boolean champEntierEstValide (String champ)
    {
        int champEntier = Integer.parseInt(champ);
        if (!champ.isEmpty() && champEntier > 0)
            return true;
        else
            return false;
    }

    public void gestionChampInvalide (String nomChamp, JTextField button)
    {
        JOptionPane.showMessageDialog(null, nomChamp + " invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        button.setBackground(Color.RED);
    }
}
