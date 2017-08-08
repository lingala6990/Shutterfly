package model;

import java.sql.Timestamp;

/**
 * Created by Arjun on 8/7/17.
 *
 * This class represents Order entity from the model
 */
public class Order {

    private String orderId;
    private Timestamp eventTime;
    private String customerId;
    private double totalAmount;

    /**
     *
     * Constructor for Order
     *
     * @param orderId
     * @param eventTime
     * @param customerId
     * @param totalAmount
     */
    public Order(String orderId, Timestamp eventTime, String customerId, double totalAmount) {
        this.orderId = orderId;
        this.eventTime = eventTime;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
