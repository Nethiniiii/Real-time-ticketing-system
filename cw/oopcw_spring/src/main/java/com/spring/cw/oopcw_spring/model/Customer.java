package com.spring.cw.oopcw_spring.model;

import com.spring.cw.oopcw_spring.ticketpool.TicketPool;

/**
 * The Customer class implements Runnable, representing a customer that removes tickets from the TicketPool.
 * It runs in a separate thread, removing tickets from the pool as long as the customer is active.
 */
public class Customer implements Runnable {
    // Ticket pool that the customer will interact with
    private final TicketPool ticketPool;
    // Unique identifier for the customer
    private final int customerNO;
    // Flag to indicate if the customer is running
    private boolean running = false;
    // Flag to control the loop execution
    private boolean cont = true;

    /**
     * Constructs a new Customer with the specified ticket pool and customer number.
     *
     * @param ticketPool the ticket pool to interact with
     * @param customerNO the unique identifier for the customer
     */
    public Customer(TicketPool ticketPool, int customerNO) {
        this.ticketPool = ticketPool;
        this.customerNO = customerNO;
    }

    /**
     * Starts the customer's thread and sets the running flag to true.
     */
    public void startCustomer(){
        running = true;
        new Thread(this).start();
    }

    /**
     * Stops the customer's thread by setting the running flag to false.
     */
    public void stopCustomer(){
        this.running = false;
    }

    /**
     * The run method is executed when the thread is started.
     * It attempts to remove tickets from the ticket pool until no more tickets can be purchased
     * or the customer is stopped.
     */
    @Override
    public void run() {
        while (cont && running) {
            cont = ticketPool.Remove_Ticket(customerNO);
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
