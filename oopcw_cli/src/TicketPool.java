import com.google.gson.Gson;
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
    // Logging object for logging messages and errors
    Logging logging = new Logging();

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
                logging.Save_message("  Vendor no " + vendorID + " add a ticket.\n     Current Ticket Pool :" + tickets.size() + "\n     Total added tickets: " + ticketsAdded);

                try {
                    Thread.sleep(1500);

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    logging.Save_error(e.getMessage());
                }

            } else {
                System.out.println("Vendor number " + vendorID + " is trying.\nTicketpool is full....Wait a bit\n");
                logging.Save_message("Vendor number " + vendorID + " is trying.\n     Ticketpool is full....Wait a bit\n");
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
                logging.Save_message("Customer no " + customerNO + " purchased a ticket\n     Current Ticket Pool : " + tickets.size() + "\n     Total tickets purchased: " + ticketsPurchased);

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    logging.Save_error(e.getMessage());
                }
            } else {
                System.out.println("Customer number " + customerNO + " is trying.\nTicketpool is empty\n");
                logging.Save_message("Customer number " + customerNO + " is trying.\n     Ticketpool is empty\n");
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
}





