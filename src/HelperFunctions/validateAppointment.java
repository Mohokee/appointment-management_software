package HelperFunctions;

import javafx.scene.control.Alert;

import java.time.ZonedDateTime;

/**
 * This class ensures appointments are not overlapping and are scheduled within business hours
 */
public class validateAppointment {

    public static boolean validateAppointmentUpdate(ZonedDateTime localStarting, ZonedDateTime localEnding, ZonedDateTime localStart, ZonedDateTime localEnd){
        /**Alerts*/
        Alert scheduleError = new Alert(Alert.AlertType.ERROR);

        System.out.println(localEnding);
        System.out.println(localStarting);

        if (localEnding == localStarting) {
            /**This makes sure that the end time is not equal to the starting time*/
            scheduleError.setContentText("The end time can not be equal to the start time");
            scheduleError.showAndWait();
            return true;
        } else if (localStarting.isBefore(localStart) || localEnding.isAfter(localEnd)) {
            /**This checks to make sure the appointment has been scheduled during business hours, and if not,
             * will display an error with business time
             * **/
            scheduleError.setContentText("Business hours are 8AM to 10PM EST seven days a week");
            scheduleError.showAndWait();
            return true;
        }
        return false;
    }
}
