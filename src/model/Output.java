package model;

/**
 * Created by Arjun on 8/8/17.
 *
 * This class represents output format
 */
public class Output {

    private Customer customer;
    private double customerLTV;

    public Output(Customer customer, double customerLTV) {
        this.customer = customer;
        this.customerLTV = customerLTV;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getCustomerLTV() {
        return customerLTV;
    }

    public void setCustomerLTV(double customerLTV) {
        this.customerLTV = customerLTV;
    }
}
