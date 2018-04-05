package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class PanneauSpinnerDate extends JPanel
{
    private JSpinner spinnerMois, spinnerJours, spinnerAnnees;
    private String[] monthStrings = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
    private SpinnerListModel monthModel;
    private SpinnerNumberModel yearsModel, daysModel;
    private JLabel jour, mois, annee;

    public PanneauSpinnerDate() {
        this.setLayout(new FlowLayout());
        //Ajout des labels/spinners
        jour = new JLabel("JJ :");
        jour.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jour);
        //Le constructeur n'accepte que des Integers
        Integer min = 1;
        Integer max = 31;
        Integer value = GregorianCalendar.DAY_OF_MONTH;
        Integer step = 1;
        daysModel = new SpinnerNumberModel(value, min, max, step);
        spinnerJours = new JSpinner(daysModel);
        this.add(spinnerJours);

        mois = new JLabel("MM :");
        mois.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(mois);
        monthModel = new SpinnerListModel(monthStrings);
        spinnerMois = new JSpinner(monthModel);
        this.add(spinnerMois);

        annee = new JLabel("AAAA :");
        annee.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(annee);
        min = GregorianCalendar.YEAR - 100;
        max = GregorianCalendar.YEAR + 100;
        value = GregorianCalendar.YEAR;
        yearsModel = new SpinnerNumberModel(value, min, max, step);
        spinnerAnnees = new JSpinner(yearsModel);
        this.add(spinnerAnnees);
    }
}
