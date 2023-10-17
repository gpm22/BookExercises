package logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;

public class ContactsListLogic implements Logic {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection connection = (Connection) request.getAttribute("connection");

        ContatoDAO dao = new ContatoDAO(connection);
        request.setAttribute("contatos", dao.getTodosOsContatos());
        return "WEB-INF/jsp/contacts-list-3.jsp";
    }

}