package Model;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * This is the appointment class that stores database info on an appointment
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end,
                       Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }


    /**
     * @return the appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }
    /**
     * Set the appointment ID
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the title
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set the description
     * @param description the appointmentId to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * Set the appointment type
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start time
     */
    public Timestamp getStart() {
        return start;
    }
    /**
     * Set the appointment start time
     * @param start the start to set
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * @return the end time
     */
    public Timestamp getEnd() {
        return end;
    }
    /**
     * Set the appointment end time
     * @param end the end to set
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * @return the created date
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * Set the date the appointment was created
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return who created the appointment
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set the appointment's creator
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the lastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Set the appointment's last update date
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return who last updated the appointment
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Set who updated the appointment last
     * @param lastUpdatedBy the lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Set the customer ID
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Set the user ID
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }
    /**
     * Set the contact ID
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }


}
