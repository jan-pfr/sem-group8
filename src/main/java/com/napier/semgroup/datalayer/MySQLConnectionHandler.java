package com.napier.semgroup.datalayer;

import java.sql.*;


public class MySQLConnectionHandler implements DatabaseConnection {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    @Override
    public void connect(String location) {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        int timer = 1;
        for (int i = 0; i < retries; ++i)
        {
            if (i >= 7){
                timer = 5000;
            }
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(timer);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
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
                System.out.println("Connection closed.");
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
                // Execute SQL statement and return result
                return stmt.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
                return null;
            }
        }

    }

}
