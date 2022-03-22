package Model;

import java.sql.Timestamp;
import java.util.Date;
/**
 * This class is for customers
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;

    public Customer(int customerId, String customerName, String address, String postalCode, String phone,
                    Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }


    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Set the Customer ID
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Set the Customer Name
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Set the Address
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Set the postal code
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Set the phone number
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the created date
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * Set the date the customer was created
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the name of the user who created the customer
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set the name of the user who created the customer profile
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the date when last updated
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Set date when customer was last updated
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return who updated the customer profile last
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Set the who last updated the customer profile
     * @param lastUpdatedBy the lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the division id
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**
     * Set the division id
     * @param divisionId the divisionId to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
