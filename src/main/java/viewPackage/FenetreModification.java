package viewPackage;

import modelPackage.OrdrePreparation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class FenetreModification extends JFrame
{
    private ArrayList<OrdrePreparation> ordres;
    private Integer iOrdre;
    private Container frameContainer;
    private JTable jTable;
    private AllOrdresPreparationModel allOrdresPreparationModel;
    private JScrollPane jScrollPane;
    private PanneauFiller panneauFiller;

    public FenetreModification(ArrayList<OrdrePreparation> ordres, Integer iOrdre) {
        super("Affichage de l'ordre à modifier");
        setSize(new Dimension(500, 200));
        setBounds(1100, 100, 400, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        setOrdres(ordres);
        setiOrdre(iOrdre);

        //On ajoute les différents panneaux
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        panneauFiller = new PanneauFiller("<html><h3>Affichage de l'ordre à modifier</h3></html>");
        frameContainer.add(panneauFiller, BorderLayout.NORTH);

        allOrdresPreparationModel = new AllOrdresPreparationModel(getOrdres());
        jTable = new JTable(allOrdresPreparationModel);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setDefaultRenderer(Object.class, new AllOrdresPreparationModelCellRenderer());

        jScrollPane = new JScrollPane(jTable);
        frameContainer.add(jScrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public ArrayList<OrdrePreparation> getOrdres() {
        return ordres;
    }

    public void setOrdres(ArrayList<OrdrePreparation> ordres) {
        this.ordres = ordres;
    }

    public Integer getiOrdre() {
        return iOrdre;
    }

    public void setiOrdre(Integer iOrdre) {
        this.iOrdre = iOrdre;
    }

    private static  class AllOrdresPreparationModelCellRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            final Component pane = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            pane.setBackground(Color.BLUE);
            return pane;
        }
    }
}
