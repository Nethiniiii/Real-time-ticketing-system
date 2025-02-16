import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Configuration class manages the settings for a ticketing system, allowing
 * configuration of total tickets, ticket release rate, customer retrieval rate,
 * maximum ticket capacity, total vendors, and total customers.
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int totalVendors;
    private int totalCustomers;

    /**
     * Gets the total number of tickets.
     * @return totalTickets
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    /**
     * Gets the ticket release rate.
     * @return ticketReleaseRate
     */
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    /**
     * Gets the customer retrieval rate.
     * @return customerRetrievalRate
     */
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Gets the maximum ticket capacity.
     * @return maxTicketCapacity
     */
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    /**
     * Gets the total number of vendors.
     * @return totalVendors
     */
    public int getTotalVendors() {
        return totalVendors;
    }

    /**
     * Gets the total number of customers.
     * @return totalCustomers
     */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Sets the total number of tickets.
     * @param totalTickets the total number of tickets to set
     */
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    /**
     * Sets the ticket release rate.
     * @param ticketReleaseRate the ticket release rate to set
     */
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     * Sets the customer retrieval rate.
     * @param customerRetrievalRate the customer retrieval rate to set
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    /**
     * Sets the maximum ticket capacity.
     * @param maxTicketCapacity the maximum ticket capacity to set
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Sets the total number of vendors.
     * @param totalVendors the total number of vendors to set
     */
    public void setTotalVendors(int totalVendors) {
        this.totalVendors = totalVendors;
    }

    /**
     * Sets the total number of customers.
     * @param totalCustomers the total number of customers to set
     */
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    private transient Scanner input = new Scanner(System.in);

    /**
     * Prompts the user to enter the number of total tickets and sets the value.
     */
    public void Tot_ticket_inputs() {
        while (true) {
            System.out.print("Please enter the number of total Tickets: ");
            try {
                int tot_tickets = input.nextInt();
                if (tot_tickets >= 0) {
                    setTotalTickets(tot_tickets);
                    break;
                } else {
                    System.out.println("Please enter a valid number");
                }
            } catch (Exception e) {
                System.out.println(e + "." + "\n Please enter a valid number");
                input.nextLine();
            }
        }
    }

    /**
     * Prompts the user to enter the number of total vendors and sets the value.
     */
    public void Tot_vendor_inputs() {
        while (true) {
            System.out.print("Please enter the number of total vendors: ");
            try {
                int tot_vendors = input.nextInt();
                if (tot_vendors >= 0) {
                    setTotalVendors(tot_vendors);
                    break;
                } else {
                    System.out.println("Please enter a valid number");
                }
            } catch (Exception e) {
                System.out.println(e + "." + "\n Please enter a valid number");
                input.nextLine();
            }
        }
    }

    /**
     * Prompts the user to enter the number of total customers and sets the value.
     */
    public void Tot_customer_inputs() {
        while (true) {
            System.out.print("Please enter the number of total customers: ");
            try {
                int tot_customers = input.nextInt();
                if (tot_customers >= 0) {
                    setTotalCustomers(tot_customers);
                    break;
                } else {
                    System.out.println("Please enter a valid number");
                }
            } catch (Exception e) {
                System.out.println(e + "." + "\n Please enter a valid number");
                input.nextLine();
            }
        }
    }

    /**
     * Prompts the user to enter the customer retrieval rate and sets the value.
     */
    public void CustomerRetrievalRate() {
        while (true) {
            System.out.print("Please enter the customer retrieval rate : ");
            try {
                int tot_cus_rate = input.nextInt();
                if (tot_cus_rate >= 0) {
                    setCustomerRetrievalRate(tot_cus_rate);
                    break;
                } else {
                    System.out.println("Please enter a valid number");
                }
            } catch (Exception e) {
                System.out.println(e + "." + "\n Please enter a valid number");
                input.nextLine();
            }
        }
    }

    /**
     * Prompts the user to enter the ticket release rate and sets the value.
     */
    public void TicketReleaseRate() {
        while (true) {
            System.out.print("Please enter the ticket release rate  : ");
            try {
                int tot_rel_rate = input.nextInt();
                if (tot_rel_rate >= 0) {
                    setTicketReleaseRate(tot_rel_rate);
                    break;
                } else {
                    System.out.println("Please enter a valid number");
                }
            } catch (Exception e) {
                System.out.println(e + "." + "\n Please enter a valid number");
                input.nextLine();
            }
        }
    }

    /**
     * Prompts the user to enter the maximum ticket capacity and sets the value.
     */
    public void MaxTicketCapacity() {
        while (true) {
            System.out.print("Please enter the maximum ticket capacity  : ");
            try {
                int max_tic_capacity = input.nextInt();
                if (max_tic_capacity >= 0) {
                    setMaxTicketCapacity(max_tic_capacity);
                    break;
                } else {
                    System.out.println("Please enter a valid number");
                }
            } catch (Exception e) {
                System.out.println(e + "." + "\n Please enter a valid number");
                input.nextLine();
            }
        }
    }

    /**
     * Saves the configuration to a JSON file.
     * @param filepath the file path where the configuration will be saved
     */
    public void saveToJson(String filepath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filepath)) {
            gson.toJson(this, writer); // Serialize this Configuration object to JSON
            System.out.println("Configuration saved successfully to " + filepath);
        } catch (IOException e) {
            System.out.println("Failed to save configuration. Please check the file path.");
            e.printStackTrace();
        }
    }
}
