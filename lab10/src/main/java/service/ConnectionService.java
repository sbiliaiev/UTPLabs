package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnectionService {
    private static ConnectionService INSTANCE;
    private static Connection connection;

    final String host = "0.0.0.0";
    final String port = "55001";
    final String dbName = "UsersGroups";
    final String username = "postgres";
    final String password = "postgrespw";

    private ConnectionService() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(getUrl(), username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ConnectionService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionService();
        }

        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }


    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getUrl() {
        return String.format("jdbc:postgresql://%s:%s/%s?ssl=false", host, port, dbName);
    }
}
