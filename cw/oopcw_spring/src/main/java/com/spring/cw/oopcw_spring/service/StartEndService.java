package com.spring.cw.oopcw_spring.service;

import com.spring.cw.oopcw_spring.ticketpool.TicketPool;
import com.spring.cw.oopcw_spring.configuration.Configuration;
import com.spring.cw.oopcw_spring.model.Customer;
import com.spring.cw.oopcw_spring.model.Vendor;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * The StartEndService class is a Spring service responsible for starting, stopping,
 * and managing the state of the ticketing system. It handles loading configuration,
 * managing vendors and customers, and interacting with the ticket pool.
 */
@Service
public class StartEndService {
    // Lists to hold vendor and customer objects
    List<Vendor> vendors = new LinkedList<>();
    List<Customer> customers = new LinkedList<>();

    // Configuration object to hold system settings
    Configuration configuration;

    // TicketPool object to manage the tickets
    private final TicketPool ticketPool = new TicketPool();

    /**
     * Returns the current size of the ticket pool.
     * @return the number of tickets in the pool
     */
    public int getTicketPoolSize() {
        return ticketPool.getTicketsSize();
    }

    /**
     * Loads the configuration from a JSON file.
     * @param filepath the path to the JSON file
     * @return the Configuration object loaded from the file
     */
    public Configuration loadFromJason(String filepath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filepath)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reloads the configuration from the JSON file and updates the ticket pool.
     */
    public void reloadConfiguration() {
        this.configuration = loadFromJason("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\config.json");
        ticketPool.setConfiguration(configuration); // Update the ticket pool with the new configuration
    }

    /**
     * Constructs a new StartEndService and loads the initial configuration.
     */
    public StartEndService() {
        this.configuration = loadFromJason("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\config.json");
        ticketPool.setConfiguration(configuration);
    }

    /**
     * Starts the ticketing system by creating and starting vendor and customer threads.
     */
    public void startSystem() {
        // Create and start vendor threads
        Thread[] vendorThreads = new Thread[configuration.getTotalVendors()];
        for (int i = 0; i < configuration.getTotalVendors(); i++) {
            Vendor vendor = new Vendor(ticketPool, i + 1);
            vendorThreads[i] = new Thread(vendor, "Vendor :" + (i + 1));
            vendor.startVendor();
            vendors.add(vendor);
        }

        // Create and start customer threads
        Thread[] customerThreads = new Thread[configuration.getTotalCustomers()];
        for (int i = 0; i < configuration.getTotalCustomers(); i++) {
            Customer customer = new Customer(ticketPool, i + 1);
            customerThreads[i] = new Thread(customer, "Customer :" + (i + 1));
            customer.startCustomer();
            customers.add(customer);
        }
    }

    /**
     * Stops the ticketing system by stopping all vendor and customer threads.
     */
    public void stopSystem() {
        for (Vendor vendor : vendors) {
            vendor.stopVendor();
        }

        for (Customer customer : customers) {
            customer.stopCustomer();
        }
        System.out.println("Stopped by the USER\n");
    }
}
