package CRUD;

import HelperFunctions.ConnectionDB;
import HelperFunctions.Query;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * This class handles all database connections involving the First Level Division class
 */
public class FirstLevelDivisionCRUD {
    /**This method gets a single specific first level division from the database**/
    public static FirstLevelDivision getState(String state) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM first_level_divisions WHERE Division  = '" + state+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param contactResult is the user result from query
         * */
        FirstLevelDivision stateResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int stateIdDB=result.getInt("Division_ID");
            String stateNameDB=result.getString("Division");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");
            int stateCountryId = result.getInt("COUNTRY_ID");

            stateResult= new FirstLevelDivision(stateIdDB,stateNameDB,createDate,createdBy,lastUpdate,lastUpdateBy,stateCountryId);
            return stateResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }

    /** This method gets all FLD'S from the database */
    public static ObservableList<FirstLevelDivision> getAllFLDs() throws Exception{
        ObservableList<FirstLevelDivision> allFLD= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select * from first_level_divisions";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int stateIdDB=result.getInt("Division_ID");
            String stateNameDB=result.getString("Division");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");
            int stateCountryId = result.getInt("COUNTRY_ID");

            FirstLevelDivision stateResult= new FirstLevelDivision(stateIdDB,stateNameDB,createDate,createdBy,lastUpdate,lastUpdateBy,stateCountryId);

            allFLD.add(stateResult);

        }
        ConnectionDB.closeConnection();
        return allFLD;
    }

    /** This method gets all Division names from the database
     * @param countryID gets the first level divisions for the country selected through ID
     * */
    public static ObservableList<String> getAllFLDNames(int countryID) throws Exception{
        ObservableList<String> FLDNames= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Division FROM first_level_divisions WHERE COUNTRY_ID = " + countryID;
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            String stateResult= result.getString("Division");

            FLDNames.add(stateResult);

        }
        ConnectionDB.closeConnection();
        return FLDNames;
    }

    /** This method gets all Division names from the database
     * @param state gets the int ID for first level division selected by string
     * */
    public static int getFLDId(String state) throws Exception{
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Division_ID FROM first_level_divisions WHERE Division = '" + state+ "'";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            int stateResult= result.getInt("Division_ID");

            return stateResult;
        }
        ConnectionDB.closeConnection();
        return Integer.parseInt(null);
    }

    public static String getFLD(int stateId) throws Exception{
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Division FROM first_level_divisions WHERE Division_ID = " + stateId;
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            String stateResult= result.getString("Division");

            return stateResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }
}
