package model;

import java.sql.Timestamp;

/**
 * Created by Arjun on 8/7/17.
 *
 * This class represents Customer Entity from the model
 */
public class Customer {

    private String customerId;
    private Timestamp eventTime;
    private String lastName;
    private String adrCity;
    private String adrState;

    /**
     *
     * Constructor for customer
     *
     * @param customerId
     * @param eventTime
     * @param lastName
     * @param adrCity
     * @param adrState
     */
    public Customer(String customerId, Timestamp eventTime, String lastName, String adrCity, String adrState) {
        this.customerId = customerId;
        this.eventTime = eventTime;
        this.lastName = lastName;
        this.adrCity = adrCity;
        this.adrState = adrState;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdrCity() {
        return adrCity;
    }

    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    public String getAdrState() {
        return adrState;
    }

    public void setAdrState(String adrState) {
        this.adrState = adrState;
    }
}
