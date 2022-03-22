package Controller;

import CRUD.AppointmentCRUD;
import CRUD.CustomerCRUD;
import Model.Appointment;
import Model.Customer;
import javafx.event.ActionEvent;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * THIS CLASS CONTAINS BOTH LAMBDAS
 * This class displays the main customer and appointment tables and acts as a hub. It leads to reports, adding,
 * and updating pages, and will allow users to delete customers and reports on the page itself.
 *
 */
public class mainScreenController implements Initializable {
    /**Customer Table imports**/
    @FXML public TableView<Customer> mainCustTable;
    /**Customer Table column imports**/
    @FXML private TableColumn customerIdColumn;
    @FXML private TableColumn customerNameColumn;
    @FXML private TableColumn customerAddressColumn;
    @FXML private TableColumn customerZip;
    @FXML private TableColumn customerDivisionIdColumn;
    @FXML private TableColumn customerPhoneColumn;

    /**Appointment Weekly Table imports**/
    @FXML private TableView<Appointment> weeklyAppointments;
    /**Appointment Weekly Table column imports**/
    @FXML private TableColumn apptId;
    @FXML private TableColumn apptTitle;
    @FXML private TableColumn apptDescription;
    @FXML private TableColumn apptLocation;
    @FXML private TableColumn apptContact;
    @FXML private TableColumn apptType;
    @FXML private TableColumn apptStart;
    @FXML private TableColumn apptEnd;
    @FXML private TableColumn apptCustId;
    @FXML private TableColumn apptUserId;

    /**Appointment Monthly Table imports**/
    @FXML private TableView<Appointment> monthlyAppointments;
    /**Appointment Monthly Table column imports**/
    @FXML private TableColumn monthApptId;
    @FXML private TableColumn monthApptTitle;
    @FXML private TableColumn monthApptDescript;
    @FXML private TableColumn monthApptLocation;
    @FXML private TableColumn monthApptContact;
    @FXML private TableColumn monthApptType;
    @FXML private TableColumn monthApptStart;
    @FXML private TableColumn monthApptEnd;
    @FXML private TableColumn monthApptCustId;
    @FXML private TableColumn monthApptUserId;

    /**All Appointment Table imports**/
    @FXML private TableView<Appointment> allAppointmentsTable;
    /**All Appointment Table column imports**/
    @FXML private TableColumn allApptId;
    @FXML private TableColumn allApptTitle;
    @FXML private TableColumn allApptDescript;
    @FXML private TableColumn allApptLocation;
    @FXML private TableColumn allApptContact;
    @FXML private TableColumn allApptType;
    @FXML private TableColumn allApptStart;
    @FXML private TableColumn allApptEnd;
    @FXML private TableColumn allApptCustId;
    @FXML private TableColumn allApptUserId;

    @FXML private Button exit;


    /**Alerts*/
    Alert deleteCust = new Alert(Alert.AlertType.WARNING);
    Alert deleteAppointmentsCust = new Alert(Alert.AlertType.CONFIRMATION);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    /**
     * LAMBDA EXPLANATION: This lambda helps to make changing scenes more efficient
     * This method returns to the login page
     * Event e logOut
     */
    sceneSwitch logOut = (e) ->{
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();};

    /**
     * This method directs to the add customer page
     * @param event addCustomer
     */
    public void addCustomer(Event event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/addCustomer.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * This method gives selected customer data to the update customer screen and
     * changes screens
     * @param event changeSceneToUpdateCustomer
     */
    public void changeSceneToUpdateCustomer(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/updateCustomer.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        updateCustomerController control = loader.getController();
        try {
            control.initData(mainCustTable.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**This checks if a customer is selected, and if not, displays an error message**/
        if(mainCustTable.getSelectionModel().getSelectedItem().equals(null)) {
           Alert noneSelected  = new Alert(Alert.AlertType.ERROR);
           noneSelected.setContentText("No Customer Selected");
           noneSelected.showAndWait();
        } else {
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }



    /**
     * This method directs to the add appointment page
     * @param event addAppointment
     */
    public void addAppointment(Event event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/addAppointment.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * This method gives selected appointment data to the update appointment screen and
     * changes screens
     * @param event changeSceneToUpdateAppointment
     */
    public void changeSceneToUpdateAppointment(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/updateAppointment.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        updateAppointmentController control = loader.getController();
        try {
            control.initData(allAppointmentsTable.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**This checks if an appointment is selected, and if not, displays an error message**/
        if(allAppointmentsTable.getSelectionModel().getSelectedItem().equals(null)) {
            Alert noneSelected  = new Alert(Alert.AlertType.ERROR);
            noneSelected.setContentText("No Appointment Selected");
            noneSelected.showAndWait();
            return;
        } else {
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }

    /**This method deletes a customer as long as they have no appointments scheduled**/
    public void deleteCustomer(Event event) throws Exception {
       Customer deleteThem = mainCustTable.getSelectionModel().getSelectedItem();
       if(deleteThem == null) {
           deleteCust.setContentText("No Customer Selected");
       } else {
           deleteAppointmentsCust.setContentText("Do you want to delete this customer and all associated appointments?");
           Optional<ButtonType> result = deleteAppointmentsCust.showAndWait();
           if (result.get() == ButtonType.OK) {
               AppointmentCRUD.deleteCustAppointment(deleteThem.getCustomerId());
               CustomerCRUD.deleteCustomer(deleteThem.getCustomerName());
               mainCustTable.setItems(CustomerCRUD.getAllCustomers());
               allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());
               deleteCust.setContentText("Customer Deleted");
               deleteCust.show();
           }
       }
       }



    /**This method deletes an appointment**/
    public void deleteAppointment(Event event) throws Exception {
        Appointment deleteThem = allAppointmentsTable.getSelectionModel().getSelectedItem();
        if(deleteThem == null) {
            deleteCust.setContentText("No Customer Selected");
        } else {
            deleteAppointmentsCust.setContentText("Do you want to delete this appointment?");
            Optional<ButtonType> result = deleteAppointmentsCust.showAndWait();
            if (result.get() == ButtonType.OK) {
                AppointmentCRUD.deleteAppointment(deleteThem.getTitle());
                allAppointmentsTable.setItems(AppointmentCRUD.getAllAppointments());
                deleteCust.setContentText("Appointment Deleted    " + "Appointment ID: " + deleteThem.getAppointmentId() + "   Type: " + deleteThem.getType());
                deleteCust.show();
            }
        }
    }

    /**These events load the report pages when activated*/
    /**
     * This method directs to the Month and Type Report
     * @param event
     */
    public void monthTypeReportGo(Event event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/monthAndTypeReport.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /** This method directs to the appointment by location report
     * @param event
     */
    public void apptLocationReportGo(Event event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/appointmentByLocation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /** This method directs to the Contact schedule report
     * @param event
     */
    public void contactScheduleReportGo(Event event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/contactSchedules.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    interface sceneSwitch {
        public void sceneSwitch(Event event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /**This Populates the Customer Table**/
            mainCustTable.setItems(CustomerCRUD.getAllCustomers());

            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            customerZip.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            customerDivisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
            customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

            /**This Populates the Weekly Appointment Table**/
            weeklyAppointments.setItems(AppointmentCRUD.getWeeklyAppointments());
            System.out.println(AppointmentCRUD.getWeeklyAppointments());

            apptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            apptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            apptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            apptContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
            apptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            apptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
            apptCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            apptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

            /**This Populates the Monthly Appointment Table**/
            monthlyAppointments.setItems(AppointmentCRUD.getMonthlyAppointments());

            monthApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            monthApptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            monthApptDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
            monthApptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            monthApptContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            monthApptType.setCellValueFactory(new PropertyValueFactory<>("type"));
            monthApptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            monthApptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
            monthApptCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            monthApptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

            /**This Populates the All Appointments Table**/
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
            allApptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

            /**
             * This section creates a 15 minute alert if any upcoming appointments were found in the database.
             * If not, it returns an alert that says there are no upcoming appointments.
             */
            try {
                if(!AppointmentCRUD.getUpcomingAppointments().isEmpty()){
                    alert.setContentText("Appointment" + AppointmentCRUD.getUpcomingAppointments().get(0).getApptID() + " starts in " + "15 Minutes at "
                    + AppointmentCRUD.getUpcomingAppointments().get(0).getStart());
                    alert.showAndWait();
                } else {
                    alert.setContentText("There are no upcoming appointments");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * LAMBDA EXPLANATION:This lambda simplifies assigning functions to buttons
         * This Lambda sets the log out button to the logout lambda
         */
        exit.setOnAction(e -> {
            logOut.sceneSwitch(e);
        });

    }
}
