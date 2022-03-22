package Controller;

import CRUD.UserCRUD;
import HelperFunctions.checkForBlanks;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class takes a username and password, compares it to the database, and logs the user in, or warns of incorrect
 * credentials. If the system language is french, the language will be changed to french
 */
public class loginController implements Initializable {
    @FXML private Label userLocation;
    @FXML private Label zoneID;
    @FXML private Label zoneIdLabel;
    @FXML private Label welcome;
    @FXML private Label userLabel;
    @FXML private Label passwordLabel;
    @FXML private Label userLocationLabel;
    @FXML private Button login;
    @FXML private TextField userField;
    @FXML private TextField passwordField;

    /**
     * Alerts
     * */
    Alert emptyField = new Alert(Alert.AlertType.WARNING);
    Alert incorrectCredentials = new Alert(Alert.AlertType.WARNING);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Set variables for Password and Username input
    * */
    private String userName;
    private String password;

    /**
     * Set variables for Password and Username from Database
     * */
    private String loginSuccess;
    private String passwordSuccess;
    public ObservableList<User> allUsers;

    {
        try {
            allUsers = UserCRUD.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private final ZoneId zoneId = ZoneId.systemDefault();
    /**
     * Test french locale
     */
    //private Locale locale = Locale.FRANCE;
     /**
      * Default user's locale
      * @param locale
      * */
    private final Locale locale = Locale.getDefault();



    /**
     * Sets the Language to French when called, if language is FR
     * */
    private void setToFrench(){
        zoneIdLabel.setText("ID de Zone");
        userLocationLabel.setText("Emplacement de l'Utilisateur:");
        welcome.setText("Bienvenu");
        userLabel.setText("Nom d'utilisateur");
        passwordLabel.setText("Le mot de passe");
        login.setText("Connectez-Vous");
        emptyField.setContentText("Tous les champs doivent être remplis");
        incorrectCredentials.setContentText("Les informations d'identification de connexion sont incorrectes");
    }

    /**
     * Sets the Language to English when called, if language is EN
     * */
    private void setToEnglish(){
        emptyField.setContentText("All Fields must be Filled");
        incorrectCredentials.setContentText("Your username entry was incorrect");
        alert.setContentText("Your password entry was incorrect");
    }

    /**
     * Login button controls will login to main screen if successful and display error messages if not
     * @param event login
     * */
   public void login(Event event) throws Exception {
       userName = userField.getText();
       password = passwordField.getText();


       /**
        * This method checks if the username and password fields are blank, or if the username is not contained in the database
        * */
       ObservableList<TextField> logins = FXCollections.observableArrayList();
       logins.addAll(userField,passwordField);
       if(locale.getLanguage().equals("fr")) {
           checkForBlanks.checkFrench(logins);
       } else {
           checkForBlanks.check(logins);
       }
       if (UserCRUD.getUser(userName) == null) {
           print(userName,"Unsuccessful",Timestamp.valueOf(LocalDateTime.now()));
           incorrectCredentials.showAndWait();
       } else if(!UserCRUD.getUser(userName).getPassword().equals(password)) {
           print(userName,"Unsuccessful",Timestamp.valueOf(LocalDateTime.now()));
           alert.showAndWait();
       } else {
           /**
            * If the user input is contained in the database, this method continues the login process and changes to
            * the Main Screen
            * */
       loginSuccess = UserCRUD.getUser(userName).getUserName();
       passwordSuccess = UserCRUD.getUser(userName).getPassword();

       if (loginSuccess.equals(userName) && passwordSuccess.equals(password)) {
           print(userName,"Successful",Timestamp.valueOf(LocalDateTime.now()));
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("../View/mainScreen.fxml"));
           Parent tableViewParent = loader.load();

           Scene tableViewScene = new Scene(tableViewParent);

           /**
            * Access the controller and call a method
            * */
           mainScreenController control = loader.getController();

           /**
            * This line gets the Stage information, changes scene to the Main Screen
            * */
           Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

           window.setScene(tableViewScene);
           window.show();
       }
       }
   }

    /**
     * This function
     * @param user a list of inputs
     * @param attempt string for the success of the attempt
     * @param stamp timestamp with date and time of login attempt
     */
    public static void print(String user, String attempt, Timestamp stamp) {
        try {
            PrintWriter p = new PrintWriter(new FileOutputStream(("userLogins.txt"), true));
            //out.txt will appear in the project's root directory under NetBeans projects
            //Note that Notepad will not display the following lines on separate lines
            p.append("User Name: " + user + "\n");
            p.append("Attempt Success: "+ attempt + "\n");
            p.append("Timestamp: "+ stamp + "\n");
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Locale.getDefault());
        userLocation.setText(locale.getCountry());
        zoneID.setText(zoneId.toString());
        if(locale.getLanguage().equals("fr")) {
            setToFrench();
        } else {
            setToEnglish();
        }
    }
}