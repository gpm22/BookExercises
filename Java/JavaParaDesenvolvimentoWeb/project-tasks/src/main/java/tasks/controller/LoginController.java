package tasks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tasks.dao.JdbcUserDao;
import tasks.model.User;

@Controller
public class LoginController {

    @RequestMapping("/loginForm")
    public String loginForm() {
        return "form-login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user, HttpSession session) {
        if (new JdbcUserDao().doesUserExist(user)) {
            session.setAttribute("loggedUser", user);
            return "menu";
        }
        return "redirect:loginForm";
    }
}