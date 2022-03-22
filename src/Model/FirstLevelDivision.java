package Model;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * This class is for first level divisions
 */
public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryId;

    public FirstLevelDivision(int divisionId, String division,Date createDate, String createdBy,
                              Timestamp lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
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

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }
    /**
     * Set the division
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return the date created
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * Set the date created
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    /**
     * @return who created the division
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set who created the division
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return when last updated
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Set the date last updated
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return who last updated division
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Set who last update the division
     * @param lastUpdatedBy the lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the division's country id
     */
    public int getCountryID() {
        return countryId;
    }
    /**
     * Set the country id
     * @param countryId the countryId to set
     */
    public void setCountryID(int countryId) {
        this.countryId = countryId;
    }
}
