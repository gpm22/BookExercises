<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <link type="text/css" href="resources/css/tasks.css" rel="stylesheet" />
    </head>
    <body>
        <h3>Adicionar tarefas</h3>
        <form:errors path="task.description"/>
        <form action="addTask" method="post">
            Descrição:
            <br/>
            <textarea name="description" rows="5" cols="100"></textarea><br/>
            <input type="submit" value="Add"></form>
        </body>
    </html>
</html>
