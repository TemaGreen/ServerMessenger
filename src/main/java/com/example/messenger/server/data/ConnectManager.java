package com.example.messenger.server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {

    private final static String url = "jdbc:postgresql://localhost:5432/messengerDB";
    private final static String user = "postgres";
    private final static String pass = "1313";

    public static Connection getConnect() throws SQLException {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e){
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path");
            e.printStackTrace();
        }
        return connection;
    }
}
