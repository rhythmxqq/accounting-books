package com.example.bookregister2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getDBConnection(){

        String databaseName = "test2";
        String databaseUser = "root";
        String databasePassword = "123qweasd";
        String url = "jdbc:mysql://localhost/" + databaseName;


        try {
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseLink;

    }


}
