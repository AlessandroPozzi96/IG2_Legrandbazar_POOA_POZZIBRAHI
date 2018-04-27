package viewPackage;

import modelPackage.OrdrePreparation;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AllOrdresPreparationModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<OrdrePreparation> contents;

    public AllOrdresPreparationModel(ArrayList<OrdrePreparation> ordrePreparations){
        columnNames = new ArrayList<>();
        columnNames.add("Date");
        columnNames.add("NumeroSequentiel");
        columnNames.add("QuantitePrevue");
        columnNames.add("QuantiteProduite");
        columnNames.add("DateVente");
        columnNames.add("DatePreparation");
        columnNames.add("Remarque");
        columnNames.add("EstUrgent");
        columnNames.add("Recette");
        columnNames.add("CodeBarreArticle");
        columnNames.add("MatriculeCuisinier");
        columnNames.add("MatriculeResponsable");
        setContents(ordrePreparations);
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Object getValueAt(int rowIndex, int columnIndex) {
        OrdrePreparation ordrePreparation = contents.get(rowIndex);
        switch (columnIndex){
            case 0 : return ordrePreparation.getDate().getTime();
            case 1 : return ordrePreparation.getNumeroSequentiel();
            case 2 : return ordrePreparation.getQuantitePrevue();
            case 3 : {
                if(ordrePreparation.getQuantiteProduite() != null){
                    return ordrePreparation.getQuantiteProduite();
                }
                else return null;
            }
            case 4 : {
                if(ordrePreparation.getDateVente() != null){
                    return ordrePreparation.getDateVente().getTime();
                }
                else return null;
            }
            case 5 : {
                if(ordrePreparation.getDatePreparation() != null){
                    return ordrePreparation.getDatePreparation().getTime();
                }
                else return null;
            }
            case 6 : {
                if(ordrePreparation.getRemarque() != null){
                    return ordrePreparation.getRemarque();
                }
                else return null;
            }
            case 7: return ordrePreparation.getEstUrgent();
            case 8 : return ordrePreparation.getNom();
            case 9 : return ordrePreparation.getCodeBarre();
            case 10 : {
                if(ordrePreparation.getMatricule_Cui() != null){
                    return ordrePreparation.getMatricule_Cui();
                }
                else return null;
            }
            case 11 : return ordrePreparation.getMatricule_Res();
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
            case 3 : c = Integer.class;
                break;
            case 4 : c = Date.class;
                break;
            case 5 : c = Date.class;
                break;
            case 6 : c = String.class;
                break;
            case 7 : c = Boolean.class;
                break;
            case 8 : c = String.class;
                break;
            case 9 : c = Integer.class;
                break;
            case 10 : c = Integer.class;
                break;
            case 11 : c = Integer.class;
                break;
                default : c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<OrdrePreparation> contents) {
        this.contents = contents;
    }
}
