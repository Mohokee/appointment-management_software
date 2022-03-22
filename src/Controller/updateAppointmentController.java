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
import java.util.ResourceBundle;

/**
 * This class enables the user to update an appointment. The initial data is prepopulated
 * from the user table selection on the Main screen appointment table.
 */
public class updateAppointmentController implements Initializable {

    /**
     * Alerts
     */
    Alert noAppointment = new Alert(Alert.AlertType.ERROR);
    Alert alert = new Alert(Alert.AlertType.WARNING);

    /**
     * Appointment Table imports
     **/
    @FXML
    private TableView allAppointmentsTable;

    /**
     * Appointment Table column imports
     **/
    @FXML
    private TableColumn idUpdate;
    @FXML
    private TableColumn titleUpdate;
    @FXML
    private TableColumn descriptionUpdate;
    @FXML
    private TableColumn locationUpdate;
    @FXML
    private TableColumn contactIDUpdate;
    @FXML
    private TableColumn typeUpdate;
    @FXML
    private TableColumn startUpdate;
    @FXML
    private TableColumn endUpdate;
    @FXML
    private TableColumn custIDUpdate;

    /**
     * Text Field Imports
     */
    @FXML
    private TextField apptID;
    @FXML
    private TextField apptTitle;
    @FXML
    private TextField apptDesc;
    @FXML
    private TextField apptLocation;

    /**
     * Combo Box Imports
     */
    @FXML
    private ComboBox apptContact;
    @FXML
    private ComboBox apptType;
    @FXML
    private ComboBox custID;
    @FXML
    private ComboBox userID;

    /**
     * Time and Date Combo Boxes
     */
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox year;

    /**
     * Start
     */
    @FXML
    private ComboBox startHour;
    @FXML
    private ComboBox startMinute;

    /**
     * End
     */
    @FXML
    private ComboBox endHour;
    @FXML
    private ComboBox endMinute;

    @FXML
    private Label appointmentAddedMessage;


    /**
     * This method accepts a customer to initialize the view from the Main table
     *
     * @param appointment appointment to initialize
     */
    public void initData(Appointment appointment) throws Exception {
        if (appointment == null) {
            noAppointment.setContentText("No Appointment Selected");
            noAppointment.showAndWait();
        } else {

            /**Initiates Text Field Info to Update*/
            apptID.setText(String.valueOf(appointment.getAppointmentId()));
            apptTitle.setText(appointment.getTitle());
            apptDesc.setText(String.valueOf(appointment.getDescription()));
            apptLocation.setText(String.valueOf(appointment.getLocation()));

            /**These populate and initialize the combo box data with the original information*/
            /**This gets the contacts from the database and sets it to the appointment's initial contact*/
            ObservableList<String> contactNames = ContactCRUD.getAllContactNames();
            apptContact.setItems(contactNames);
            apptContact.setValue(ContactCRUD.getContactFromID(appointment.getContactId()));

            /**This gets the contacts from the database and sets it to the appointment's initial contact*/
            ObservableList<String> appointmentTypes = AppointmentCRUD.getAllAppointmentTypes();
            apptType.setItems(appointmentTypes);
            apptType.setValue(appointment.getType());

            /**This sets the initial date and time combo box values to the original appointment date and times*/
            Timestamp start = appointment.getStart();
            Timestamp end = appointment.getEnd();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
            DateTimeFormatter twentyFour = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime startOriginal = start.toLocalDateTime();
            LocalDateTime endOriginal = end.toLocalDateTime();

            Timestamp zStart = Timestamp.valueOf(startOriginal);
            Timestamp zEnd = Timestamp.valueOf(endOriginal);

            ZonedDateTime zonedStart = zStart.toLocalDateTime().atZone(ZoneId.systemDefault());
            ZonedDateTime zonedEnd = zEnd.toLocalDateTime().atZone(ZoneId.systemDefault());


            month.setValue(startOriginal.getMonthValue());
            day.setValue(startOriginal.getDayOfMonth());
            year.setValue(startOriginal.getYear());
            startHour.setValue(zonedStart.getHour());
            endHour.setValue(zonedEnd.getHour());


            if (zonedStart.getMinute() == 0) {
                startMinute.setValue("00");
            } else {
                startMinute.setValue(zonedStart.getMinute());
            }

            if (zonedEnd.getMinute() == 0) {
                endMinute.setValue("00");
            } else {
                endMinute.setValue(zonedEnd.getMinute());
            }

            /**This sets user and customer combo boxes to default value*/
            custID.setValue(appointment.getCustomerId());
            userID.setValue(appointment.getUserId());


        }

    }

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
     * This method adds the customer to the database and displays a message when adding is successful
     * */
    public void updateAppointment(Event event) throws Exception {
        ObservableList<TextField> textFields = FXCollections.observableArrayList();
        ObservableList<ComboBox> combos = FXCollections.observableArrayList();

        textFields.addAll(apptTitle, apptDesc, apptLocation);
        combos.addAll(apptContact, apptType, month, day, year, startHour, startMinute, endHour, endMinute, custID, userID);

        if (checkForBlanks.check(textFields) || checkForBlanks.checkCombos(combos)) {
            return;
        } else {

            int idUpdate = Integer.parseInt(apptID.getText());
            String titleUpdate = apptTitle.getText();
            String descriptionUpdate = apptDesc.getText();
            String locationUpdate = apptLocation.getText();
            int contactIDUpdate = ContactCRUD.getContactIDFromName(String.valueOf(apptContact.getValue()));
            String typeUpdate = String.valueOf(apptType.getValue());
            if (custID.getValue() == null || userID.getValue() == null) {
                alert.setContentText("The ID combo boxes for customer and user must both be filled");
                alert.showAndWait();
            } else {

                int customerID = Integer.parseInt(String.valueOf(custID.getValue()));
                int userId = Integer.parseInt(String.valueOf(userID.getValue()));
                /**These document the date and time created and updated*/
                Timestamp timestamp = Timestamp.from(Instant.now());
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

                /**These convert the user input from ZonedDateTime to UTC and then to a timestamp to save in the object*/

                /**Datetime formatter helps format time for a more readable AM/PM format*/

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

                /**Date input collection*/
                int y = Integer.parseInt(String.valueOf(year.getValue()));
                int m = Integer.parseInt(String.valueOf(month.getValue()));
                int d = Integer.parseInt(String.valueOf(day.getValue()));

                /**Start time input collection*/
                int startH = Integer.parseInt(String.valueOf(startHour.getValue()));
                int startM = Integer.parseInt(String.valueOf(startMinute.getValue()));

                /**End time input collection*/
                int endH= Integer.parseInt(String.valueOf(endHour.getValue()));
                int endM = Integer.parseInt(String.valueOf(endMinute.getValue()));

                ZonedDateTime startingTime;
                ZonedDateTime endingTime;

                startingTime = ZonedDateTime.of(y, m, d, startH, startM, 0, 0, ZoneId.systemDefault());
                endingTime = ZonedDateTime.of(y, m, d, endH, endM, 0, 0, ZoneId.systemDefault());


                /**Zoned date time parse*/


                /**These are UTC conversions*/
                ZonedDateTime UTCStart = startingTime.withZoneSameInstant(ZoneId.of("UTC"));
                ZonedDateTime UTCEnd = endingTime.withZoneSameInstant(ZoneId.of("UTC"));

                LocalDateTime convertStart = UTCStart.toLocalDateTime();
                LocalDateTime convertEnd = UTCEnd.toLocalDateTime();

                /**Timestamp Conversions for saving to Appointment object*/
                Timestamp startee = Timestamp.valueOf(convertStart);
                Timestamp endee = Timestamp.valueOf(convertEnd);

                /**These variables are for checking that appointment times are within business hours of 8am-10pm Eastern 7 days a week*/
                ZonedDateTime startEastern = ZonedDateTime.of(startingTime.getYear(), startingTime.getMonthValue(), startingTime.getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
                ZonedDateTime endEastern = ZonedDateTime.of(endingTime.getYear(), endingTime.getMonthValue(), endingTime.getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
                ZonedDateTime localBusinessStart = startEastern.withZoneSameInstant(ZoneId.systemDefault());
                ZonedDateTime localBusinessEnd = endEastern.withZoneSameInstant(ZoneId.systemDefault());

                ZonedDateTime zoneStart = startingTime.withZoneSameInstant(ZoneId.systemDefault());
                ZonedDateTime zoneEnd = endingTime.withZoneSameInstant(ZoneId.systemDefault());

                /**These variables help check for appointment conflicts*/
                LocalTime desiredStart = startingTime.toLocalTime();
                LocalTime desiredEnd = endingTime.toLocalTime();

                LocalDate localStartingDate = startingTime.toLocalDate();
                LocalDate localEndDate = endingTime.toLocalDate();

                Timestamp stampStart = Timestamp.valueOf(LocalDateTime.of(localStartingDate, desiredStart));
                Timestamp stampEnd = Timestamp.valueOf(LocalDateTime.of(localEndDate, desiredEnd));

                /**
                 * This section helps to validate the appointment by making sure it is within business hours
                 */
                if (validateAppointment.validateAppointmentUpdate(zoneStart, zoneEnd, localBusinessStart, localBusinessEnd)) {
                    System.out.println("Appointment Invalid");
                    return;
                } else {
                    /**This checks for conflicting appointments*/
                    ObservableList<Appointment> conflict = AppointmentCRUD.checkOverlapUpdate(stampStart, stampEnd, customerID, idUpdate);
                    if (!conflict.isEmpty()) {
                        for (Appointment a : conflict) {
                            alert.setContentText("Appointment " + a.getAppointmentId() + "\r\n" + " has conflicting times with this appointment " +
                                    "\r\n" + "Please select a different time");
                            alert.showAndWait();
                        }
                    } else {

                        AppointmentCRUD.updateAppointment(idUpdate, titleUpdate, descriptionUpdate, locationUpdate, typeUpdate, startee,
                                endee, date, "test", timestamp, "test", customerID, userId, contactIDUpdate);

                        appointmentAddedMessage.setText("Appointment Updated");
                        allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());
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

        /**This section populates the date and time combo boxes*/
        month.setItems(months);
        day.setItems(days);
        year.setItems(years);

        startHour.setItems(hours);
        startMinute.setItems(minutes);

        endHour.setItems(hours);
        endMinute.setItems(minutes);


        /**This Populates the Appointments Table**/
        try {
            allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());

            idUpdate.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleUpdate.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionUpdate.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationUpdate.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactIDUpdate.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            typeUpdate.setCellValueFactory(new PropertyValueFactory<>("type"));
            startUpdate.setCellValueFactory(new PropertyValueFactory<>("start"));
            endUpdate.setCellValueFactory(new PropertyValueFactory<>("end"));
            custIDUpdate.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***
         * This code sets a message to let the user know ID is auto-generated
         * and disables the field
         * */
        apptID.setText("Id Auto-Generated");
        apptID.setEditable(false);

        /***
         * This code gets contacts, appointment types, customer id's, and user id's from the database
         * */
        try {
            ObservableList<String> contactNames = ContactCRUD.getAllContactNames();
            apptContact.setItems(contactNames);

            ObservableList<String> apptTypes = AppointmentCRUD.getAllAppointmentTypes();
            apptType.setItems(apptTypes);

            custID.setItems(AppointmentCRUD.getAllCustomerIds().sorted());

            userID.setItems(AppointmentCRUD.getAllUserIds().sorted());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
