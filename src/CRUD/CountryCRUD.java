package CRUD;

import HelperFunctions.ConnectionDB;
import HelperFunctions.Query;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
/**
 * This class handles all database connections involving the country class
 */
public class CountryCRUD {
    /**This method gets a single specific country from the database**/
    public static Country getCountry(String country) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM countries WHERE Country_Name  = '" + country+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param countryResult is the user result from query
         * */
        Country countryResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int countryIdDB=result.getInt("Country_Id");
            String countryNameDB=result.getString("Country_Name");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");

            countryResult= new Country(countryIdDB,countryNameDB,createDate,createdBy,lastUpdate,lastUpdateBy);
            return countryResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }

    /** This method gets all Countries from the database */
    public static ObservableList<Country> getAllCountries() throws Exception{
        ObservableList<Country> allCountries= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select * from countries";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int countryIdDB=result.getInt("Country_Id");
            String countryNameDB=result.getString("Country");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");

            Country countryResult= new Country(countryIdDB,countryNameDB,createDate,createdBy,lastUpdate,lastUpdateBy);

            allCountries.add(countryResult);

        }
        ConnectionDB.closeConnection();
        return allCountries;
    }

    /** This method gets all Country names from the database */
    public static ObservableList<String> getAllCountryNames() throws Exception{
        ObservableList<String> countryNames= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select Country from countries";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            String countryResult= result.getString("Country");

           countryNames.add(countryResult);

        }
        ConnectionDB.closeConnection();
        return countryNames;
    }


}
