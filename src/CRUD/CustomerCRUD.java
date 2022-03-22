package CRUD;

import HelperFunctions.ConnectionDB;
import HelperFunctions.Query;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
/**
 * This class handles all database connections involving the customer class
 */
public class CustomerCRUD {
    /**
     * This method gets a single customer from the db
     * @param customerName the customer to get
     * @return the customer
     * @throws Exception
     */
    public static Customer getCustomer(String customerName) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM customers WHERE Customer_Name  = '" + customerName+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param customerResult is the user result from query
         * */
        Customer customerResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int customerIdDB=result.getInt("Customer_Id");
            String customerNameDB=result.getString("Customer_Name");
            String customerAddressDB=result.getString("Address");
            String customerPostalCodeDB=result.getString("Postal_Code");
            String customerPhoneDB=result.getString("Phone");
            Date customerCreateDateDB=result.getDate("Create_Date");
            String customerCreatedByDB=result.getString("Created_By");
            Timestamp customerLastUpdateDB=result.getTimestamp("Last_Update");
            String customerLastUpdateBy=result.getString("Last_Updated_By");
            int customerDivisionIdDB=result.getInt("Division_ID");

            customerResult= new Customer(customerIdDB,customerNameDB,customerAddressDB,customerPostalCodeDB,
                    customerPhoneDB,customerCreateDateDB,customerCreatedByDB,
                    customerLastUpdateDB,customerLastUpdateBy,customerDivisionIdDB);
            return customerResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }
    /**
     * This method creates an observable list of all customers in the database
     * */
    public static ObservableList<Customer> getAllCustomers() throws Exception{
        ObservableList<Customer> allCustomers= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select * from customers";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int customerIdDB=result.getInt("Customer_Id");
            String customerNameDB=result.getString("Customer_Name");
            String customerAddressDB=result.getString("Address");
            String customerPostalCodeDB=result.getString("Postal_Code");
            String customerPhoneDB=result.getString("Phone");
            Date customerCreateDateDB=result.getDate("Create_Date");
            String customerCreatedByDB=result.getString("Created_By");
            Timestamp customerLastUpdateDB=result.getTimestamp("Last_Update");
            String customerLastUpdateBy=result.getString("Last_Updated_By");
            int customerDivisionIdDB=result.getInt("Division_ID");

            Customer customerResult= new Customer(customerIdDB,customerNameDB,customerAddressDB,customerPostalCodeDB,
                    customerPhoneDB,customerCreateDateDB,customerCreatedByDB,
                    customerLastUpdateDB,customerLastUpdateBy,customerDivisionIdDB);
            allCustomers.add(customerResult);

        }
        ConnectionDB.closeConnection();
        return allCustomers;
    }
    /** This method adds a new customer to the Database. */
    @FXML
    public static Customer addCustomer(int customerId, String customerName, String address, String postalCode, String phone,
                            java.util.Date createDate, String createdBy, Timestamp lastUpdate,
                            String lastUpdatedBy, int divisionId) throws Exception {


            Customer newCust = new Customer(customerId,customerName,address,postalCode,phone,
                createDate,createdBy,lastUpdate,lastUpdatedBy,divisionId);

        /**
         * This Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * This Creates a mySQL query for the database
         * */
        String sqlStatement="INSERT INTO customers(Customer_Name, Address, Postal_Code , Phone,  Created_By,  Last_Updated_By, Division_ID) " +
                "VALUES('" + customerName + "', '" + address + "', '" + postalCode + "', '" + phone + "', " + "' admin', " + "  'admin' , '" + divisionId + "' )";
        Query.makeQuery(sqlStatement);
        /**
         * @param customerResult is the user result from query
         * */
        return newCust;
        }

    /** This method updates a customer in the Database. */
    @FXML
    public static Customer updateCustomer(int customerId, String customerName, String address, String postalCode, String phone,
                                       java.util.Date createDate, String createdBy, Timestamp lastUpdate,
                                       String lastUpdatedBy, int divisionId) throws Exception {


        Customer updatedCust = new Customer(customerId,customerName,address,postalCode,phone,
                createDate,createdBy,lastUpdate,lastUpdatedBy,divisionId);

        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement= "UPDATE customers SET Customer_Name = '" + customerName + "', Address = '" + address + "'," +
                " Postal_Code = '" + postalCode + "', Phone = '" + phone + "', Division_ID = '" + divisionId +
                "'  WHERE Customer_ID = " + customerId + "";
        Query.makeQuery(sqlStatement);
        /**
         * @param customerResult is the user result from query
         * */
        return updatedCust;
    }
    /**This method deletes a customer from the database*/
    @FXML
    public static Customer deleteCustomer(String customerName) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="DELETE FROM customers WHERE Customer_Name  = '" + customerName+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param customerResult is the user result from query
         * */
        ResultSet result=Query.getResult();
        ConnectionDB.closeConnection();
        return null;
    }
}

