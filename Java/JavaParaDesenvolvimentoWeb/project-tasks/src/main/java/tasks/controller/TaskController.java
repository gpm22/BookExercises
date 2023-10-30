package tasks.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import tasks.dao.JdbcTaskDao;
import tasks.model.Task;

@Controller
public class TaskController {

    @RequestMapping("/newTask")
    public String getNewTaskForm() {
        return "tasks/task-form";
    }

    @RequestMapping("/addTask")
    public String addNewTask(@Valid Task task, BindingResult result) throws SQLException {

        if(result.hasFieldErrors("description"))
            return "tasks/task-form";

        JdbcTaskDao dao = new JdbcTaskDao();
        dao.add(task);
        return "tasks/task-added";
    }
}