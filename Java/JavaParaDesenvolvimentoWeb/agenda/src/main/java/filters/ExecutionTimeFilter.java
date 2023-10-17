package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class ExecutionTimeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long initialTime = System.currentTimeMillis();

        chain.doFilter(request, response);

        long finalTime = System.currentTimeMillis();

        String uri = ((HttpServletRequest)request).getRequestURI();
        String parameters = ((HttpServletRequest)request).getParameter("logic");

        System.out.println(String.format("Execution time of the requisition from %s?logica=%s took %d (ms)", uri, parameters, (finalTime - initialTime)));
    }
    
}
