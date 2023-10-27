package tasks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import tasks.factories.ConnectionFactory;
import tasks.model.Task;

public class JdbcTaskDao {
    // a conexão com o banco de dados
    private Connection connection;


    public JdbcTaskDao(Connection connection){
        this.connection = connection;
    }

    public JdbcTaskDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public Task add(Task task) throws SQLException {
        String sql = "insert into tasks " +
                "(description,concluded,conclusionDate)" +
                " values (?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // seta os valores

            stmt.setString(1, task.getDescription());
            stmt.setBoolean(2, task.getConcluded());
            if (task.getConclusionDate() != null){
                stmt.setDate(3, new Date(
                    task.getConclusionDate().getTimeInMillis()));
            }else {
                stmt.setDate(3, null);
            }
            // executa
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Falha ao criar usuário, nenhuma linha foi afetada.");

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            task.setId(generatedKeys.getLong(1));
            stmt.close();
            return task;
        } catch (SQLException e) {
            throw e;
        }
    }
}
