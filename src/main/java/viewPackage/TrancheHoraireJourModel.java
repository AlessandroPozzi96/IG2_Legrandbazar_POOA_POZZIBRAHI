
package viewPackage;

import modelPackage.OrdrePreparation;
import modelPackage.TacheMetier;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TrancheHoraireJourModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<TacheMetier> contents;

    public TrancheHoraireJourModel(ArrayList<TacheMetier> tacheMetiers){
        columnNames = new ArrayList<>();
        columnNames.add("Tranche Horaire");
        columnNames.add("Moyenne des ordres de préparation");
        setContents(tacheMetiers);
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TacheMetier tacheMetier = contents.get(rowIndex);
        switch (columnIndex){
            case 0 : return tacheMetier.getTrancheHoraire();
            case 1 : return tacheMetier.getMoyenne();
            default: return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column){
            case 0 : c = String.class;
                break;
            case 1 : c = Double.class;
                break;
            default : c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<TacheMetier> contents) {
        this.contents = contents;
    }
}
