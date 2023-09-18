package logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowContactDataForAddingLogic implements Logic{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/jsp/add-contact.jsp";
    }
}

