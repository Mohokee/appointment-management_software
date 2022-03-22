package HelperFunctions;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * This class helps to check for blanks in text fields and combo boxes.
 * */
public class checkForBlanks {
    /**
     * This class checks for blanks in any text fields.
     * If they are blank, then an alert with the text field's ID is shown to the user.
     * @param fields the list of fields to check
     * @return
     */
    public static Boolean check(ObservableList<TextField> fields) {

        for (TextField f : fields) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(f.getText().isEmpty()){
                alert.setContentText("The " + f.getId() + " field is empty, please enter a value");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }

    /**
     * This class checks for blanks in any text fields.
     * If they are blank, then an alert with the text field's ID is shown to the user.
     * @param combo the list of combo boxes to check
     * @return
     */
    public static Boolean checkCombos(ObservableList<ComboBox> combo) {

        for (ComboBox c : combo) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(c.getValue() == null){
                alert.setContentText("The " + c.getId() + " combo box is empty, please enter a value");
                alert.showAndWait();
            }


        }
        return false;
    }

    /**
     * This class checks for blanks in any text fields.
     * If they are blank, then an alert with the text field's ID is shown to the user.
     * @param fields the list of fields to check
     * @return
     */
    public static Boolean checkFrench(ObservableList<TextField> fields) {

        for (TextField f : fields) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(f.getText().isEmpty()){
                alert.setContentText("Tous les champs doivent être remplis");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }
}



