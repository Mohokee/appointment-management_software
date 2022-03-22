package Model;

import CRUD.CountryCRUD;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * This class is for countries
 */
public class Country {
    private int countryId;
    private String country;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Country(int countryId, String country, Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }


    /**
     * @return the country id
     */
    public int getCountryId() {
        return countryId;
    }
    /**
     * Set the country id
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }


    /**
     * @return the country
     */
    public String getCountry() {

        return country;
    }
    /**
     * Set the country
     * @param country the countryId to set
     */
    public void setCountry(String country) throws Exception {
        this.country = CountryCRUD.getCountry(country).getCountry();
    }


    /**
     * @return the created date
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * Set the date the country was created in database
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the name of the user who created the country in database
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set the name of the user who created the country profile
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
     * Set date when country was last updated
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return who updated the country profile last
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Set the who last updated the country profile
     * @param lastUpdatedBy the lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}


