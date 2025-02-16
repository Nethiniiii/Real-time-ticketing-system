/**
 * The Main class serves as the entry point to the ticketing system application.
 * It configures the system settings, saves the configuration, and starts vendor and customer threads.
 */
public class Main {
    public static void main(String[] args) {
        // Create a new configuration instance
        Configuration configuration = new Configuration();

        // Prompt user to input total tickets
        configuration.Tot_ticket_inputs();

        // Prompt user to input total vendors
        configuration.Tot_vendor_inputs();

        // Prompt user to input total customers
        configuration.Tot_customer_inputs();

        // Prompt user to input ticket release rate
        configuration.TicketReleaseRate();

        // Prompt user to input customer retrieval rate
        configuration.CustomerRetrievalRate();

        // Prompt user to input maximum ticket capacity
        configuration.MaxTicketCapacity();

        // Save the configuration to a JSON file
        configuration.saveToJson("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\config.json");

        // Create a new ticket pool instance
        TicketPool ticketPool = new TicketPool();

        // Create and start vendor threads
        // Create an array to hold vendor threads
        Thread[] vendorThreads = new Thread[configuration.getTotalVendors()];

        for (int i = 0; i < configuration.getTotalVendors(); i++) {
            // Create a new Vendor instance
            Vendor vendor = new Vendor(ticketPool, i + 1);
            // Create a new Thread with the vendor instance, and give it a name for easier identification
            vendorThreads[i] = new Thread(vendor, "Vendor :" + (i + 1));
            // Start the vendor thread
            vendorThreads[i].start();
        }

        // Create and start customer threads
        // Create an array to hold customer threads
        Thread[] customerThreads = new Thread[configuration.getTotalCustomers()];
        for (int i = 0; i < configuration.getTotalCustomers(); i++) {
            // Create an array to hold customer threads
            Customer customer = new Customer(ticketPool, i + 1);
            // Create a new Thread with the customer instance, and give it a name for easier identification
            customerThreads[i] = new Thread(customer, "Customer :" + (i + 1));
            // Start the customer thread
            customerThreads[i].start();
        }
    }
}



