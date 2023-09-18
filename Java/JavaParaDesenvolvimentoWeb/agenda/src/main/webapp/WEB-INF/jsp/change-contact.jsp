<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<c:import url="../../header.jsp"/>
<fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy" type="date" var="contactFormattedDate"/>
<html>
    <head>
        <link href="css/jquery-ui.min.css" rel="stylesheet"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery-ui.min.js"></script>
    </head>
    <body>
        <h1>Mudar Contato</h1>
        <hr/>
        <h2>Dados Atuais</h2>
        <p>
            Nome: ${contato.nome}</p>
        <p>
            E-mail: ${contato.email}</p>
        <p>
            Endereço: ${contato.endereco}</p>
        <p>
            Data Nascimento: ${contactFormattedDate}</p>

        <hr/>
        <h2>Novos Dados</h2>
        <form action="mvc">
            <input type="hidden" name="logic" value="UpdateContactLogic"/>
            <input type="hidden" name="id" value="${contato.id}"/>
            Nome:
            <input type="text" name="nome" value="${contato.nome}"/><br/>
            E-mail:
            <input type="text" name="email" value="${contato.email}"/><br/>
            Endereço:
            <input type="text" name="endereco" / value="${contato.endereco}"><br/>
                Data Nascimento:
                <custom:dateInput id="dataNascimento" value="${contactFormattedDate}"/><br/>
                <input type="submit" value="Gravar"/>
            </form>
        </body>
    </html>

    <c:import url="../../footer.jsp"/>
</html>
