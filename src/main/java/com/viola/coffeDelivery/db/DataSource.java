package com.viola.coffeDelivery.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost/test?"
                + "user=minty&password=greatsqldb");

    }
}
