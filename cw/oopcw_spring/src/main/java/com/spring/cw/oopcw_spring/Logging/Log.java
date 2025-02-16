package com.spring.cw.oopcw_spring.Logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The Log class provides methods to log messages and errors to specified log files.
 * It appends timestamped log entries to these files.
 */
public class Log {

    /**
     * Logs a general message to the Logs.txt file.
     *
     * @param message the message to log
     */
    public void Save_message(String message){
        try {
            // Create FileWriter in append mode to add logs to the end of the file
            FileWriter file = new FileWriter("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_spring\\src\\main\\java\\com\\spring\\cw\\oopcw_spring\\Logging\\Logs.txt", true);
            // Write the message to the file with a timestamp
            file.write("\n");
            file.write(LocalDateTime.now().toString() + ": " + message + "\n");
            file.flush();
        } catch (IOException e) {
            // Throw runtime exception in case of an I/O error
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
            // Create FileWriter in append mode to add errors to the end of the file
            FileWriter file = new FileWriter("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_spring\\src\\main\\java\\com\\spring\\cw\\oopcw_spring\\Logging\\Errors.txt", true);
            // Write the error message to the file with a timestamp
            file.write("\n");
            file.write(LocalDateTime.now().toString() + ": " + message + "\n");
            file.flush();
        } catch (IOException e) {
            // Throw runtime exception in case of an I/O error
            throw new RuntimeException(e);
        }
    }
}
