package logic;

import java.sql.Connection;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;
import models.Contato;

public class UpdateContactLogic implements Logic {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contato contato;
        try {
            contato = Contato.createContatoByRequest(request);
        } catch (ParseException e) {
            e.printStackTrace();
            return "../erro.html";
        }

        Connection connection = (Connection) request.getAttribute("connection");

        ContatoDAO dao = new ContatoDAO(connection);

        if (contato.getId() == null) {
            dao.adiciona(contato);
            System.out.println("Adding contact ...");
        } else {
            dao.altera(contato);
            System.out.println("Changing contact ...");
        }

        return new ContactsListLogic().execute(request, response);
    }

}
