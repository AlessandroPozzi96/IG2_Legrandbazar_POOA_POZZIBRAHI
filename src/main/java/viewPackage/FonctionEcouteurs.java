package viewPackage;

import javax.swing.*;

public class FonctionEcouteurs
{
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
}
