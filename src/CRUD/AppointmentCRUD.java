package CRUD;

import HelperFunctions.ConnectionDB;
import HelperFunctions.Query;
import HelperFunctions.TimeFunctions;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class handles all database connections involving the appointment and appointment report classes
 */
public class AppointmentCRUD {

    /** This method gets all appointments from the database */
    public static ObservableList<Appointment> getAllAppointments() throws Exception{
        ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select * from appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();


        while(result.next()){
            int appointmentId=result.getInt("Appointment_ID");
            String title=result.getString("Title");
            String description=result.getString("Description");
            String location=result.getString("Location");
            String type=result.getString("Type");
            Timestamp start= TimeFunctions.UTCtoLocal(result.getTimestamp("Start"));
            Timestamp end= TimeFunctions.UTCtoLocal(result.getTimestamp("End"));
            int customerId=result.getInt("Customer_ID");
            int userId=result.getInt("User_ID");
            int contactId=result.getInt("Contact_ID");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");


            Appointment appointmentResult= new Appointment(appointmentId,title,description,location,type,
                    start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,
                    userId,contactId);
            allAppointments.add(appointmentResult);

        }
        ConnectionDB.closeConnection();
        return allAppointments;
    }

    /** This method gets weekly appointments from the database */
    public static ObservableList<Appointment> getWeeklyAppointments() throws Exception{
        ObservableList<Appointment> weeklyAppointments= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date,Created_By,Last_Update,Last_Updated_By, Customer_ID, User_ID,Contact_ID  FROM appointments " +
                " WHERE Start > CURDATE() AND Start < CURDATE() + INTERVAL 7 DAY;";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int appointmentId=result.getInt("Appointment_ID");
            String title=result.getString("Title");
            String description=result.getString("Description");
            String location=result.getString("Location");
            String type=result.getString("Type");
            Timestamp start= TimeFunctions.UTCtoLocal(result.getTimestamp("Start"));
            Timestamp end= TimeFunctions.UTCtoLocal(result.getTimestamp("End"));
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");
            int customerId=result.getInt("Customer_ID");
            int userId=result.getInt("User_ID");
            int contactId=result.getInt("Contact_ID");


            Appointment appointmentResult= new Appointment(appointmentId,title,description,location,type,
                    start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,
                    userId,contactId);

            weeklyAppointments.add(appointmentResult);

        }
        ConnectionDB.closeConnection();
        return weeklyAppointments;
    }


    /** This method gets appointments from the current month from the database */
    public static ObservableList<Appointment> getMonthlyAppointments() throws Exception{
        ObservableList<Appointment> monthlyAppointments= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="select Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date,Created_By,Last_Update,Last_Updated_By, Customer_ID, User_ID,Contact_ID  FROM appointments " +
                " where Start > curdate() and Start < CURDATE() + interval 30 day;";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int appointmentId=result.getInt("Appointment_ID");
            String title=result.getString("Title");
            String description=result.getString("Description");
            String location=result.getString("Location");
            String type=result.getString("Type");
            Timestamp start= TimeFunctions.UTCtoLocal(result.getTimestamp("Start"));
            Timestamp end= TimeFunctions.UTCtoLocal(result.getTimestamp("End"));
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");
            int customerId=result.getInt("Customer_ID");
            int userId=result.getInt("User_ID");
            int contactId=result.getInt("Contact_ID");


            Appointment appointmentResult= new Appointment(appointmentId,title,description,location,type,
                    start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,
                    userId,contactId);

            monthlyAppointments.add(appointmentResult);

        }
        ConnectionDB.closeConnection();
        return monthlyAppointments;
    }

    /**
     *
     * @param appointmentId ID of the appointment to delete
     * @return
     * @throws Exception
     */
    public static Appointment deleteAppointment(int appointmentId) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="DELETE FROM appointments WHERE Appointment_ID  = " + appointmentId;
        Query.makeQuery(sqlStatement);
        /**
         * @param result is the result from query
         * */
        ResultSet result=Query.getResult();
        ConnectionDB.closeConnection();
        return null;
    }

    /**
     * This code deletes appointments associated with a customer prior to customer deletion
     * @param custId the customer to delete appointments for
     * @return
     * @throws Exception connects to DB
     */
    public static Appointment deleteCustAppointment(int custId) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="DELETE FROM appointments WHERE Customer_ID  = " + custId;
        Query.makeQuery(sqlStatement);

        ConnectionDB.closeConnection();
        return null;
    }

    /** This method gets all appointments from the database */
    /**
     *
     * @param custId gets all appointments for a specific customer
     * @return
     * @throws Exception
     */
    public static ObservableList<Appointment> getCustAppointments(int custId) throws Exception{
        ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT FROM appointments WHERE Customer_ID = " + custId;
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int appointmentId=result.getInt("Appointment_ID");
            String title=result.getString("Title");
            String description=result.getString("Description");
            String location=result.getString("Location");
            String type=result.getString("Type");
            Timestamp start= TimeFunctions.UTCtoLocal(result.getTimestamp("Start"));
            Timestamp end= TimeFunctions.UTCtoLocal(result.getTimestamp("End"));
            int customerId=result.getInt("Customer_ID");
            int userId=result.getInt("User_ID");
            int contactId=result.getInt("Contact_ID");
            Date createDate=result.getDate("Create_Date");
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            String lastUpdateBy=result.getString("Last_Updated_By");

            Appointment appointmentResult= new Appointment(appointmentId,title,description,location,type,
                    start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,
                    userId,contactId);
            allAppointments.add(appointmentResult);
        }
        ConnectionDB.closeConnection();
        return allAppointments;
    }


    /** This method gets all appointment types from the database
     * */
    public static ObservableList<String> getAllAppointmentTypes() throws Exception{
        ObservableList<String> apptTypes= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Type FROM appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            String typeResult= result.getString("Type");

            if(!apptTypes.contains(typeResult)){
                apptTypes.add(typeResult);
            }

        }
        ConnectionDB.closeConnection();
        return apptTypes;
    }

    /** This method gets all user id's from the database
     * */
        public static ObservableList<Integer> getAllUserIds() throws Exception{
        ObservableList<Integer> userId= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT User_ID FROM appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            int idResult= result.getInt("User_ID");

            if(!userId.contains(idResult)){
                userId.add(idResult);
            }

        }
        ConnectionDB.closeConnection();

        return userId;
    }

    /** This method gets all customer id's from the database
     * */
    public static ObservableList<Integer> getAllCustomerIds() throws Exception{
        ObservableList<Integer> customerId= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Customer_ID FROM customers";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            int idResult= result.getInt("Customer_ID");
            customerId.add(idResult);

        }
        ConnectionDB.closeConnection();

        return customerId;
    }


    /**
    /**
     * This method adds a new appointment to the Database
     * @param appointmentId new appointment's ID
     * @param title new appointment's title
     * @param description new appointment's description
     * @param location new appointment's location
     * @param type new appointment's type
     * @param start new appointment's start
     * @param end new appointment's end
     * @param createDate new appointment's creation date
     * @param createdBy name of user who created appointment
     * @param lastUpdate new appointment's last update date
     * @param lastUpdatedBy user who updated new appointment last
     * @param customerId new appointment's customer ID
     * @param userId new appointment's user ID
     * @param contactId new appointment's contact ID
     * @return
     * @throws Exception
     */
    @FXML
    public static Appointment addAppointment(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end,
                                             Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) throws Exception {


        Appointment newAppt = new Appointment(appointmentId, title, description, location, type, start, end,
                createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);

        LocalDateTime finalStart = start.toLocalDateTime();
        LocalDateTime finalEnd = end.toLocalDateTime();

        System.out.println(finalStart);
        System.out.println(finalEnd);

        /**
         * This Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * This Creates a mySQL query for the database
         * */
        String sqlStatement="INSERT INTO appointments(Title, Description, Location,  Type,  Start, End, Customer_ID, User_ID,Contact_ID) " +
                "VALUES('" + title + "', '" + description + "', '" + location + "', '" + type + "', '" + finalStart + "', '" + finalEnd + "','" + customerId + "', '" + userId + "' , '" + contactId + "')";
        Query.makeQuery(sqlStatement);

        ConnectionDB.closeConnection();
        return newAppt;
    }

     /**
     * This method deletes an appointment from the database
     * @param title The title of the appointment to delete
     * @return
     * @throws Exception
     */
    @FXML
    public static Customer deleteAppointment(String title) throws Exception{
        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement="DELETE FROM appointments WHERE Title  = '" + title+ "'";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        ConnectionDB.closeConnection();
        return null;
    }

    /**This method checks the database for overlapping appointments.
     * @param start
     * @param  end
     * @param custID
     * */
    public static ObservableList<Appointment> checkOverlap(Timestamp start, Timestamp end, int custID) throws Exception {
        ObservableList<Appointment> overlap = FXCollections.observableArrayList();
        ObservableList<Appointment> apptList = AppointmentCRUD.getCustomerAppointments(custID);

        for (Appointment a : apptList) {
                if (start.after(a.getStart()) && start.before(a.getEnd()) ||
                        end.after(a.getStart()) && end.before(a.getEnd()) ||
                        start.before(a.getStart()) && end.after(a.getStart()) ||
                        start.equals(a.getStart()) && end.equals(a.getEnd()) ||
                        start.equals(a.getStart()) || end.equals(a.getStart())) {

                    overlap.addAll(a);
                }

        }
        ConnectionDB.closeConnection();
        return overlap;
    }


    /**This method checks the database for overlapping appointments when updating.
     * @param start the customer input start time to check
     * @param  end the customer input end time to check
     * @param custID the customer id that needs to be checked for overlapping appointments
     * @param apptID this variable makes sure that the appointment we are trying to update isn't included in checking for overlaps
     * */
    public static ObservableList<Appointment> checkOverlapUpdate(Timestamp start, Timestamp end, int custID, int apptID) throws Exception {
        ObservableList<Appointment> overlap = FXCollections.observableArrayList();

        ObservableList<Appointment> apptList = AppointmentCRUD.getCustomerAppointments(custID);
        for (Appointment a : apptList) {
            if(a.getAppointmentId() != apptID) {
                if (start.after(a.getStart()) && start.before(a.getEnd()) ||
                        end.after(a.getStart()) && end.before(a.getEnd()) ||
                        start.before(a.getStart()) && end.after(a.getStart()) ||
                        start.equals(a.getStart()) && end.equals(a.getEnd()) ||
                        start.equals(a.getStart()) || end.equals(a.getStart())) {

                    overlap.addAll(a);
                }
            }
        }
        return overlap;
    }

    /**This method gets all appointments from the database with customer ID
     * @param custID**/
    public static ObservableList<Appointment> getCustomerAppointments(int custID) throws Exception {

        ObservableList<Appointment> custAppts = FXCollections.observableArrayList();

        for (Appointment appointment : getAllAppointments()){
            if (appointment.getCustomerId() == custID){
                custAppts.add(appointment);
            }
        }
        return custAppts;
    }

    /** This method updates an appointment in the Database. */
    @FXML
    public static Appointment updateAppointment(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end,
                                                Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) throws Exception {


        Appointment updatedAppt = new Appointment(appointmentId, title, description, location, type, start, end,
                createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);

        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        /**
         * Creates a mySQL query for the database
         * */
        String sqlStatement= "UPDATE appointments SET Title = '" + title + "', Description = '" + description +
                "', Location = '" + location + "', Type = '" + type + "', Start = '" +
                start.toLocalDateTime() + "', End = '" + end.toLocalDateTime() +
                "', Customer_ID = '" + customerId + "', User_ID = '" + userId + "', Contact_ID = '" + contactId +
                "' WHERE Appointment_ID = '" + appointmentId + "'";
        Query.makeQuery(sqlStatement);

        return updatedAppt;
    }

    /** This method gets all customer id's from the database
     * */
    public static ObservableList<LocalDateTime> getStartTime() throws Exception{
        ObservableList<LocalDateTime> startingTimes= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Start FROM appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){

            Timestamp startStamp= TimeFunctions.UTCtoLocal(result.getTimestamp("Start"));
            LocalDateTime start = startStamp.toLocalDateTime();
            startingTimes.add(start);

        }
        ConnectionDB.closeConnection();

        return startingTimes;
    }

    /**
     * Reports
     */

    /** This method gets appointments from the database based on location
     * @param location location from combo box selection
     * */
    public static ObservableList<AppointmentByLocation> prepareLocationReport(String location) throws Exception {

        ObservableList<AppointmentByLocation> abl = FXCollections.observableArrayList();

        String sqlStatement = "SELECT Appointment_ID,Location FROM appointments WHERE Location = '"+location+"'";
        ConnectionDB.makeConnection();
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();

        /**
         * This code gets the resulting Appointment ID's sorted by location
         */
        while (result.next()) {
            int apptID = result.getInt("Appointment_ID");
            String locations = result.getString("Location");


            AppointmentByLocation ablReport = new AppointmentByLocation(apptID,locations);
            abl.add(ablReport);
        }
        ConnectionDB.closeConnection();
        return abl;
    }

    /** This method gets all locations from the Database
     * */
        public static ObservableList<String> getAllLocations() throws Exception {

        ObservableList<String> locations = FXCollections.observableArrayList();

        String sqlStatement = "SELECT DISTINCT Location FROM appointments GROUP BY Location";
        ConnectionDB.makeConnection();
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();

        /**
         * This code gets the resulting Appointment ID's sorted by location
         */
        while (result.next()) {
            String location = result.getString("Location");
            locations.add(location);
        }
        ConnectionDB.closeConnection();
        return locations;
    }

    /**
     * This method gets the type of appointments from the database and sort them by months for the report
     * @param selectedMonth month to prepare report from
     */
    public static ObservableList<MonthTypeReport> prepareMonthTypeReport(String selectedMonth) throws Exception {

        ObservableList<MonthTypeReport> mtr = FXCollections.observableArrayList();
        String sqlStatement = "SELECT COUNT(Title) AS Total,Type,MONTHNAME(Start) AS month,MONTH(Start) AS Month FROM appointments WHERE MONTHNAME(Start) = '" + selectedMonth + "' GROUP BY MONTH(Start),month,Type ORDER BY Month;";

        /**
         * Connects to database
         * */
        ConnectionDB.makeConnection();
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();


        while (result.next()) {
            String month = result.getString("Month");
            String type = result.getString("Type");
            int total = result.getInt("Total");

            MonthTypeReport mtreport = new MonthTypeReport(month, type, total);
            mtr.add(mtreport);
        }
        return mtr;
    }

    /** This method gets appointments from the database that are starting within 15 minutes*/
    public static ObservableList<UpcomingAppointment> getUpcomingAppointments() throws Exception{
        ObservableList<UpcomingAppointment> upcomingAppointments= FXCollections.observableArrayList();
        ConnectionDB.makeConnection();
        String sqlStatement="SELECT Appointment_ID, Start FROM appointments WHERE Start BETWEEN NOW() AND ADDDATE(NOW(),INTERVAL 15 MINUTE) ORDER BY Start ASC";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int appointmentId=result.getInt("Appointment_ID");
            Timestamp start= result.getTimestamp("Start");

            LocalDateTime s = start.toLocalDateTime();
            ZonedDateTime z = s.atZone(ZoneId.systemDefault());


            UpcomingAppointment appointmentResult= new UpcomingAppointment(appointmentId, z);

            upcomingAppointments.add(appointmentResult);
        }
        ConnectionDB.closeConnection();
        System.out.println(upcomingAppointments);
        return upcomingAppointments;

    }
}

