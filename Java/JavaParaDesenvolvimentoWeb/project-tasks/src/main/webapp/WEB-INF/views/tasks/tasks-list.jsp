<%@ page import="daos.*, models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<display:table name="tasks" id="task">
    <display:column sortable="true" property="description" title="description"/>
    <display:column sortable="true" title="concluded?">
        <c:if test="${task.concluded eq false}">
            Not Concluded
        </c:if>
        <c:if test="${task.concluded eq true}">
            Concluded
        </c:if>
    </display:column>
    <display:column
    sortable="true" property="conclusionDate.time" title="Conclusion Date" format="{0, date, dd/MM/yyyy}"/>
<!--
    <display:column paramProperty="id" title="" paramId="id" href="mvc?logic=RemoveContactLogic">
        remove
    </display:column>
    <display:column paramProperty="id" title="" paramId="id" href="mvc?logic=ShowContactDataForChangingLogic">
        change
    </display:column>
-->
</display:table>
<button type="button" onclick="location.href='newTask'">Create new task</button>
