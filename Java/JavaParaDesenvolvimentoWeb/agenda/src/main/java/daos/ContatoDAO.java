package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factories.ConnectionFactory;
import exceptions.DAOException;
import models.Contato;

public class ContatoDAO {

    // a conexão com o banco de dados
    private Connection connection;


    public ContatoDAO(Connection connection){
        this.connection = connection;
    }

    public ContatoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public Contato adiciona(Contato contato) {
        String sql = "insert into contatos " +
                "(nome,email,endereco,dataNascimento)" +
                " values (?,?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // seta os valores

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date(
                    contato.getDataNascimento().getTimeInMillis()));

            // executa
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
                throw new DAOException("Falha ao criar usuário, nenhuma linha foi afetada.");

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            contato.setId(generatedKeys.getLong(1));
            stmt.close();
            return contato;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Contato> getTodosOsContatos() {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from contatos");

            return criarListaDeContatos(stmt);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Contato getContatoPorId(Long id) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from contatos where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Contato contato;
            if (rs.next()) {
                contato = new Contato(rs);
            } else {
                contato = new Contato();
            }

            rs.close();
            stmt.close();
            return contato;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Contato> getContatosPorNome(String nome) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from contatos where nome = ?");
            stmt.setString(1, nome);
            return criarListaDeContatos(stmt);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private List<Contato> criarListaDeContatos(PreparedStatement stmt) throws SQLException {

        ResultSet rs = stmt.executeQuery();
        List<Contato> contatos = new ArrayList<>();
        while (rs.next()) {

            // criando o objeto Contato
            Contato contato = new Contato(rs);

            // adicionando o objeto à lista
            contatos.add(contato);
        }

        rs.close();
        stmt.close();
        return contatos;
    }

    public void altera(Contato contato) {
        String sql = "update contatos set nome=?, email=?, endereco=?," +
                "dataNascimento=? where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento()
                    .getTimeInMillis()));
            stmt.setLong(5, contato.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void remove(Contato contato) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from contatos where id=?");
            stmt.setLong(1, contato.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}