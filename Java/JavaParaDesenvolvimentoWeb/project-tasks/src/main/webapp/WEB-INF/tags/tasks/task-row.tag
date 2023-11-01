<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="task" required="true" type="tasks.model.Task" %>
<tr id="task_${task.id}">
    <td>${task.description}</td>
    <td>
        <c:if test="${task.concluded eq false}">
            Not Concluded
        </c:if>
        <c:if test="${task.concluded eq true}">
            Concluded
        </c:if>
    </td>
    <td>
        <fmt:formatDate value="${task.conclusionDate.time}" pattern="dd/MM/yyyy HH:mm:ss"/>
    </td>
    <td>
        <a href="#" onclick="remove(${task.id})">Remove</a>
    </td>
    <c:if test="${task.concluded eq false}">
        <td>
            <a href="#" onclick="concludeNow(${task.id})">Conclude</a>
        </td>
    </c:if>

</tr>

<script type="text/javascript">
    function concludeNow(id) {
        $.post("concludeTask", {
            'id': id
        }, function (response) {
            alert("concluded!");
            $("#task_"+id).replaceWith(response);
        });
    }
    function remove(id) {
        $.post("removeTask", {
            'id': id
        }, function () {
            alert("removed!");
            $("#task_"+id).remove();
        });
    }
</script>