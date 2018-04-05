package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauSpinnerDate extends JPanel
{
    private JSpinner spinner;
    private String[] monthStrings = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
    private SpinnerListModel monthModel;
    private SpinnerNumberModel yearsModel, daysModel;

    public PanneauSpinnerDate() {
        this.setLayout(new FlowLayout());

    }
}
