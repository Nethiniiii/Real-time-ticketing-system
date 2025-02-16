package com.spring.cw.oopcw_spring.controller;

import com.spring.cw.oopcw_spring.configuration.Configuration;
import com.spring.cw.oopcw_spring.service.StartEndService;
import org.springframework.web.bind.annotation.*;

/**
 * TicketController is a REST controller that provides endpoints to start, stop,
 * configure the ticketing system, and get the current size of the ticket pool.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api")
public class TicketController {

    // Service to manage the start and end of the ticketing system
    private final StartEndService startEndService = new StartEndService();

    /**
     * Endpoint to start the ticketing system.
     * @return a success message
     */
    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/start")
    public String startTheSystem() {
        startEndService.startSystem();
        return "success";
    }

    /**
     * Endpoint to stop the ticketing system.
     * @return a success message
     */
    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/end")
    public String endSystem() {
        startEndService.stopSystem();
        return "success";
    }

    /**
     * Endpoint to configure the ticketing system.
     * @param config the configuration settings for the ticketing system
     * @return a message indicating that the configuration was saved
     */
    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/configure")
    public String configureSystem(@RequestBody Configuration config) {
        config.saveToJson("D:\\sem 1\\Object Oriented Programming\\cw\\oopcw_cli\\src\\config.json");
        startEndService.reloadConfiguration(); // Reload the updated configuration
        return "Configuration saved!";
    }

    /**
     * Endpoint to get the current size of the ticket pool.
     * @return the number of available tickets in the pool
     */
    @CrossOrigin(origins = "http://localhost:5173/")
    @GetMapping("/ticketpool")
    public int getCurrentTicketPool() {
        int availableTickets = startEndService.getTicketPoolSize(); // Get current tickets in the pool
        return availableTickets;
    }
}
