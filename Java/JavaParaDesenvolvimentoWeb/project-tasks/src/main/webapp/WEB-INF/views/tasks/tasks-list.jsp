<%@ page import="daos.*, models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/tasks" prefix="custom" %>
<html>
    <head>
        <script type="text/javascript" src="resources/js/jquery-3.7.1.js"></script>
    </head>
    <body>
        <table>
            <tr>
                <th>description</th>
                <th>concluded?</th>
                <th>Conclusion Date</th>
            </tr>
            <c:forEach items="${tasks}" var="task">
                <custom:task-row task="${task}"></custom:task-row> 
            </c:forEach>
        </table>
        <button type="button" onclick="location.href='newTask'">Create new task</button>

    </body>
</html>
