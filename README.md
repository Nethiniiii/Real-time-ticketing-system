# Real-Time Ticketing Event System


## Introduction

The Real-Time Ticketing Event System is a comprehensive application developed using Spring Boot and React. This system efficiently manages ticket distribution in real-time by simulating a ticket pool where vendors add tickets, and customers retrieve them. The backend runs on localhost:8080, and the frontend runs on localhost:5173. Additionally, a CLI component is built using pure Java to configure the system settings. This system provides a user-friendly interface to manage the ticketing process seamlessly.

## Setup Instructions

### Prerequisites
1. Java Development Kit (JDK): Version 8 or higher
2. Apache Maven: For building the backend application
3. Node.js: Version 14.x or higher for running the frontend
4. npm (Node Package Manager): For managing frontend dependencies
5. Spring Boot: For running the backend services

### How to Build and Run the Application

#### Backend Setup(Spring Boot)

1. Clone the repository: git clone https://github.com/your-username/real-time-ticketing-event.git

2. Navigate to the backend directory: cd real-time-ticketing-event/backend

3. Build the backend application using Maven: mvn clean install

4. Run the backend application: mvn spring-boot:run

#### Frontend Setup

1. Navigate to the frontend directory: cd ../frontend

2. Install the dependencies: npm install

3. Run the frontend application: npm start

## Usage Instructions

### How to Configure and Start the System

1. Open the frontend application:
- Open your web browser and navigate to http://localhost:5173.

2. Configure the system:
- Fill in the configuration form with the following settings:
    - Total Tickets: The total number of tickets that can be added to the system.
    - Ticket Release Rate: The rate at which vendors add tickets to the pool.
    - Customer Retrieval Rate: The rate at which customers retrieve tickets from the pool.
    - Maximum Ticket Capacity: The maximum number of tickets that the pool can hold at any time.
    - Total Vendors: The total number of vendors adding tickets to the pool.
    - Total Customers: The total number of customers retrieving tickets from the pool.

3. Submit the configuration:
- Click on the Submit Configuration button to save the settings. A success message will be displayed upon successful submission.

4. Start the system:
- Click on the Start button to start the ticketing system. Vendors will begin adding tickets, and customers will start retrieving them.

5. Stop the system:
- Click on the Stop button to stop all threads, halting the ticketing process.

## Explanation of UI Controls

1. Configuration Form:
    - Total Tickets: Enter the total number of tickets for the event.
    - Ticket Release Rate: Specify the rate at which tickets are added by vendors.
    - Customer Retrieval Rate: Specify the rate at which tickets are retrieved by customers.
    - Maximum Ticket Capacity: Set the maximum number of tickets the pool can hold.
    - Total Vendors: Enter the number of vendors that will add tickets.
    - Total Customers: Enter the number of customers that will retrieve tickets.

2. Available Tickets Display:
    - Displays the current number of available tickets in the pool in real-time.

3. Start Button:
    - Starts the ticketing system, initiating all vendor and customer threads.

4. Stop Button:
    - Stops the ticketing system, halting all vendor and customer threads.

## API Endpoints

1. POST /api/start: Starts the ticketing system.
2. POST /api/end: Stops the ticketing system.
3. POST /api/configure: Configures the system settings.
4. GET /api/ticketpool: Retrieves the current size of the ticket pool.

## Logging

The system maintains logs of significant events and errors. Log files are stored in the following locations:
    - Events Log: Logs.txt
    - Errors Log: Errors.txt

## Troubleshooting

    - Ensure all prerequisites are installed and configured correctly.
    - Check the log files for any error messages that might indicate issues.
    - Ensure the backend server is running before starting the frontend application.
