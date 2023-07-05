package com.gpm22.WebJavaH2DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
          "jdbc:h2:file:./data/mydb", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
