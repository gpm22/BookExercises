package tasks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tasks.model.Task;

@Repository
public class JdbcTaskDao {
    // a conexão com o banco de dados
    private Connection connection;

    @Autowired
    public JdbcTaskDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            if (task.getConclusionDate() != null) {
                stmt.setTimestamp(3, new Timestamp(
                        task.getConclusionDate().getTimeInMillis()));
            } else {
                stmt.setTimestamp(3, null);
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

    public Task getTaskById(Long id) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tasks where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Task task;
            if (rs.next()) {
                task = new Task(rs);
            } else {
                task = new Task();
            }

            rs.close();
            stmt.close();
            return task;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getTasks() {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tasks");

            return createTasksList(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Task> createTasksList(PreparedStatement stmt) throws SQLException {

        ResultSet rs = stmt.executeQuery();
        List<Task> tasks = new ArrayList<>();
        while (rs.next()) {

            // criando o objeto Task
            Task task = new Task(rs);

            // adicionando o objeto à lista
            tasks.add(task);
        }

        rs.close();
        stmt.close();
        return tasks;
    }

    public void remove(Task task) {
        this.remove(task.getId());
    }

    public void remove(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from tasks where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Task task) {
        String sql = "update tasks set description=?, concluded=?, conclusionDate=? where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, task.getDescription());
            stmt.setBoolean(2, task.getConcluded());

            if (task.getConclusionDate() != null) {
                stmt.setTimestamp(3, new Timestamp(task.getConclusionDate()
                        .getTimeInMillis()));
            } else {
                stmt.setTimestamp(3, null);
            }

            stmt.setLong(4, task.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
