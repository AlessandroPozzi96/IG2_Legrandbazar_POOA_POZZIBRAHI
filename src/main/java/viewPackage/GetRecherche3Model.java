package viewPackage;

import modelPackage.Recherche3;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GetRecherche3Model extends AbstractTableModel
{
    private ArrayList<String> columnNames;
    private ArrayList<Recherche3> contents;

    public GetRecherche3Model(ArrayList<Recherche3> recherches3) {
        columnNames = new ArrayList<>();
        columnNames.add("Libelle");
        columnNames.add("Date");
        columnNames.add("Quantite");
        setContents(recherches3);
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column);}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void setContents(ArrayList<Recherche3> contents) {
        this.contents = contents;
    }
}
