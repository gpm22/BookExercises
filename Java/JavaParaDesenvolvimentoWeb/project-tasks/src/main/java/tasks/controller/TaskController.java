package tasks.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tasks.dao.JdbcTaskDao;
import tasks.model.Task;

@Controller
public class TaskController {

    private final JdbcTaskDao dao;

    @Autowired
    public TaskController(JdbcTaskDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/newTask")
    public String getNewTaskForm() {
        return "tasks/task-form";
    }

    @RequestMapping("/addTask")
    public String addNewTask(@Valid Task task, BindingResult result) throws SQLException {
        if (result.hasFieldErrors("description"))
            return "tasks/task-form";

        dao.add(task);
        return "tasks/task-added";
    }

    @RequestMapping("/getTasks")
    public String getTasks(Model model) {
        List<Task> tasks = dao.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/tasks-list";
    }

    @ResponseBody
    @RequestMapping("/removeTask")
    public void remove(Long id) {
        dao.remove(id);
    }

    @RequestMapping("/concludeTask")
    public String conclude(Long id, Model model) {
        Task task = dao.getTaskById(id);
        task.setConcluded(true);
        task.setConclusionDate(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")));
        dao.update(task);
        model.addAttribute("task", task);
        return "tasks/task-concluded";
    }
}