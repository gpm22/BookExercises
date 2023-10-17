package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContatoDAO;
import models.Contato;

@WebServlet("/addContact")
public class AddContactServlet extends HttpServlet {

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        Contato contato;
        try {
            contato = Contato.createContatoByRequest(request);
        } catch (ParseException e) {
            out.println("Erro de conversão da data");
            return; // para a execução do método
        }

        // salva o contato
        Connection connection = (Connection) request.getAttribute("connection");

        ContatoDAO dao = new ContatoDAO(connection);
        dao.adiciona(contato);

        // imprime o nome do contato que foi adicionado
        RequestDispatcher rd = request.getRequestDispatcher("/contact-added.jsp");
        rd.forward(request, response);
    }
}
