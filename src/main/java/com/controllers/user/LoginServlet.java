package com.controllers.user;

import com.common.exceptions.MyException;
import com.models.pojo.User;
import com.services.interfaces.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.services.UserService;
import com.services.mailer.Sender;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserServiceInterface service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on get LoginServlet");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        ServletConfig config = getServletConfig();
        String[] strings = {config.getInitParameter("user"), config.getInitParameter("flag")};
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = service.authorize(login,password);
        } catch (MyException e) {
            e.printStackTrace();
        }
        try {
            if(password.length()>0 && login.length()>3 && user != null) {
                logger.trace("find");
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60*60);
                if(user.getNickName().equals(strings[0])&&strings[1].equals("send")){
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
