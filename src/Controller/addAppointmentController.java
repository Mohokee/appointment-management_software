package Controller;

import CRUD.AppointmentCRUD;
import CRUD.ContactCRUD;
import HelperFunctions.checkForBlanks;
import HelperFunctions.validateAppointment;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * This controller enables the user to add appointments to the database and is connected to the "addAppointment" view
 */
public class addAppointmentController implements Initializable {
    /**
     * Alerts are initialized here
     */
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert scheduleError = new Alert(Alert.AlertType.ERROR);
    Alert overlap = new Alert(Alert.AlertType.ERROR);

    /**
     * All Appointment Table imports
     **/
    @FXML
    private TableView allAppointmentsTable;
    /**
     * Appointment Table column imports
     **/
    @FXML
    private TableColumn allApptId;
    @FXML
    private TableColumn allApptTitle;
    @FXML
    private TableColumn allApptDescript;
    @FXML
    private TableColumn allApptLocation;
    @FXML
    private TableColumn allApptContact;
    @FXML
    private TableColumn allApptType;
    @FXML
    private TableColumn allApptStart;
    @FXML
    private TableColumn allApptEnd;
    @FXML
    private TableColumn allApptCustId;

    /**
     * Text Field Imports
     **/
    @FXML
    public TextField addAppt;
    @FXML
    public TextField addTitle;
    @FXML
    public TextField addDesc;
    @FXML
    public TextField addLocation;
    @FXML
    private ComboBox addContact;
    @FXML
    private ComboBox addType;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox year;
    @FXML
    private ComboBox hour;
    @FXML
    private ComboBox minute;
    @FXML
    private ComboBox endHour;
    @FXML
    private ComboBox endMinute;
    @FXML
    private ComboBox custId;
    @FXML
    private ComboBox userId;

    @FXML
    private Label appointmentAddedMessage;


    /**
     * This method returns the user to the main page
     *
     * @param event backToMain
     */
    public void backToMain(Event event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/mainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /***
     * This method adds the appointment to the database and displays a message when adding is successful
     * */
    public void addAppointment(Event event) throws Exception {
        int addAppt = 0;
        String addTitles = addTitle.getText();
        String addDescs = addDesc.getText();
        String addLocations = addLocation.getText();
        String addContacts = String.valueOf((addContact.getValue()));
        String addTypes = String.valueOf(addType.getValue());
        if (custId.getValue() == null || userId.getValue() == null) {
            alert.setContentText("User ID and Customer ID's are both required");
            alert.showAndWait();
        } else {
            int addCustIds = Integer.parseInt(String.valueOf(custId.getValue()));
            int addUserIds = Integer.parseInt(String.valueOf(userId.getValue()));

            Timestamp timestamp = Timestamp.from(Instant.now());
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());


            /**These help check if any fields are blank, and if so, will warn the user
             * */
            ObservableList<TextField> textFields = FXCollections.observableArrayList();
            textFields.addAll(addTitle, addDesc, addLocation);

            ObservableList<ComboBox> comboBoxes = FXCollections.observableArrayList();
            comboBoxes.addAll(addContact, addType, month, day, year, hour, minute, endHour, endMinute, custId, userId);

            if (checkForBlanks.check(textFields) || checkForBlanks.checkCombos(comboBoxes)) {
                return;
            } else {

                /**This method converts the user inputed time information into a timestamp, then a string that is
                 * converted to a local datetime object
                 * */
                /**Date input collection*/
                String months = String.valueOf((month.getValue()));
                String days = String.valueOf((day.getValue()));
                String years = String.valueOf((year.getValue()));

                /**Start time input collection*/
                String hours = String.valueOf((hour.getValue()));
                String minutes = String.valueOf((minute.getValue()));

                /**End time input collection */
                String endHours = String.valueOf(endHour.getValue());
                String endMinutes = String.valueOf((endMinute.getValue()));

                /**
                 * These are strings from user input that will be converted into time
                 * */
                String startStamp = years + "-" + months + "-" + days + " " + hours + ":" + minutes + ":" + "00";
                String endStamp = years + "-" + months + "-" + days + " " + endHours + ":" + endMinutes + ":" + "00";

                /**Datetime formatter helps format time for a more readable AM/PM format*/

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                DateTimeFormatter twentyFour = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                /***ZonedDateTimes for displaying AM/PM local time*/
                ZonedDateTime startingTime = LocalDateTime.parse(startStamp, formatter).atZone(ZoneId.systemDefault());
                ZonedDateTime endingTime = LocalDateTime.parse(endStamp, formatter).atZone(ZoneId.systemDefault());

                /**Local Time and LocalDate information*/
                LocalTime desiredStart = startingTime.toLocalTime();
                LocalTime desiredEnd = endingTime.toLocalTime();

                LocalDate localStartingDate = startingTime.toLocalDate();
                LocalDate localEndDate = endingTime.toLocalDate();


                /**These are UTC conversions*/
                ZonedDateTime UTCStart = startingTime.withZoneSameInstant(ZoneId.of("UTC"));
                ZonedDateTime UTCEnd = endingTime.withZoneSameInstant(ZoneId.of("UTC"));

                LocalDateTime convertStart = UTCStart.toLocalDateTime();
                LocalDateTime convertEnd = UTCEnd.toLocalDateTime();

                /**Timestamp Conversions for saving to Appointment object*/
                Timestamp startee = Timestamp.valueOf(convertStart);
                Timestamp endee = Timestamp.valueOf(convertEnd);

                Timestamp stampStart = Timestamp.valueOf(LocalDateTime.of(localStartingDate, desiredStart));
                Timestamp stampEnd = Timestamp.valueOf(LocalDateTime.of(localEndDate, desiredEnd));

                ZonedDateTime zoneStart = startingTime.withZoneSameInstant(ZoneId.systemDefault());
                ZonedDateTime zoneEnd = endingTime.withZoneSameInstant(ZoneId.systemDefault());


                /**These variables are for checking that appointment times are within business hours of 8am-10pm Eastern 7 days a week*/
                ZonedDateTime startEastern = ZonedDateTime.of(startingTime.getYear(), startingTime.getMonthValue(), startingTime.getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
                ZonedDateTime endEastern = ZonedDateTime.of(endingTime.getYear(), endingTime.getMonthValue(), endingTime.getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
                ZonedDateTime localBusinessStart = startEastern.withZoneSameInstant(ZoneId.systemDefault());
                ZonedDateTime localBusinessEnd = endEastern.withZoneSameInstant(ZoneId.systemDefault());

                if (validateAppointment.validateAppointmentUpdate(zoneStart, zoneEnd, localBusinessStart, localBusinessEnd)) {
                    System.out.println("Appointment Invalid");
                    return;
                } else {

                /**This checks for conflicting appointments*/
                ObservableList<Appointment> conflict = AppointmentCRUD.checkOverlap(stampStart, stampEnd, addCustIds);
                if (!conflict.isEmpty()) {
                    for (Appointment a : conflict) {
                        alert.setContentText("Appointment " + a.getAppointmentId() + "\r\n" + " has conflicting times with this appointment " +
                                "\r\n" + "Please select a different time");
                        alert.showAndWait();
                    }
                } else {
                    int addContactId;

                    if (addContacts.equals("Anika Costa")) {
                        addContactId = 1;
                        AppointmentCRUD.addAppointment(addAppt, addTitles, addDescs, addLocations, addTypes, startee, endee, date, "test", timestamp, "test", addCustIds, addUserIds, addContactId);
                        appointmentAddedMessage.setText("Appointment Added");
                        allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());
                    } else if (addContacts.equals("Daniel Garcia")) {
                        addContactId = 2;
                        AppointmentCRUD.addAppointment(addAppt, addTitles, addDescs, addLocations, addTypes, startee, endee, date, "test", timestamp, "test", addCustIds, addUserIds, addContactId);
                        appointmentAddedMessage.setText("Appointment Added");
                        allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());
                    } else if (addContacts.equals("Li Lee")) {
                        addContactId = 3;
                        AppointmentCRUD.addAppointment(addAppt, addTitles, addDescs, addLocations, addTypes, startee, endee, date, "test", timestamp, "test", addCustIds, addUserIds, addContactId);
                        appointmentAddedMessage.setText("Appointment Added");
                        allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());
                    }
                }
            }
        }
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**This code populates the hours and minutes combo box*/
        ObservableList<String> hours = FXCollections.observableArrayList();
        ObservableList<String> minutes = FXCollections.observableArrayList();
        ObservableList<String> days = FXCollections.observableArrayList();
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> years = FXCollections.observableArrayList();

        hours.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12","13","14","15","16","17","18","19","20","21","22","23","00");
        minutes.addAll("00", "15", "30", "45");
        days.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        months.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12");
        years.addAll("2021", "2022");
        hour.setItems(hours);
        minute.setItems(minutes);

        endHour.setItems(hours);
        endMinute.setItems(minutes);

        day.setItems(days);
        month.setItems(months);
        year.setItems(years);
        /**This Populates the Appointments Table**/
        try {
            allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());

            allApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            allApptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            allApptDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
            allApptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            allApptContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            allApptType.setCellValueFactory(new PropertyValueFactory<>("type"));
            allApptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            allApptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
            allApptCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***
         * This code gets contacts, appointment types, customer id's, and user id's from the database
         * */
        try {
            ObservableList<String> contactNames = ContactCRUD.getAllContactNames();
            addContact.setItems(contactNames);

            ObservableList<String> apptTypes = AppointmentCRUD.getAllAppointmentTypes();
            addType.setItems(apptTypes);

            custId.setItems(AppointmentCRUD.getAllCustomerIds().sorted());

            userId.setItems(AppointmentCRUD.getAllUserIds().sorted());


        } catch (Exception e) {
            e.printStackTrace();
        }


        /***
         * This code sets a message to let the user know ID is auto-generated
         * and disables the field
         * */
        addAppt.setText("Id Auto-Generated");
        addAppt.setEditable(false);

        allApptType.setEditable(true);
        System.out.println(new Date());

    }
}
