<html>
    <body>
        <%-- comentário em JSP aqui: nossa primeira página JSP --%>

        <%
        String mensagem = "Bem vindo ao sistema de agenda!";
      %>
            <% out.println(mensagem); %>

            <br/>

            <%
        String desenvolvido = "Desenvolvido por gpm22";
      %>
            <%= desenvolvido %>

            <br/>

            <%
        System.out.println("Tudo foi executado!");
      %></body>
    </body>
</html>
