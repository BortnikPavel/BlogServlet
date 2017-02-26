package controllers.Filtres;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Павел on 26.02.2017.
 */
@WebFilter(filterName = "MyFilter")
public class MyFilter implements Filter {
    private static Logger logger = Logger.getLogger(MyFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException{
        logger.trace("Filter work");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        if(session.getAttribute("nickname")==null){
            request.getRequestDispatcher("login.jsp").forward(req, resp);
        }else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
