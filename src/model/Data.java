package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arjun on 8/8/17.
 *
 * This class holds the data for the entire model
 */
public class Data {

    // Data structure for holding customers information
    private Map<String, Customer> customers;

    // Data structure for holding orders information, Orders are mapped with CustomerId
    // storing customerId as key and list of orders as value
    private Map<String, List<Order>> orders;

    // Data structure for holding site visit information, Site Visits are mapped with CustomerId
    // storing customerId as key and list of site visits as value
    private Map<String, List<SiteVisit>> siteVisits;

    // Data structure for holding image information, Images are mapped with CustomerId
    // storing customerUd as key and list of images as value
    private Map<String, List<ImageUpload>> imageUploads;

    /**
     *
     * constructor for initializing data
     *
     */
    public Data() {
        this.customers = new HashMap<>();
        this.orders = new HashMap<>();
        this.siteVisits = new HashMap<>();
        this.imageUploads = new HashMap<>();
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers;
    }

    public Map<String, List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, List<Order>> orders) {
        this.orders = orders;
    }

    public Map<String, List<SiteVisit>> getSiteVisits() {
        return siteVisits;
    }

    public void setSiteVisits(Map<String, List<SiteVisit>> siteVisits) {
        this.siteVisits = siteVisits;
    }

    public Map<String, List<ImageUpload>> getImageUploads() {
        return imageUploads;
    }

    public void setImageUploads(Map<String, List<ImageUpload>> imageUploads) {
        this.imageUploads = imageUploads;
    }

    public String size() {
        return "customers size : " + this.customers.size() + "\n" + "site visits size : " + this.siteVisits.size()
                + "\n" + "orders size : " + this.orders.size() + "\n" + "images count : " + this.imageUploads.size();
    }
}
