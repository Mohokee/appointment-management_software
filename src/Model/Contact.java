package Model;

/**
 * This is the Contact class that stores database info on a contact
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;

    public Contact(int contactId,String contactName,String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact's id number
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact's name
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact's email address
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
