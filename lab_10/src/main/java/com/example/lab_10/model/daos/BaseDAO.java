package com.example.lab_10.model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    public Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        String user = "root";
        String pass = "1234";
        String url = "jdbc:mysql://34.74.105.244:3306/lab7";

        return DriverManager.getConnection(url, user, pass);
    }
}
