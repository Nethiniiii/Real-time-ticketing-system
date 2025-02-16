package com.spring.cw.oopcw_spring.model;

import com.spring.cw.oopcw_spring.ticketpool.TicketPool;

/**
 * The Vendor class implements Runnable, representing a vendor that adds tickets to the TicketPool.
 * It runs in a separate thread, adding tickets to the pool as long as the vendor is active.
 */
public class Vendor implements Runnable {
    // Ticket pool that the vendor will interact with
    private final TicketPool ticketPool;
    // Unique identifier for the vendor
    private final int vendorID;
    // Flag to indicate if the vendor is running
    private boolean running = false;
    // Flag to control the loop execution
    private boolean cont = true;

    /**
     * Constructs a new Vendor with the specified ticket pool and vendor ID.
     *
     * @param ticketPool the ticket pool to interact with
     * @param vendorID the unique identifier for the vendor
     */
    public Vendor(TicketPool ticketPool, int vendorID) {
        this.ticketPool = ticketPool;
        this.vendorID = vendorID;
    }

    /**
     * Starts the vendor's thread and sets the running flag to true.
     */
    public void startVendor() {
        this.running = true;
        new Thread(this).start();
    }

    /**
     * Stops the vendor's thread by setting the running flag to false.
     */
    public void stopVendor() {
        this.running = false;
    }

    /**
     * The run method is executed when the thread is started.
     * It attempts to add tickets to the ticket pool until no more tickets can be added
     * or the vendor is stopped.
     */
    @Override
    public void run() {
        while (cont && running) {
            cont = ticketPool.Add_Ticket(vendorID);
            try {
                // Introduce a small delay to simulate real-world behavior and avoid busy-waiting
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
            // Yield the processor to allow other threads to execute
            Thread.yield();
        }
    }
}
