package controllers.user;

import common.exceptions.MyException;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.UserService;
import services.mailer.Sender;

import javax.mail.MessagingException;
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
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user;
        try {
            if(password.length()>0 && login.length()>3 && (user=UserService.authorize(login,password)) != null) {
                logger.trace("find");
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60*60);
                if(user.getNickName().equals("admin")&&user.getFlagMail()==1){
                    Sender sender = new Sender();
                    sender.setTo(user.getEmail());
                    sender.send();
                }
                resp.sendRedirect("/welcomePage.jsp");
            }else{
                logger.trace("can not find");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (MyException e) {
            req.setAttribute("mess", "Sorry some problem with our system, try later)");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
