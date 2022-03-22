package Controller;

import CRUD.AppointmentCRUD;
import CRUD.CountryCRUD;
import CRUD.CustomerCRUD;
import CRUD.FirstLevelDivisionCRUD;
import HelperFunctions.checkForBlanks;
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
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This controller enables the user to add customers to the database and is connected to the "addCustomer" view
 */
public class addCustomerController implements Initializable {
    /***
     * Customer Added Success label import
     * */
    @ FXML private Label customerAddedMessage;

    /**Customer Table imports**/
    @FXML
    private TableView addCustTable;
    /**Customer Table column imports**/
    @FXML private TableColumn customerIdColumn;
    @FXML private TableColumn customerNameColumn;
    @FXML private TableColumn customerAddressColumn;
    @FXML private TableColumn customerZip;
    @FXML private TableColumn customerDivisionIdColumn;
    @FXML private TableColumn customerPhoneColumn;

    /**Add Customer Text Field imports**/
    @FXML private TextField addCustId;
    @FXML private TextField addCustName;
    @FXML private TextField addCustPhone;
    @FXML private TextField addCustAddress;
    @FXML private TextField addCustPostal;
    @FXML private ComboBox<String> addCustCountry;
    @FXML private ComboBox<String> addCustState;

    /**Alerts**/
    Alert alert = new Alert(Alert.AlertType.WARNING);
    /***
     * This method adds the customer to the database and displays a message when adding is successful
     * */
    public void addCustomer() throws Exception {
        /**
         * This code checks for blanks in any text fields and returns an id-specific alert if present
         */
        ObservableList<TextField> textFields = FXCollections.observableArrayList();
        textFields.addAll(addCustName, addCustAddress, addCustPhone, addCustPostal);

        /**
         * This code checks for blanks in any combo boxes and returns an id-specific alert if present
         */
        ObservableList<ComboBox> combos = FXCollections.observableArrayList();
        combos.addAll(addCustCountry, addCustState);


            /**This code checks to see if the first level division combo box is empty*/
            if (checkForBlanks.checkCombos(combos) || checkForBlanks.check(textFields)) {
                return;
            } else if (addCustState.getValue() == null){
                alert.setContentText("Please select a state or province");
                alert.showAndWait();
            }else {
                /**These variables hold user input from the text fields and combo boxes*/
                int addCustId = 0;
                String addName = addCustName.getText();
                String addPhone = addCustPhone.getText();
                String addAddress = addCustAddress.getText();
                String addPostal = addCustPostal.getText();
                String addState = String.valueOf((addCustState.getValue()));
                String country = String.valueOf(addCustCountry.getValue());
                Timestamp timestamp = Timestamp.from(Instant.now());
                /**This code gets the first level division ID to add to a new Customer based on the user input*/
                int stateId = FirstLevelDivisionCRUD.getFLDId(addCustState.getValue());

                CustomerCRUD.addCustomer(addCustId, addName, addAddress, addPostal, addPhone,
                        new Date(), "test", timestamp,
                        "test", stateId);

                customerAddedMessage.setText("Add Customer Successful");
                addCustTable.setItems(AppointmentCRUD.getAllAppointments());
            }
        }

    /** This method populates the First level division (FLD) combo box depending on which country is selected.
     * @param event populate FLD combo box
      */
    @FXML
    public void populateFLDComboBox(Event event) throws Exception {

        System.out.println(addCustCountry.getValue());

        String selectCountry = addCustCountry.getValue();

        if(Objects.equals(selectCountry, "U.S")){
            ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(1);
            addCustState.setItems(FLD);
        } else if(Objects.equals(selectCountry, "UK")){
            ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(2);
            addCustState.setItems(FLD);
        } else if(Objects.equals(selectCountry, "Canada")){
            ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(3);
            addCustState.setItems(FLD);
        }

    }



    /**
     * This method returns the user to the main page
     * @param event backToMain
     */
    public void backToMain(Event event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/mainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            /**This Populates the Customer Table**/
            addCustTable.setItems(CustomerCRUD.getAllCustomers());

            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            customerZip.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            customerDivisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
            customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /***
         * This code gets the Countries from the database
         * */
        try {
            ObservableList<String> countryNames = CountryCRUD.getAllCountryNames();
            addCustCountry.setItems(countryNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /***
         * This code sets a message to let the user know ID is auto-generated
         * and disables the field
         * */
        addCustId.setText("Id Auto-Generated");
        addCustId.setEditable(false);

    }
}
