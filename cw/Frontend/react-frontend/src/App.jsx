import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [config, setConfig] = useState({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0,
    totalVendors: 0,
    totalCustomers: 0,
  });
  const [availableTickets, setAvailableTickets] = useState(0); // State for available tickets

  // Fetch available tickets and set interval to fetch periodically
  useEffect(() => {
    const fetchAvailableTickets = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/ticketpool'); // Your endpoint
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json(); // Assuming the backend returns a JSON with the ticket count
        setAvailableTickets(data); // Set the available tickets in state
      } catch (error) {
        console.error('Error fetching available tickets:', error);
      }
    };

    // Fetch available tickets initially
    fetchAvailableTickets();

    // Set up interval to fetch available tickets every 2 seconds (2000ms)
    const intervalId = setInterval(fetchAvailableTickets, 1000);

    // Cleanup interval on component unmount
    return () => clearInterval(intervalId);
  }, []); // Empty dependency array ensures this runs only once when the component mounts

  const handleStart = async () => {
    try {
      await axios.post('http://localhost:8080/api/start');
      alert('Service started successfully!');
    } catch (error) {
      console.error('Error starting service:', error);
      alert('Failed to start service.');
    }
  };

  const handleStop = async () => {
    try {
      await axios.post('http://localhost:8080/api/end');
      alert('Service stopped successfully!');
    } catch (error) {
      console.error('Error stopping service:', error);
      alert('Failed to stop service.');
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfig({
      ...config,
      [name]: parseInt(value, 10),
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/configure', config);
      alert('Configuration submitted successfully!');
    } catch (error) {
      console.error('Error submitting configuration:', error);
      alert('Failed to submit configuration.');
    }
  };

  return (
    <div className="App" style={styles.container}>
      <header style={styles.header}>
        <h1 style={styles.title}>Real-Time Ticketing Event</h1>
        <p style={styles.subtitle}>Efficiently manage your ticketing system</p>
      </header>

      {/* Available Tickets Display */}
      <div style={styles.availableTicketsContainer}>
        <h2 style={styles.availableTicketsTitle}>Available Tickets: {availableTickets}</h2>
      </div>

      <div style={styles.buttonContainer}>
        <button style={styles.button} onClick={handleStart}>
          Start
        </button>
        <button style={{ ...styles.button, backgroundColor: '#dc3545' }} onClick={handleStop}>
          Stop
        </button>
      </div>

      <form onSubmit={handleSubmit} style={styles.form}>
        <h2 style={styles.formTitle}>Configure Settings</h2>

        {Object.keys(config).map((key) => (
          <div key={key} style={styles.inputGroup}>
            <label style={styles.label}>{formatLabel(key)}:</label>
            <input
              type="number"
              name={key}
              value={config[key]}
              onChange={handleChange}
              style={styles.input}
            />
          </div>
        ))}

        <button type="submit" style={styles.submitButton}>
          Submit Configuration
        </button>
      </form>
    </div>
  );
}

const styles = {
  container: {
    fontFamily: "'Roboto', sans-serif",
    maxWidth: '800px',
    margin: 'auto',
    padding: '20px',
    backgroundColor: '#f9f9f9',
    borderRadius: '8px',
    boxShadow: '0 2px 8px rgba(0, 0, 0, 0.1)',
  },
  header: {
    textAlign: 'center',
    marginBottom: '20px',
  },
  title: {
    fontSize: '28px',
    color: '#343a40',
  },
  subtitle: {
    fontSize: '16px',
    color: '#6c757d',
  },
  availableTicketsContainer: {
    textAlign: 'center',
    marginBottom: '20px',
  },
  availableTicketsTitle: {
    fontSize: '24px',
    color: '#495057',
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'center',
    marginBottom: '20px',
  },
  button: {
    backgroundColor: '#28a745',
    color: '#fff',
    padding: '10px 20px',
    border: 'none',
    borderRadius: '4px',
    fontSize: '16px',
    marginRight: '10px',
    cursor: 'pointer',
  },
  form: {
    backgroundColor: '#fff',
    padding: '20px',
    borderRadius: '8px',
    boxShadow: '0 2px 8px rgba(0, 0, 0, 0.1)',
  },
  formTitle: {
    fontSize: '20px',
    color: '#495057',
    marginBottom: '15px',
    textAlign: 'center',
  },
  inputGroup: {
    marginBottom: '15px',
    display: 'flex',
    flexDirection: 'column',
  },
  label: {
    fontSize: '14px',
    marginBottom: '5px',
    color: '#495057',
  },
  input: {
    padding: '8px',
    fontSize: '14px',
    border: '1px solid #ced4da',
    borderRadius: '4px',
  },
  submitButton: {
    backgroundColor: '#007bff',
    color: '#fff',
    padding: '10px',
    border: 'none',
    borderRadius: '4px',
    width: '100%',
    fontSize: '16px',
    cursor: 'pointer',
  },
};

const formatLabel = (label) =>
  label
    .replace(/([A-Z])/g, ' $1')
    .replace(/^./, (str) => str.toUpperCase());

export default App;
