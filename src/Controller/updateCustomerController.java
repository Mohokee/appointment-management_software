package Controller;

import CRUD.CountryCRUD;
import CRUD.CustomerCRUD;
import CRUD.FirstLevelDivisionCRUD;
import HelperFunctions.checkForBlanks;
import Model.Customer;
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
 * This class enables a user to update a customer, the data is prepopulated from the user's
 * customer selection on the mainscreen table
 */
public class updateCustomerController implements Initializable {
    /**Alerts*/
    Alert noCustomer = new Alert(Alert.AlertType.ERROR);
    Alert alert = new Alert(Alert.AlertType.WARNING);

    /**Table Imports*/
    @FXML public TableView updateCustTable;

    @FXML private TableColumn updateCustId;
    @FXML private TableColumn updateCustName;
    @FXML private TableColumn updateCustAddress;
    @FXML private TableColumn updateCustPostal;
    @FXML private TableColumn updateCustDiv;
    @FXML private TableColumn updateCustPhone;

    /**Text Field Imports*/
    @FXML private TextField changeId;
    @FXML private TextField changeName;
    @FXML private TextField changePhone;
    @FXML private TextField changeAddress;
    @FXML private TextField changePostal;

    /**Combo Box Imports*/
    @FXML private ComboBox countryCombo;
    @FXML private ComboBox stateCombo;

    /***/
    @FXML private Label customerUpdatedMessage;

    /**
     * This method accepts a customer to initialize the view
     * @param customer
     */
    public void initData(Customer customer) throws Exception {
        if (customer == null){
            noCustomer.setContentText("No Customer Selected");
            noCustomer.showAndWait();
        } else {
            changeId.setText(String.valueOf(customer.getCustomerId()));
            changeName.setText(customer.getCustomerName());
            changePhone.setText(String.valueOf(customer.getPhone()));
            changeAddress.setText(String.valueOf(customer.getAddress()));
            changePostal.setText(String.valueOf(customer.getPostalCode()));

            /**These conditions set the country combo box based on the division ID, and fills the
             * pre-selected first level division combo box with the selected customer's state*/
            if(customer.getDivisionId() > 0 && customer.getDivisionId() < 55){
                countryCombo.setValue("U.S");

                ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(1);
                stateCombo.setItems(FLD);

                stateCombo.setValue(FirstLevelDivisionCRUD.getFLD(customer.getDivisionId()));

            } else if(customer.getDivisionId() > 55 && customer.getDivisionId() < 100){
                countryCombo.setValue("Canada");

                ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(3);
                stateCombo.setItems(FLD);

                stateCombo.setValue(FirstLevelDivisionCRUD.getFLD(customer.getDivisionId()));

            } else if(customer.getDivisionId() > 100){
                countryCombo.setValue("UK");

                ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(2);
                stateCombo.setItems(FLD);

                stateCombo.setValue(FirstLevelDivisionCRUD.getFLD(customer.getDivisionId()));
            }
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

    /** This method populates the First level division (FLD) combobox depending on which country is selected.
     * @param event populate FLD combo box
     */
    @FXML
    public void populateFLDComboBox(Event event) throws Exception {

        System.out.println(countryCombo.getValue());

        String selectCountry = countryCombo.getValue().toString();

        if(Objects.equals(selectCountry, "U.S")){
            ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(1);
            stateCombo.setItems(FLD);
        } else if(Objects.equals(selectCountry, "UK")){
            ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(2);
            stateCombo.setItems(FLD);
        } else if(Objects.equals(selectCountry, "Canada")){
            ObservableList<String>  FLD = FirstLevelDivisionCRUD.getAllFLDNames(3);
            stateCombo.setItems(FLD);
        }
    }



    /***
     * This method updates the customer in the database and displays a message when adding is successful
     * */
    public void updateCustomer() throws Exception {
        int addCustId = Integer.parseInt(changeId.getText());
        String addName = changeName.getText();
        String addPhone = changePhone.getText();
        String addAddress = changeAddress.getText();
        String addPostal = changePostal.getText();
        String addState = String.valueOf((stateCombo.getValue()));
        String country = String.valueOf(countryCombo.getValue());
        Timestamp timestamp = Timestamp.from(Instant.now());

        ObservableList<TextField> textFields = FXCollections.observableArrayList();
        ObservableList<ComboBox> combos = FXCollections.observableArrayList();

        textFields.setAll(changeName, changePhone, changeAddress, changePostal);
        combos.setAll(stateCombo, countryCombo);

        /**
         * This makes sure that no fields have been left blank
         */
        if (checkForBlanks.check(textFields) || checkForBlanks.checkCombos(combos)) {
            return;
        } else {


            int stateId = FirstLevelDivisionCRUD.getFLDId(addState);

            CustomerCRUD.updateCustomer(addCustId, addName, addAddress, addPostal, addPhone,
                    new Date(), "test", timestamp,
                    "test", stateId);

            customerUpdatedMessage.setText("Update Customer Successful");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /***
             * This code sets a message to let the user know ID is auto-generated
             * and disables the field
             * */
            changeId.setText("Id Auto-Generated");
            changeId.setEditable(false);

            /**This Populates the Customer Table**/
            updateCustTable.setItems(CustomerCRUD.getAllCustomers());

            updateCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            updateCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            updateCustAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            updateCustPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            updateCustDiv.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
            updateCustPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***
         * This code gets the Countries from the database
         * */
        try {
            ObservableList<String> countryNames = CountryCRUD.getAllCountryNames();
            countryCombo.setItems(countryNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
