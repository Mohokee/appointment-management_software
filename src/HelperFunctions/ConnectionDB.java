package HelperFunctions;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class makes a database connection
 */
public class ConnectionDB {
    /**
     * Database Connection Class Credentials
     * */
    private static final String databaseName="WJ08dz6";
    private static final String DB_URL="jdbc:mysql://127.0.0.1:3306/" + databaseName;
    private static final String username="U08dz6";
    private static final String password="53689261894";
    private static final String driver="com.mysql.jdbc.Driver";

    /**
     * Establishes a database connection
     * */
    public static Connection conn;
    /**
     * Make a connection
     * */
    public static void makeConnection()throws Exception
    {
        Class.forName(driver);
        conn= DriverManager.getConnection(DB_URL,username,password);
        System.out.println("Connection Successful");
    }

    /**
     *  Close the connection
     **/
    public static void closeConnection()throws Exception{
        conn.close();
        System.out.println("Connection Closed");
    }
}
