<%@ page import="daos.*, models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="../../header.jsp"/>
<display:table name="contatos" id="contato">
    <display:column sortable="true" property="nome" title="Nome"/>
    <display:column sortable="true" property="email" title="Email"/>
    <display:column sortable="true" property="endereco" title="Endereço"/>
    <display:column sortable="true" property="dataNascimento.time" title="Data de Nascimento" format="{0, date, dd/MM/yyyy}"/>
    <display:column paramProperty="id" title="" paramId="id" href="mvc?logic=RemoveContactLogic">
        remove
    </display:column>
    <display:column paramProperty="id" title="" paramId="id" href="mvc?logic=ShowContactDataForChangingLogic">
        change
    </display:column>
</display:table>
<button type="button" onclick="location.href='mvc?logic=ShowContactDataForAddingLogic'">Create new user</button>
<c:import url="../../footer.jsp"/>
