package Model;

/**
 * This class compiles a report listing appointments by location
 */
public class AppointmentByLocation {
    private int apptID;
    private String location;

    public AppointmentByLocation(int apptID,String location) {
        this.apptID = apptID;
        this.location = location;
    }

    public int getApptID() {
        return apptID;
    }

    public String getLocation() {
        return location;
    }


}
