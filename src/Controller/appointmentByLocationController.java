package Controller;

import CRUD.AppointmentCRUD;
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
 * REPORT CONTROLLER
 * This controller generates the tableview for the appointment by location report
 */
public class appointmentByLocationController implements Initializable {
    /**Table Imports*/
    @FXML
    private TableView locationTable;
    /**Column imports*/
    @FXML private TableColumn apptIDColumn;
    @FXML private TableColumn locationColumn;
    /**Combo Box imports**/
    @FXML private ComboBox<String> locationCombo;

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

    /** This method populates the report table
     */
    @FXML
    public void populateLocationTable(Event event) throws Exception {

        String locationSelection = String.valueOf(locationCombo.getValue());

        locationTable.setItems(AppointmentCRUD.prepareLocationReport(locationSelection));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apptIDColumn.setCellValueFactory((new PropertyValueFactory<>("apptID")));
            locationColumn.setCellValueFactory((new PropertyValueFactory<>("location")));

            ObservableList<String> locations = AppointmentCRUD.getAllLocations();
            locationCombo.setItems(locations);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
