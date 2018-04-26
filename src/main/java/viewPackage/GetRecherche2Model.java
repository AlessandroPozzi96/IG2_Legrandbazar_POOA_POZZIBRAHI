package viewPackage;

import modelPackage.Recherche2;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class GetRecherche2Model extends AbstractTableModel
{
    private ArrayList<String> columnNames;
    private ArrayList<Recherche2> contents;

    public GetRecherche2Model(ArrayList<Recherche2> recherches2) {
        columnNames = new ArrayList<>();
        columnNames.add("DateOrdre");
        columnNames.add("NumeroSequentiel");
        columnNames.add("QuantitePrevue");
        columnNames.add("EstUrgent");
        columnNames.add("Matricule_Res");
        columnNames.add("DateTicket");
        setContents(recherches2);
    }

    @Override
    public int getRowCount() {
        return columnNames.size();
    }

    @Override
    public int getColumnCount() {
        return contents.size();
    }

    public String getColumnName(int column)
    {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recherche2 recherche2 = contents.get(rowIndex);
        switch (columnIndex)
        {
            case 0 : return recherche2.getDateOrdre().getTime();
            case 1 : return recherche2.getNumeroSequentiel();
            case 2 : return recherche2.getQuantitePrevue();
            case 3 : return recherche2.getEstUrgent();
            case 4 : return recherche2.getMatri_Res();
            case 5 : return recherche2.getDateTicket().getTime();
            default: return null;
        }
    }

    public Class getColumnClass(int column){
        Class c;
        switch (column){
            case 0 : c = Date.class;
                break;
            case 1 : c = Integer.class;
                break;
            case 2 : c = Integer.class;
                break;
            case 3 : c = Boolean.class;
                break;
            case 4 : c = Integer.class;
                break;
            case 5 : c = Date.class;
                break;
            default : c = String.class;
        }
        return c;
    }


    public void setContents(ArrayList<Recherche2> contents) {
        this.contents = contents;
    }
}
