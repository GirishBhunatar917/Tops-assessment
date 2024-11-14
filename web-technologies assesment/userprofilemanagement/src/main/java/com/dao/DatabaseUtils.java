package com.dao;

import java.sql.*;

public class DatabaseUtils {

    // Load the JDBC driver for MySQL
    static {
        try {
            // Load MySQL JDBC driver (it must be in the classpath)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/user_profile";  // URL of your database
        String user = "root";  // Your database username
        String password = "";  // Your database password (use appropriate password)

        // Establish and return the connection
        return DriverManager.getConnection(url, user, password);
    }
}
