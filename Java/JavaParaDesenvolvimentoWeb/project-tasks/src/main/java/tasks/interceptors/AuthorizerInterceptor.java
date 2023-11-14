package tasks.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object controller) throws Exception {

        String uri = request.getRequestURI();
        if (uri.endsWith("loginForm") ||
                uri.endsWith("doLogin") ||
                uri.contains("resources")) {
            return true;
        }

        if (request.getSession()
                .getAttribute("loggedUser") != null) {
            return true;
        }

        response.sendRedirect("loginForm");
        return false;
    }
}
