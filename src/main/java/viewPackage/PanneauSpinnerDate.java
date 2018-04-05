package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauSpinnerDate extends JPanel
{
    private JSpinner spinnerMois, spinnerJours, spinnerAnnees;
    private String[] monthStrings = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
    private SpinnerListModel monthModel;
    private SpinnerNumberModel yearsModel, daysModel;
    private JLabel jour, mois, annee;
    private Calendar calendar = new GregorianCalendar();

    public PanneauSpinnerDate() {
        this.setLayout(new FlowLayout());
        calendar.setTime(new Date());
        //Ajout des labels/spinners
        jour = new JLabel("JJ :");
        jour.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jour);
        //Le constructeur n'accepte que des Integers
        Integer min = 1;
        Integer max = 31;
        Integer value = calendar.get(Calendar.DAY_OF_MONTH);
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
        min = calendar.get(Calendar.YEAR) - 100;
        max = calendar.get(Calendar.YEAR) + 100;
        value = calendar.get(Calendar.YEAR);
        yearsModel = new SpinnerNumberModel(value, min, max, step);
        spinnerAnnees = new JSpinner(yearsModel);
        this.add(spinnerAnnees);
    }

    public void reinitialiserChamps ()
    {
        spinnerJours.setValue(calendar.get(Calendar.DAY_OF_MONTH));
        spinnerMois.setValue(monthStrings[0]);
        spinnerAnnees.setValue(calendar.get(Calendar.YEAR));
    }
}
