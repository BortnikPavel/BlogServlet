package controllers.user;

import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Павел on 25.02.2017.
 */
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on get LoginServlet");
        String action = req.getParameter("action");
        if(action == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else if(action.equalsIgnoreCase("logout")){
            HttpSession session = req.getSession();
            session.removeAttribute("nickname");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if(password.length()>0 && login.length()>3 && UserService.authorize(login,password) != null) {
                logger.trace("find");
                HttpSession session = req.getSession();
                session.setAttribute("nickname", "avtoriz");
                session.setMaxInactiveInterval(60*60);
                Cookie userName = new Cookie("nickname", login);
                userName.setMaxAge(60*60);
                resp.addCookie(userName);
                resp.sendRedirect("/startPage.jsp");
            }else{
                logger.trace("can not find");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("mess", "Sorry some problem with our system, try later)");
            req.getRequestDispatcher("startPage.jsp").forward(req,resp);
        }
    }
}
