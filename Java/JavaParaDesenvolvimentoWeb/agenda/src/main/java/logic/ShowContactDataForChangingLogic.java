package logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;

public class ShowContactDataForChangingLogic implements Logic {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id")); 
        
        Connection connection = (Connection) request.getAttribute("connection");

        ContatoDAO dao = new ContatoDAO(connection);
        request.setAttribute("contato", dao.getContatoPorId(id));
        return "WEB-INF/jsp/change-contact.jsp";
    }
    
}
