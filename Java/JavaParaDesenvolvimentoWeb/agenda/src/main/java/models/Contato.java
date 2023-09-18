package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Contato {

    private Long id;
    private String nome;
    private String email;
    private String endereco;
    private Calendar dataNascimento;

    // métodos get e set para id, nome, email, endereço e dataNascimento

    public Contato() {
    }

    public Contato(ResultSet rs) throws SQLException {
        id = rs.getLong("id");
        nome = rs.getString("nome");
        email = rs.getString("email");
        endereco = rs.getString("endereco");

        // montando a data através do Calendar
        Calendar data = Calendar.getInstance();
        data.setTime(rs.getDate("dataNascimento"));
        dataNascimento = data;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novo) {
        this.nome = novo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String novo) {
        this.email = novo;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String novo) {
        this.endereco = novo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long novo) {
        this.id = novo;
    }

    public Calendar getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String data = dataNascimento != null ? formatter.format(dataNascimento.getTime()) : "null";
        return "Contato [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco
                + ", dataNascimento=" + data + "]";
    }

    public static Contato createContatoByRequest(HttpServletRequest request) throws ParseException {
        String idStr = request.getParameter("id");
        Long id = idStr != null ? Long.parseLong(idStr) : null;

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String dataEmTexto = request.getParameter("dataNascimento");
        Calendar dataNascimento = null;

        Date date = new SimpleDateFormat("dd/MM/yyyy")
                .parse(dataEmTexto);
        dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(date);

        Contato contato = new Contato();
        contato.setId(id);
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setDataNascimento(dataNascimento);
        return contato;
    }
}
