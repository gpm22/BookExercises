<%@ page import="daos.*, models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
    <head>
        <script type="text/javascript" src="resources/js/jquery-3.7.1.js"></script>
    </head>
    <body>
        <display:table name="tasks" id="task">
            <display:column sortable="true" property="description" title="description"/>
            <display:column sortable="true" title="concluded?">
                <p id="task_${task.id}">
                    <c:if test="${task.concluded eq false}">
                        Not Concluded
                    </c:if>
                    <c:if test="${task.concluded eq true}">
                        Concluded
                    </c:if>
                </p>
            </display:column>
            <display:column sortable="true" property="conclusionDate.time" title="Conclusion Date" format="{0, date, dd/MM/yyyy HH:mm:ss}"/>
            <display:column>
               <a href="#" onClick="remove(${task.id})">Remove</a>
            </display:column>
            <display:column>
               <a href="#" onClick="concludeNow(${task.id})">Conclude</a>
            </display:column>
        </display:table>
        <button type="button" onclick="location.href='newTask'">Create new task</button>
        <script type="text/javascript">
            function concludeNow(id) {
                $.post("concludeTask", {
                    'id': id
                }, function () {
                    alert("concluded!");
                    location.reload();
                });
            }
            function remove(id) {
                $.post("removeTask", {
                    'id': id
                }, function () {
                    alert("removed!");
                    location.reload();
                });
            }
        </script>
    </body>
</html>
