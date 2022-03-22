package Controller;

import CRUD.AppointmentCRUD;
import Model.MonthTypeReport;
import javafx.collections.FXCollections;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class creates a report by month and type.It displays the info on a table that
 * is automatically updated when an item on the combobox is selected.
 */
public class monthAndTypeReportController implements Initializable {
    /**Table imports*/
    @FXML private TableView<MonthTypeReport> typeMonthTable;

    /**Table column imports*/
    @FXML private TableColumn month;
    @FXML private TableColumn type;
    @FXML private TableColumn total;

    /**Combobox imports*/
    @FXML private ComboBox selectMonth;


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
    public void populateReportTable(Event event) throws Exception {

        String monthSelection = String.valueOf(selectMonth.getValue());

        typeMonthTable.setItems(AppointmentCRUD.prepareMonthTypeReport(monthSelection));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            typeMonthTable.setItems(AppointmentCRUD.prepareMonthTypeReport(LocalDateTime.now().getMonth().name()));

            month.setCellValueFactory((new PropertyValueFactory<>("month")));
            type.setCellValueFactory((new PropertyValueFactory<>("type")));
            total.setCellValueFactory((new PropertyValueFactory<>("total")));
        } catch (Exception e) {
            e.printStackTrace();
        }



        /**Initialize month names in the month combobox*/
        ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March",
                "April", "May", "June", "July", "August", "September", "October", "November", "December");

        selectMonth.setItems(months);
        selectMonth.getSelectionModel().select(LocalDateTime.now().getMonth().name());

    }
}
