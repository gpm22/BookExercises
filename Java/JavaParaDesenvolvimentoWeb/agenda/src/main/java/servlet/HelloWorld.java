package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {
  private int contador = 0; // variavel de instância

  @Override
  protected void service(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    contador++; // a cada requisição a mesma variável é incrementada
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Primeira Servlet</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Oi mundo Servlet 1!</h1>");
    out.println("Contador agora é: " + contador);
    out.println("</body>");
    out.println("</html>");
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    log("Iniciando a servlet");
  }

  public void destroy() {
    super.destroy();
    log("Destruindo a servlet");
  }
}
