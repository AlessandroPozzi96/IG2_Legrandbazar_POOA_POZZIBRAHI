package viewPackage;

import javax.swing.*;

public class FonctionEcouteurs
{
    public void setTextesChamps (JLabel... champs)
    {
        for (JLabel champ : champs)
        {
            champ.setText("");
        }
    }
}
