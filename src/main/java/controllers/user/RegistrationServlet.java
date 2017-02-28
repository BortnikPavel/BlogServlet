package controllers.user;

import common.exceptions.MyException;
import common.validators.EmailValidator;
import common.validators.NickNameValidator;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Павел on 25.02.2017.
 */
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on get");
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        User user;
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");
        try {
            if(EmailValidator.validate(email)&&firstName!=null&&
                lastName!=null&& NickNameValidator.isValidNickName(nickName)&&password!=null) {
                user = new User(firstName, lastName, email, nickName, password);
                if (UserService.registration(user) != null) {
                    resp.sendRedirect("/login.jsp");
                } else {
                    req.getRequestDispatcher("/registration.jsp").forward(req,resp);
                }
            } else {
                req.getRequestDispatcher("/registration.jsp").forward(req,resp);
            }
        } catch (MyException e) {
            req.setAttribute("mess", "Sorry some problem with our system, try later)");
            req.getRequestDispatcher("topicsList.jsp").forward(req,resp);
        }
    }
}
