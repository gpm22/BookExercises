package logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstLogic implements Logic {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("Executing the logic ...");
        System.out.println("returning the name of the jsp page");
        return "first-logic.jsp";
    }
    
}
