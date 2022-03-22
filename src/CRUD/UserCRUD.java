package CRUD;

import HelperFunctions.ConnectionDB;
import HelperFunctions.Query;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
/**
 * This class handles all database connections involving the user class
 */
public class UserCRUD {
    public static User getUser(String userName) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM users WHERE User_Name  = '" + userName+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param userResult is the user result from query
         * */
        User userResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("User_Id");
            String usersName=result.getString("User_Name");
            String password=result.getString("Password");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");

            userResult= new User(userid,usersName,password,createDate,createdBy,lastUpdate,lastUpdateBy);
            return userResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }
    /**
     * This class gets all users from the database
     */
    public static ObservableList<User> getAllUsers() throws Exception{
        ObservableList<User> allUsers=FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("User_Id");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdatedBy=result.getString("Last_Updated_By");

            User userResult= new User(userid, userNameG, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            allUsers.add(userResult);

        }
        ConnectionDB.closeConnection();
        return allUsers;
    }


}
