package com.controllers.user;

import com.common.exceptions.MyException;
import com.common.validators.EmailValidator;
import com.common.validators.NickNameValidator;
import com.models.pojo.User;
import com.services.interfaces.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Павел on 25.02.2017.
 */
public class RegistrationServlet extends HttpServlet {
    private UserServiceInterface service;
    private EmailValidator emailValidator;
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Autowired
    public void setEmailValidator(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Autowired
    public void setService(UserServiceInterface service) {
        this.service = service;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

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
            if(emailValidator.validate(email)&&firstName!=null&&
                lastName!=null&& new NickNameValidator().isValidNickName(nickName)&&password!=null) {
                user = new User(firstName, lastName, email, nickName, password);
                if (service.registration(user) != null) {
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
