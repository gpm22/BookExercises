package logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;
import models.Contato;

public class RemoveContactLogic implements Logic {
   
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = Long.parseLong(request.getParameter("id"));

        Contato contato = new Contato();
        contato.setId(id);

        Connection connection = (Connection) request.getAttribute("connection");

        ContatoDAO dao = new ContatoDAO(connection);
        dao.remove(contato);

        System.out.println("Removing contact ...");

        return new ContactsListLogic().execute(request, response);
    }
}
