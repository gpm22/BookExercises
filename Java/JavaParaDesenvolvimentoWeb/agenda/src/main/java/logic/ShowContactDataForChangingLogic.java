package logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;

public class ShowContactDataForChangingLogic implements Logic {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("contato", new ContatoDAO().getContatoPorId(id));
        return "WEB-INF/jsp/change-contact.jsp";
    }
    
}
