/**
 * The Customer class implements Runnable, representing a customer in the ticketing system.
 * Each customer attempts to remove tickets from the TicketPool in a separate thread.
 */
public class Customer implements Runnable {
    // Ticket pool that the customer will interact with
    private final TicketPool ticketPool;
    // Unique identifier for the customer
    private final int customerNO;

    // Logging instance for recording messages and errors
    Logging logging = new Logging();

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
     * The run method is executed when the thread is started.
     * It attempts to remove tickets from the ticket pool until no more tickets can be purchased.
     */
    @Override
    public void run() {
        while (ticketPool.Remove_Ticket(customerNO)) {
            try {
                // Introduce a small delay to simulate real-world behavior and avoid busy-waiting
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
                logging.Save_error("Thread interrupted: " + e.getMessage());
            }
            // Yield the processor to allow other threads to execute
            Thread.yield();
        }
    }
}





