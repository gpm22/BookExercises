package logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;
import models.Contato;

public class RemoveContactLogic implements Logic {
   
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = Long.parseLong(request.getParameter("id"));

        Contato contato = new Contato();
        contato.setId(id);

        ContatoDAO dao = new ContatoDAO();
        dao.remove(contato);

        System.out.println("Removing contact ...");

        return "contacts-list-3.jsp";
    }
}
