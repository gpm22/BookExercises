package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Logic;

@WebServlet("/mvc")
public class ControllerServelet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String parameter = request.getParameter("logic");
        String className = "logic."+parameter;

        try {
            Class _class = Class.forName(className);
            Logic logic = (Logic) _class.newInstance();
            String page = logic.execute(request, response);

            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e){
            throw new ServletException("The business logic caused an exception!", e);
        }
    }
}
