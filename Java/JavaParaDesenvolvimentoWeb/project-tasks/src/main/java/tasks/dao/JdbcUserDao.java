package tasks.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tasks.model.User;
import tasks.factories.ConnectionFactory;

public class JdbcUserDao {

    private Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    public JdbcUserDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean doesUserExist(User user){
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from users where login = ? and password = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            boolean ans = rs.next();
            rs.close();
            stmt.close();
            return ans;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
