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
            return "redirect:menu";
        }
        return "redirect:loginForm";
    }

    @RequestMapping("/menu")
    public String getMenu(){
        return "menu";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginForm";
    }
}