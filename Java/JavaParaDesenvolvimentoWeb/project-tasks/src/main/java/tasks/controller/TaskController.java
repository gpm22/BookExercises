package tasks.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
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
    public String addNewTask(Task task) throws SQLException {
        JdbcTaskDao dao = new JdbcTaskDao();
        dao.add(task);
        return "tasks/task-added";
    }
}