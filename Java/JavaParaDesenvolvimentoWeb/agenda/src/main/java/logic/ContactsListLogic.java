package logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;

public class ContactsListLogic implements Logic {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("contatos", new ContatoDAO().getTodosOsContatos());
        return "WEB-INF/jsp/contacts-list-3.jsp";
    }

}