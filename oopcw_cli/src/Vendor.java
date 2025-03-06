/**
 * The Vendor class implements Runnable, representing a vendor in the ticketing system.
 * Each vendor attempts to add tickets to the TicketPool in a separate thread.
 */
public class Vendor implements Runnable {
    // Ticket pool that the vendor will interact with
    private final TicketPool ticketPool;
    // Unique identifier for the vendor
    private final int vendorID;

    // Logging instance for recording messages and errors
    Logging logging = new Logging();

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
     * The run method is executed when the thread is started.
     * It attempts to add tickets to the ticket pool until no more tickets can be added.
     */
    @Override
    public void run() {
        while (ticketPool.Add_Ticket(vendorID)) {
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






