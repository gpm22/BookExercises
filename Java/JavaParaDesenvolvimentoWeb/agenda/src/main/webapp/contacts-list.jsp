<%@ page import="java.util.*,
java.text.SimpleDateFormat,
 daos.*,
 models.*" %>

<html>
    <body>
        <table>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Endereço</th>
                <th>Data de Nascimento</th>
            </tr>
            <%
      ContatoDAO dao = new ContatoDAO();
      List<Contato> contatos = dao.getTodosOsContatos();

      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

      for (Contato contato : contatos ) {
    %>
                <tr>
                    <td><%=contato.getNome() %></td>
                    <td><%=contato.getEmail() %></td>
                    <td><%=contato.getEndereco() %></td>
                    <td><%=formatter.format(contato.getDataNascimento().getTime()) %></td>
                </tr>
            <%
      }
    %>
        </table>
    </body>
</html>
