package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.OrdrePreparation;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class DBAccess implements DataAccess
{
    private OrdrePreparation ordrePreparation;
    private Connection connection;
    private PreparedStatement statement;

    public void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException
    {
        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new AddOrdreException("Erreur connexion !");

        try
        {
            String sql = "insert into ordrepreparation (Date, NumeroSequentiel, QuantitePrevue, QuantiteProduite, DateVente, DatePreparation, Remarque, estUrgent, Nom, CodeBarre, Matricule_Cui, Matricule_Res) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(ordrePreparation.getDate().getTimeInMillis());
            statement.setDate(1, sqlDate);
            statement.setInt(2, ordrePreparation.getNumeroSequentiel());
            statement.setInt(3, ordrePreparation.getQuantitePrevue());

            if (ordrePreparation.getQuantiteProduite() != null)
            {
                statement.setInt(4, ordrePreparation.getQuantiteProduite());
            }
            else
            {
                statement.setNull(4, Types.INTEGER);
            }

            if (ordrePreparation.getDateVente() != null)
            {
                sqlDate.setTime(ordrePreparation.getDateVente().getTimeInMillis());
                statement.setDate(5, sqlDate);
            }
            else
            {
                statement.setNull(5, Types.TIMESTAMP);
            }

            if (ordrePreparation.getDatePreparation() != null)
            {
                sqlDate.setTime(ordrePreparation.getDatePreparation().getTimeInMillis());
                statement.setDate(6, sqlDate);
            }
            else
            {
                statement.setNull(6, Types.TIMESTAMP);
            }

            if (ordrePreparation.getRemarque() != null)
            {
                statement.setString(7, ordrePreparation.getRemarque());
            }
            else
            {
                statement.setNull(7, Types.VARCHAR);
            }

            statement.setBoolean(8, ordrePreparation.getEstUrgent());
            statement.setString(9, ordrePreparation.getNom());

            if (ordrePreparation.getCodeBarre() != null)
            {
                statement.setInt(10, ordrePreparation.getCodeBarre());
            }
            else
            {
                statement.setNull(10, Types.INTEGER);
            }

            if (ordrePreparation.getMatricule_Cui() != null)
            {
                statement.setInt(11, ordrePreparation.getMatricule_Cui());
            }
            else
            {
                statement.setNull(11, Types.INTEGER);
            }

            statement.setInt(12, ordrePreparation.getMatricule_Res());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new AddOrdreException("SQL erreur !");
        }
    }

    public ArrayList<OrdrePreparation> getAllOrdres () throws AllOrdresException, ModelException
    {

        ArrayList<OrdrePreparation> ordres = new ArrayList<>();

        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new AllOrdresException("Erreur connexion !");


        try {
            String sql = "SELECT * FROM dbgrandbazar.ordrepreparation order by NumeroSequentiel";
            statement = connection.prepareStatement(sql);
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
                // Peut pas utiliser le même calendar pour les 3 dates, pq ? Ou refaire un calendar = new GregorianCalendar avant l'utilisation
                GregorianCalendar calendarVente = new GregorianCalendar();
                GregorianCalendar calendarPreparation = new GregorianCalendar();

                sqlDate = data.getDate("Date");
                calendar.setTime(sqlDate);
                ordrePreparation.setDate(calendar);
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
                    calendarVente.setTime(sqlDate);
                    ordrePreparation.setDateVente(calendarVente);
                }
                // DatePreparation
                sqlDate = data.getDate("DatePreparation");
                if(! data.wasNull()){

                    calendarPreparation.setTime(sqlDate);
                    ordrePreparation.setDatePreparation(calendarPreparation);
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

                //System.out.println(ordrePreparation.getDate().getTime());
                /*if(ordrePreparation.getDateVente() !=null){
                    System.out.println(ordrePreparation.getDateVente().getTime());
                }
                if(ordrePreparation.getDatePreparation() !=null){
                    System.out.println(ordrePreparation.getDatePreparation().getTime());
                }*/
            }

        } catch (SQLException e) {
            throw new AllOrdresException(e.getMessage());
        }
        return ordres;
    }

    @Override
    public ArrayList<String> getAllRecetteNom() throws GeneralException {

        ArrayList<String> allRecetteNom = new ArrayList<>();

        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new GeneralException("les noms de recettes","Erreur connexion !");

        try {
            String sql = "SELECT Nom FROM dbgrandbazar.recette"; // Je sais pas si il faut faire le truc avec les ? mais ca me semble inutile
            statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery(); // contient les lignes de résultat de la requête

            while(data.next()) {
                allRecetteNom.add(data.getString("Nom"));
            }
        } catch (SQLException e) {
            throw new GeneralException("les noms de recettes",e.getMessage());
        }
        return allRecetteNom;
    }

    public ArrayList<String> getCodeBarres () throws GeneralException {
        ArrayList<String> allCodeBarres = new ArrayList<>();
        String typeArticle;
        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new GeneralException("les code barres","Erreur connexion !");

        try {
            String sql = "Select CodeBarre, libelle from typearticle;"; // Je sais pas si il faut faire le truc avec les ? mais ca me semble inutile
            statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery(); // contient les lignes de résultat de la requête

            while(data.next()) {
                typeArticle = data.getString("codeBarre");
                typeArticle += " ("+data.getString("Libelle")+")";
                allCodeBarres.add(typeArticle);
            }
        } catch (SQLException e) {
            throw new GeneralException("les code barres",e.getMessage());
        }
        return allCodeBarres;
    }

    public ArrayList<String> getMatriculesCui () throws GeneralException {
        ArrayList<String> allMatriculesCui = new ArrayList<>();
        String cuisinier;
        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new GeneralException("les cuisiniers","Erreur connexion !");

        try {
            String sql = "Select * From cuisinier inner join membredupersonnel on Matricule_Cui = Matricule;"; // Je sais pas si il faut faire le truc avec les ? mais ca me semble inutile
            statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery(); // contient les lignes de résultat de la requête

            while(data.next()) {
                cuisinier = ""+data.getInt("Matricule_Cui");
                cuisinier += " ("+data.getString("Nom");
                cuisinier += " "+data.getString("Prenom")+")";
                allMatriculesCui.add(cuisinier);
            }
        } catch (SQLException e) {
            throw new GeneralException(e.getMessage(),"les cuisiniers");
        }
        return allMatriculesCui;
    }

    public ArrayList<String> getMatriculesRes () throws GeneralException {
        ArrayList<String> allMatriculeRes = new ArrayList<>();
        String responsableVente;
        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new GeneralException("les responsables des ventes","Erreur connexion !");

        try {
            String sql = "Select * From responsablevente inner join membredupersonnel on MatriculeRes = Matricule;"; // Je sais pas si il faut faire le truc avec les ? mais ca me semble inutile
            statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery(); // contient les lignes de résultat de la requête

            while(data.next()) {
                responsableVente = ""+data.getInt("MatriculeRes");
                responsableVente += " ("+data.getString("Nom");
                responsableVente += " "+data.getString("Prenom")+")";
                allMatriculeRes.add(responsableVente);
            }
        } catch (SQLException e) {
            throw new GeneralException(e.getMessage(),"les responsables des ventes");
        }
        return allMatriculeRes;    }

        public Integer getNumeroSequentiel () throws GeneralException
        {
            ArrayList<Integer> numerosSquentiel = new ArrayList<>();
            Integer numeroSequentielMax = 0;

            if ((connection = SingletonConnection.getInstance()) == null)
                throw  new GeneralException("les numeros sequentiel","Erreur connexion !");

            try
            {
                String sql = "SELECT numeroSequentiel FROM dbgrandbazar.ordrePreparation";
                statement = connection.prepareStatement(sql);
                ResultSet data = statement.executeQuery(); // contient les lignes de résultat de la requête
                while(data.next()) {
                    numerosSquentiel.add(data.getInt("numeroSequentiel"));
                }
            } catch (SQLException e) {
                throw new GeneralException("les numeros sequentiel",e.getMessage());
            }

            for (Integer numeros : numerosSquentiel)
        {
            if (numeros > numeroSequentielMax)
            {
                numeroSequentielMax = numeros;
            }
        }

            return numeroSequentielMax;
        }

    @Override
    public void updateOrdre(OrdrePreparation ordrePreparation) throws UpdateOrdreException
    {
        if ((connection = SingletonConnection.getInstance()) == null)
            throw  new UpdateOrdreException("Erreur connexion !");

        try
        {
            String sql = "update ordrepreparation set Date = ?, QuantitePrevue = ?, QuantiteProduite = ?, DateVente = ?, DatePreparation = ?, Remarque = ?, estUrgent = ?, Nom = ?, CodeBarre = ?, Matricule_Cui = ?, Matricule_Res = ? where NumeroSequentiel = ?";
            statement = connection.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(ordrePreparation.getDate().getTimeInMillis());
            statement.setDate(1, sqlDate);
            statement.setInt(2, ordrePreparation.getQuantitePrevue());

            if (ordrePreparation.getQuantiteProduite() != null) {
                statement.setInt(3, ordrePreparation.getQuantiteProduite());
            }
            else
            {
                statement.setNull(3, Types.INTEGER);
            }

            if (ordrePreparation.getDateVente() != null)
            {
                sqlDate.setTime(ordrePreparation.getDateVente().getTimeInMillis());
                statement.setDate(4, sqlDate);
            }
            else
            {
                statement.setNull(4, Types.TIMESTAMP);
            }

            if (ordrePreparation.getDatePreparation() != null)
            {
                sqlDate.setTime(ordrePreparation.getDatePreparation().getTimeInMillis());
                statement.setDate(5, sqlDate);
            }
            else
            {
                statement.setNull(5, Types.TIMESTAMP);
            }

            if (ordrePreparation.getRemarque() != null)
            {
                statement.setString(6, ordrePreparation.getRemarque());
            }
            else
            {
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setBoolean(7, ordrePreparation.getEstUrgent());
            statement.setString(8, ordrePreparation.getNom());

            if (ordrePreparation.getCodeBarre() != null)
            {
                statement.setInt(9, ordrePreparation.getCodeBarre());
            }
            else
            {
                statement.setNull(9, Types.INTEGER);
            }

            if (ordrePreparation.getMatricule_Cui() != null)
            {
                statement.setInt(10, ordrePreparation.getMatricule_Cui());
            }
            else
            {
                statement.setNull(10, Types.INTEGER);
            }

            statement.setInt(11, ordrePreparation.getMatricule_Res());
            statement.setInt(12, ordrePreparation.getNumeroSequentiel());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new UpdateOrdreException("SQL error");
        }
    }
}