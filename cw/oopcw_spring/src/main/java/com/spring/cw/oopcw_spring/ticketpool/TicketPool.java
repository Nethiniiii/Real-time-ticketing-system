package com.spring.cw.oopcw_spring.ticketpool;

import com.google.gson.Gson;
import com.spring.cw.oopcw_spring.Logging.Log;
import com.spring.cw.oopcw_spring.configuration.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * The TicketPool class manages the pool of tickets in the system. It handles the addition
 * of tickets by vendors and the removal of tickets by customers, ensuring thread safety and
 * logging operations.
 */
public class TicketPool {
    private int ticketsAdded = 0;
    private int ticketsPurchased = 0;

    // Configuration object to hold the settings
    Configuration configuration;
    // List to hold the tickets
    List<String> tickets = new LinkedList<>();
    // Log object for logging messages and errors
    Log log = new Log();

    /**
     * Constructor for TicketPool class. Loads configuration from a JSON file.
     */
    public TicketPool() {
        this.configuration = loadFromJason("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\config.json");
    }

    /**
     * Adds tickets to the pool. Each vendor can add tickets according to the ticket release rate.
     * Ensures thread safety using synchronized keyword.
     *
     * @param vendorID the ID of the vendor adding tickets
     * @return true if tickets are still being added, false if all tickets have been added
     */
    public synchronized Boolean Add_Ticket(int vendorID) {
        if (ticketsAdded >= configuration.getTotalTickets()) {
            return false;
        }

        for (int i = 1; i <= configuration.getTicketReleaseRate(); i++) {
            if (ticketsAdded < configuration.getTotalTickets() && tickets.size() < configuration.getMaxTicketCapacity()) {
                tickets.add("T");
                ticketsAdded++;
                System.out.println("Vendor no " + vendorID + " add a ticket\n     Current Ticket Pool :" + tickets.size() + "\n     Total added tickets: " + ticketsAdded + "\n");
                log.Save_message("Vendor no " + vendorID + " add a ticket.\n     Current Ticket Pool :" + tickets.size() + "\n     Total added tickets: " + ticketsAdded);

                try {
                    Thread.sleep(1500);

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    log.Save_error("Current Ticket Pool : " + tickets.size());
                }

            } else {
                System.out.println("Vendor number " + vendorID + " is trying.\nTicketpool is full....Wait a bit\n");
                log.Save_message("Vendor number " + vendorID + " is trying.\n     Ticketpool is full....Wait a bit\n");
                break;
            }
        }
        return ticketsAdded < configuration.getTotalTickets();
    }

    /**
     * Removes tickets from the pool. Each customer can remove tickets according to the customer retrieval rate.
     * Ensures thread safety using synchronized keyword.
     *
     * @param customerNO the number of the customer removing tickets
     * @return true if tickets are still being purchased, false if all tickets have been purchased
     */
    public synchronized Boolean Remove_Ticket(int customerNO) {
        if (ticketsPurchased >= configuration.getTotalTickets()) {
            return false;
        }

        for (int i = 1; i <= configuration.getCustomerRetrievalRate(); i++) {
            if (!tickets.isEmpty()) {
                tickets.remove(0);
                ticketsPurchased++;
                System.out.println("Customer no " + customerNO + " purchased a ticket\n     Current Ticket Pool : " + tickets.size() + "\n     Total tickets purchased: " + ticketsPurchased + "\n");
                log.Save_message("Customer no " + customerNO + " purchased a ticket\n     Current Ticket Pool : " + tickets.size() + "\n     Total tickets purchased: " + ticketsPurchased);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    log.Save_error("Current Ticket Pool : " + tickets.size());
                }
            } else {
                System.out.println("Customer number " + customerNO + " is trying.\nTicketpool is empty.\n");
                log.Save_message("Customer number " + customerNO + " is trying.\n     Ticketpool is empty\n");
                break;
            }
        }
        return ticketsPurchased < configuration.getTotalTickets();
    }

    /**
     * Loads configuration from a JSON file.
     *
     * @param filepath the path of the JSON file to load configuration from
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
     * Sets a new configuration for the TicketPool.
     *
     * @param configuration the new configuration to set
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        System.out.println("TicketPool configuration updated: " + configuration);
    }

    /**
     * Gets the current size of the ticket pool.
     *
     * @return the number of tickets currently in the pool
     */
    public int getTicketsSize() {
        return tickets.size();
    }
}
