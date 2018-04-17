package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauSpinnerDate extends JPanel
{
    private JSpinner spinnerDate;
    private SpinnerDateModel spinnerDateModel;
    private Calendar calendar;

    public PanneauSpinnerDate() {
        this.setLayout(new BorderLayout());
        //Version avec un modelDate
        calendar = Calendar.getInstance();
        spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setValue(calendar.getTime());
        spinnerDate = new JSpinner(spinnerDateModel);
        this.add(spinnerDate, BorderLayout.WEST);
    }

    public void reinitialiserChamps ()
    {
        calendar = Calendar.getInstance();
        spinnerDate.setValue(calendar.getTime());
    }

    public GregorianCalendar getDate ()
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime((Date) spinnerDate.getValue());
        return (GregorianCalendar) cal;
    }
}

        /*
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
        this.add(spinnerAnnees);*/