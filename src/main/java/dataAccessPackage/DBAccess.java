package dataAccessPackage;

import exceptionPackage.AddOrdreException;
import exceptionPackage.AllOrdresException;
import modelPackage.OrdrePreparation;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class DBAccess implements DataAccess
{
    private OrdrePreparation ordrePreparation;
    private Connection connection;

    public void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException
    {
        if (SingletonConnection.getInstance() == null)
            throw  new AddOrdreException("Erreur connexion !");
    }

    public ArrayList<OrdrePreparation> getAllOrdres () throws AllOrdresException
    {

        ArrayList<OrdrePreparation> ordres = new ArrayList<>();

        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new AllOrdresException("Erreur connexion !");


        try {
            String sql = "SELECT * FROM dbgrandbazar.ordrepreparation";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery(); // contient les lignes de résultat de la requête
            ResultSetMetaData meta = data.getMetaData(); // Contient les meta données (nb colonnes, ...)
            int quantiteProduite, matricule_Cui;
            String remarque;

            while(data.next()){

                ordrePreparation = new OrdrePreparation();
                // LES CHAMPS OBLIGATOIRES
                //DATE
                java.sql.Date sqlDate;
                GregorianCalendar calendar = new GregorianCalendar();
                sqlDate = data.getDate("Date");
                calendar.setTime(sqlDate);
                ordrePreparation.setDate(null); // --->>>>>>  DATE A NULL
                // NumeroSequentiel
                ordrePreparation.setNumeroSequentiel(data.getInt("NumeroSequentiel"));
                // QuantitePrevue
                ordrePreparation.setQuantitePrevue(data.getInt("QuantitePrevue"));
                // EstUrgent
                ordrePreparation.setEstUrgent(data.getBoolean("EstUrgent"));
                // Nom (recette)
                ordrePreparation.setNom(data.getString("Nom"));
                // CodeBarre
                ordrePreparation.setCodeBarre(data.getInt("CodeBarre"));
                // Matricule_Res
                ordrePreparation.setMatricule_Res(data.getInt("Matricule_Res"));
                // LES CHAMPS FACULTATIFS
                // QuantiteProduite
                quantiteProduite = data.getInt("QuantiteProduite");
                if(! data.wasNull()){
                    ordrePreparation.setQuantiteProduite(quantiteProduite);
                }
                // DateVente
                sqlDate = data.getDate("DateVente");
                if(! data.wasNull()){
                    calendar.setTime(sqlDate);
                    ordrePreparation.setDateVente(calendar);
                }
                // DatePreparation
                sqlDate = data.getDate("DatePreparation");
                if(! data.wasNull()){
                    calendar.setTime(sqlDate);
                    ordrePreparation.setDatePreparation(calendar);
                }
                // Remarque
                remarque = data.getString("Remarque");
                if(! data.wasNull()){
                    ordrePreparation.setRemarque(remarque);
                }
                // Matricule_Cui
                matricule_Cui = data.getInt("Matricule_Cui");
                if(! data.wasNull()){
                    ordrePreparation.setMatricule_Cui(matricule_Cui);
                }
                ordres.add(ordrePreparation);
            }

        } catch (SQLException e) {
            throw new AllOrdresException(e.getMessage());
        }


        return ordres;
    }
}

/*private Connection connection;

    {
        try
        {
            //Seb → 159357
            //Aless → Pa456lOt
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgrandbazar?useSSL=false","root","Pa456lOt");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }*/
