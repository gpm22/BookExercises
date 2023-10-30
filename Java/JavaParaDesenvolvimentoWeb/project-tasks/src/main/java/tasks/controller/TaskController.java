package tasks.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/getTasks")
    public String getTasks(Model model){

        JdbcTaskDao dao = new JdbcTaskDao();
        List<Task> tasks = dao.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/tasks-list";
    }

    @RequestMapping("/removeTask")
    public String remove(Task task){
        JdbcTaskDao dao = new JdbcTaskDao();
        dao.remove(task);
        return "redirect:getTasks";
    }

    @RequestMapping("/concludeTask")
    public String conclude(Task task){
        JdbcTaskDao dao = new JdbcTaskDao();
        Task oldTask = dao.getTaskById(task.getId());
        oldTask.setConcluded(true);
        oldTask.setConclusionDate(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")));
        dao.update(oldTask);
        return "redirect:getTasks";
    }
}