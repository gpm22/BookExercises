package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(){
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(
          "jdbc:h2:tcp://localhost:9092/../WebJavaH2DataBase/data/mydb", "sa", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
