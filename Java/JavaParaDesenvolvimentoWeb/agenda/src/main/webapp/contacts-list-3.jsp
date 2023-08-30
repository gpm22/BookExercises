<%@ page import="daos.*, models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<% request.setAttribute("contatos", new ContatoDAO().getTodosOsContatos()); %>
<c:import url="header.jsp" />
<display:table name="contatos">
    <display:column sortable="true" property="nome" title = "Nome" />
    <display:column sortable="true" property="email" title = "Email" />
    <display:column sortable="true" property="endereco" title = "Endereço" />
    <display:column sortable="true" property="dataNascimento.time" title = "Data de Nascimento" format="{0, date, dd/MM/yyyy}" />
</display:table>
<c:import url="footer.jsp" />
