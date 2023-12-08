package tasks.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tasks.model.User;

@Repository
public class JdbcUserDao {

    private Connection connection;

    @Autowired
    public JdbcUserDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doesUserExist(User user) {
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
