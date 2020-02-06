package com.napier.semgroup;

import java.sql.*;


public class MySQLConnectionHandler implements DatabaseConnection {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    @Override
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    @Override
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    @Override
    public ResultSet execute(String query) {
        {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(query);
                // Return new employee if valid.
                // Check one is returned
                return rset;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
                return null;
            }
        }

    }

}
