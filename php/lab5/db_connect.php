<?php
/**
 * A class file to connect to database using MySQLi
 */
class DB_CONNECT {
    private $conn;

    // constructor
    function __construct() {
        $this->connect();
    }

    // destructor
    function __destruct() {
        $this->close();
    }

    /**
     * Function to connect with database using MySQLi
     */
    function connect() {
        // include database connection variables
        require_once __DIR__ . '/db_config.php';

        // Connecting to mysql database using MySQLi
        $this->conn = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

        // Check connection
        if ($this->conn->connect_error) {
            die("Connection failed: " . $this->conn->connect_error);
        }

        return $this->conn;
    }

    /**
     * Function to get the connection object
     */
    function getConnection() {
        return $this->conn;
    }

    /**
     * Function to close db connection
     */
    function close() {
        $this->conn->close();
    }
}
?>
