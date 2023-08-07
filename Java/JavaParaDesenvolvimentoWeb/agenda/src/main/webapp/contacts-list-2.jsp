<%@ page import="java.util.*,
                 java.text.SimpleDateFormat,
                 daos.*,
                 models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="header.jsp"/>
<html>
    <body>
        <table>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Endereço</th>
                <th>Data de Nascimento</th>
            </tr>
            <jsp:useBean id="dao" class="daos.ContatoDAO"/>
            <c:forEach var="contato" items="${dao.todosOsContatos}" varStatus="id">
                <tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }" ">
                    <td>${contato.nome}</td>
                    <td><c:choose>
                        <c:when test="${not empty contato.email}">
                            <a href="mailto:${contato.email}">${contato.email}</a>
                        </c:when>
                        <c:otherwise>
                            E-mail não informado
                        </c:otherwise>
                    </c:choose></td>
                    <td>${contato.endereco}</td>
                    <td><fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd-MM-yyyy"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

<c:import url="footer.jsp"/>