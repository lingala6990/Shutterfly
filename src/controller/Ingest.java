package controller;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.Main;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Arjun on 8/8/17.
 *
 * This class provides methods to ingest the data reading from the file
 */
public class Ingest {

    /**
     *
     * This method reads data from the inputFilePath and loads data
     *
     * @param inputFilePath path of the input file
     * @return data
     */
    public static Data readInputFileAndIngest(String inputFilePath) {

        Data data = new Data();
        // Initializing JSON Parser
        JSONParser parser = new JSONParser();

        try {
            JSONArray input = (JSONArray) parser.parse(new FileReader(inputFilePath));

            // Iterate through each input event and ingest the data
            Iterator<JSONObject> itr = input.iterator();

            while(itr.hasNext()) {
                JSONObject event = (JSONObject) itr.next();
                try {
                    ingest(event, data);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data;

    }

    /**
     *
     * Ingest method reads the event info and determines the operation and ingests into data
     *
     * @param e Event
     * @param data Data
     * @throws java.text.ParseException
     */
    private static void ingest(JSONObject e, Data data) throws java.text.ParseException {

        // read type, verb and key from the input json
        String type = (String) e.get("type");
        String verb = (String) e.get("verb");
        String key = (String) e.get("key");

        // converting the event_time to Timestamp
        SimpleDateFormat sdf = new SimpleDateFormat(Main.timestampPattern);
        Timestamp eventTime = new Timestamp(sdf.parse((String) e.get("event_time")).getTime());

        // if the event type is Customer, read the values, create customer object and ingest into data
        if ("CUSTOMER".equalsIgnoreCase(type)) {
            String lastName = (String) e.get("last_name");
            String adrCity = (String) e.get("adr_city");
            String adrState = (String) e.get("adr_state");

            Customer customer = new Customer(key, eventTime, lastName, adrCity, adrState);
            addOrUpdateCustomer(customer, data, verb);

        }
        // if the event type is Order, read the values, create the Order object and ingest into data
        else if ("ORDER".equalsIgnoreCase(type)) {
            String customerId = (String) e.get("customer_id");
            String totalAmountStr = (String) e.get("total_amount");
            // filtering out the 'USD' from the string and storing the double value
            double totalAmount = Double.parseDouble(totalAmountStr.split(" ", -1)[0]);

            Order order = new Order(key, eventTime, customerId, totalAmount);
            addOrUpdateOrder(order, data, verb);

        }
        // if the event type is SiteVisit, read the values, create the SiteVisit object and ingest into data
        else if ("SITE_VISIT".equalsIgnoreCase(type)) {
            String customerId = (String) e.get("customer_id");
            Map<String, String> tags = new HashMap<>();
            // ignoring the tags for siteVisit as it is irrelevant to this scenario

            SiteVisit siteVisit = new SiteVisit(key, eventTime, customerId, tags);
            addSiteVisit(siteVisit, data);

        }
        // if the event type is Image, read the values, create the Image object and ingest into data
        else if ("IMAGE".equalsIgnoreCase(type)) {

            String customerId = (String) e.get("customer_id");
            String cameraMake = (String) e.get("camera_make");
            String cameraModel = (String) e.get("camera_model");

            ImageUpload imageUpload = new ImageUpload(key, eventTime, customerId, cameraMake, cameraModel);
            addImage(imageUpload, data);

        }
        // if the event type is not as expected ignore it
        else {
            System.out.println("Invalid Event type : " + type);
        }

    }

    /**
     *
     * This method adds or updates customer object to the data
     *
     * @param customer
     * @param data
     * @param operation
     */
    private static void addOrUpdateCustomer(Customer customer, Data data, String operation) {

        // inserting the customer object into Data irrespective of the operation as the data structure using is HashMap
        if (data.getCustomers().containsKey(customer.getCustomerId())) {
            System.out.println("Updating the customer : " + customer.getCustomerId());
        }
        data.getCustomers().put(customer.getCustomerId(), customer);

    }

    /**
     *
     * This method adds or updates order object to the data
     *
     * @param order
     * @param data
     * @param operation
     */
    private static void addOrUpdateOrder(Order order, Data data, String operation) {

        if (!data.getOrders().containsKey(order.getCustomerId())) {
            data.getOrders().put(order.getCustomerId(), new ArrayList<Order>());
        }

        List<Order> customerOrders = data.getOrders().get(order.getCustomerId());

        // if the operation is 'UPDATE'
        if (operation.equalsIgnoreCase("UPDATE")) {
            List<Order> updatedOrders = data.getOrders().get(order.getCustomerId());
            for (Order eachOrder : customerOrders) {
                if (eachOrder.getOrderId().equalsIgnoreCase(order.getOrderId())) {
                    updatedOrders.add(order);
                } else {
                    updatedOrders.add(eachOrder);
                }
            }
            data.getOrders().put(order.getCustomerId(), updatedOrders);
        }
        // if the operation is 'NEW'
        else if (operation.equalsIgnoreCase("NEW")) {
            customerOrders.add(order);
            data.getOrders().put(order.getCustomerId(), customerOrders);
        } else {
            System.out.println("Invalid Operation");
        }

    }

    /**
     *
     * This method ingests site visit into the data
     *
     * @param siteVisit
     * @param data
     */
    private static void addSiteVisit(SiteVisit siteVisit, Data data) {

        if (!data.getSiteVisits().containsKey(siteVisit.getCustomerId())) {
            data.getSiteVisits().put(siteVisit.getCustomerId(), new ArrayList<SiteVisit>());
        }

        List<SiteVisit> customerSiteVisits = data.getSiteVisits().get(siteVisit.getCustomerId());
        customerSiteVisits.add(siteVisit);
        data.getSiteVisits().put(siteVisit.getCustomerId(), customerSiteVisits);

    }

    /**
     *
     *This method ingests image data into the data
     *
     * @param imageUpload
     * @param data
     */
    private static void addImage(ImageUpload imageUpload, Data data) {
        if (!data.getImageUploads().containsKey(imageUpload.getCustomerId())) {
            data.getImageUploads().put(imageUpload.getCustomerId(), new ArrayList<ImageUpload>());
        }

        List<ImageUpload> customerImages = data.getImageUploads().get(imageUpload.getCustomerId());
        customerImages.add(imageUpload);
        data.getImageUploads().put(imageUpload.getCustomerId(), customerImages);
    }

}
