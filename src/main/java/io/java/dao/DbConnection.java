package io.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String HOST = "localhost";
    private static final Integer PORT = 3306;
    private static final String DATABASE_NAME = "jdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnection()  {
        Connection connection;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DATABASE_NAME), USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
