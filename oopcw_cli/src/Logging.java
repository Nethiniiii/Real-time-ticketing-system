import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The Logging class provides methods for logging messages and errors to specific log files.
 * It appends each log entry with a timestamp for better traceability.
 */

public class Logging {

    /**
     * Logs a general message to the Logs.txt file.
     *
     * @param message the message to log
     */

    public void Save_message(String message){
        try {
            // FileWriter in append mode to keep adding new logs to the existing file
            FileWriter file = new FileWriter("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\Logs.txt", true);
            file.write("\n");
            file.write(LocalDateTime.now().toString() + ": " + message + "\n"); // Append timestamp and message
            file.flush(); // Ensure all data is written to the file
        } catch (IOException e) {
            // Handle any I/O exceptions that occur
            throw new RuntimeException(e);
        }
    }

    /**
     * Logs an error message to the Errors.txt file.
     *
     * @param message the error message to log
     */
    public void Save_error(String message){
        try {
            // FileWriter in append mode to keep adding new errors to the existing file
            FileWriter file = new FileWriter("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\Errors.txt", true);
            file.write("\n");
            file.write(LocalDateTime.now().toString() + ": " + message + "\n"); // Append timestamp and error message
            file.flush(); // Ensure all data is written to the file
        } catch (IOException e) {
            // Handle any I/O exceptions that occur in this code
            throw new RuntimeException(e);
        }
    }
}

