<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<c:import url="header.jsp" />
<html>
    <head>
        <link href="css/jquery-ui.min.css" rel="stylesheet" />
        <script src="js/jquery.js"></script>
        <script src="js/jquery-ui.min.js"></script>
    </head>
    <body>
        <h1>Adiciona Contatos</h1>
        <hr />
        <form action="addContact">
            Nome: <input type="text" name="nome" /><br />
            E-mail: <input type="text" name="email" /><br />
            Endereço: <input type="text" name="endereco" /><br />
            Data Nascimento:
            <custom:dateInput id="dataNascimento" /><br />
            <input type="submit" value="Gravar" />
        </form>
    </body>
</html>

<c:import url="footer.jsp" />
