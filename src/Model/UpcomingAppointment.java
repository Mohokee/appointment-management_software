package Model;

import java.time.ZonedDateTime;
/**
 * This class is for appointments that are in 15 minutes
 */
public class UpcomingAppointment {
    private int apptID;
    private ZonedDateTime start;

    public UpcomingAppointment(int apptID,ZonedDateTime start) {
        this.apptID = apptID;
        this.start = start;
    }

    public int getApptID() {
        return apptID;
    }

    public ZonedDateTime getStart() {
        return start;
    }


}
