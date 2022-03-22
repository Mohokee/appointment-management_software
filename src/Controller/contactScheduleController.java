package Controller;

import CRUD.ContactCRUD;
import Model.ContactSchedule;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class creates a contact schedule for a specific contact selected from a combo box
 */
public class contactScheduleController implements Initializable {
    /**Table Imports*/
    @FXML private TableView<ContactSchedule> contactSched;

    /**Column Imports*/
    @FXML private TableColumn apptID;
    @FXML private TableColumn title;
    @FXML private TableColumn type;
    @FXML private TableColumn desc;
    @FXML private TableColumn start;
    @FXML private TableColumn end;
    @FXML private TableColumn customerID;

    /**Combo Box Imports*/
    @FXML private ComboBox contactCombo;


    /** This method populates the report table
     * @param event activates on combo box changing
     */
    @FXML
    public void populateLocationTable(Event event) throws Exception {

        String contacts = String.valueOf(contactCombo.getValue());

        if(contacts.equals("Anika Costa")){
            contactSched.setItems(ContactCRUD.getContactSchedule(1));
        } else if(contacts.equals("Daniel Garcia")){
            contactSched.setItems(ContactCRUD.getContactSchedule(2));
        } else {
            contactSched.setItems(ContactCRUD.getContactSchedule(3));
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

        /**This line gets the Stage information*/
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apptID.setCellValueFactory((new PropertyValueFactory<>("appointmentId")));
            title.setCellValueFactory((new PropertyValueFactory<>("title")));
            type.setCellValueFactory((new PropertyValueFactory<>("type")));
            desc.setCellValueFactory((new PropertyValueFactory<>("description")));
            start.setCellValueFactory((new PropertyValueFactory<>("start")));
            end.setCellValueFactory((new PropertyValueFactory<>("end")));
            customerID.setCellValueFactory((new PropertyValueFactory<>("customerId")));

            ObservableList<String> contacts = ContactCRUD.getAllContactNames();
            contactCombo.setItems(contacts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
