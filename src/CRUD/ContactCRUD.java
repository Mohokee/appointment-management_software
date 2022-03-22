package CRUD;

import HelperFunctions.ConnectionDB;
import HelperFunctions.Query;
import HelperFunctions.TimeFunctions;
import Model.Contact;
import Model.ContactSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class handles all database connections involving the contact class
 */
public class ContactCRUD {
    /**This method gets a single specific contact from the database**/
    public static Contact getContact(String contactName) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM contacts WHERE Contact_Name  = '" + contactName+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param contactResult is the user result from query
         * */
        Contact contactResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int contactId=result.getInt("Contact_Id");
            String contactNameDB=result.getString("Contact_Name");
            String email=result.getString("email");

            contactResult= new Contact(contactId,contactNameDB,email);
            return contactResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }

    /** This method gets all Contacts from the database */
    public static ObservableList<Contact> getAllContacts() throws Exception{
        ObservableList<Contact> allContacts= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select * from contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int contactId=result.getInt("Contact_ID");
            String contactName=result.getString("Contact_Name");
            String email=result.getString("Email");


            Contact contactResult= new Contact(contactId,contactName,email);
            allContacts.add(contactResult);

        }
        ConnectionDB.closeConnection();
        return allContacts;
    }

    /** This method gets all contact names from the database
     * */
    public static ObservableList<String> getAllContactNames() throws Exception{
        ObservableList<String> contactNames= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Contact_Name FROM contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            String stateResult= result.getString("Contact_Name");

            contactNames.add(stateResult);

        }
        ConnectionDB.closeConnection();
        return contactNames;
    }

    /**This method gets a single specific contact from the database based on an ID
     * @param contactID the contact if to get the name of
     * **/
    public static String getContactFromID(int contactID) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM contacts WHERE Contact_ID  = '" + contactID+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param contactResult is the user result from query
         * */
        String contactResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            String contactNameDB=result.getString("Contact_Name");

            contactResult= contactNameDB;
            return contactResult;
        }
        ConnectionDB.closeConnection();
        return null;
    }

    /**This method gets a single specific contact from the database**/
    public static Integer getContactIDFromName(String contactName) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="select * FROM contacts WHERE Contact_Name  = '" + contactName+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param contactResult is the user result from query
         * */
        int contactResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int contactId=result.getInt("Contact_Id");

            contactResult= contactId;
            return contactId;
        }
        ConnectionDB.closeConnection();
        return null;
    }

    /** This method gets individual appointments from the database for the Contact schedule report
     * @param contactID the contact to get appointments for
     * */
    public static ObservableList<ContactSchedule> getContactSchedule(int contactID) throws Exception{
        ObservableList<ContactSchedule> contactAppointments= FXCollections.observableArrayList();
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="SELECT * FROM appointments WHERE Contact_ID  = '" + contactID+ "'";
        Query.makeQuery(sqlStatement);
        /**
         * @param contactResult is the contact result from query
         * */
        ContactSchedule contactResult;

        ResultSet result=Query.getResult();
        while(result.next()){
            int appointmentId=result.getInt("Appointment_ID");
            String title=result.getString("Title");
            String description=result.getString("Description");
            String location=result.getString("Location");
            String type= result.getString("Type");
            Timestamp start= TimeFunctions.UTCtoLocal(result.getTimestamp("Start"));
            Timestamp end = TimeFunctions.UTCtoLocal(result.getTimestamp("End"));
            int customerId=result.getInt("Customer_ID");
            int userId=result.getInt("User_ID");
            int contactId=result.getInt("Contact_ID");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mm:ss z");

            LocalDateTime starting = start.toLocalDateTime();
            LocalDateTime ending = end.toLocalDateTime();

            ZonedDateTime s = starting.atZone(ZoneId.systemDefault());
            ZonedDateTime e = ending.atZone(ZoneId.systemDefault());

            e.format(formatter);
                contactResult= new ContactSchedule(appointmentId,title,description,location,type,
                        s,e,customerId,contactId);
                contactAppointments.add(contactResult);
        }
        ConnectionDB.closeConnection();
        return contactAppointments;
    }
}
