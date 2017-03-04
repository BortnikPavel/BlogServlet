package com.controllers.user;

import com.common.exceptions.MyException;
import com.models.pojo.User;
import com.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import com.services.UserService;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Павел on 28.02.2017.
 */
public class LogoutServlet extends HttpServlet {
    private UserServiceInterface service;

    @Autowired
    public void setService(UserService service) {
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
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        if(session != null&&user!=null&&user.getFlagMail()==0){
            user.setFlagMail(1);
            try {
                service.updateFlag(user);
            } catch (MyException e) {

            }
            session.invalidate();
        }else if(session != null&&user!=null&&user.getFlagMail()==1){
            user.setFlagMail(0);
            try {
                service.updateFlag(user);
            } catch (MyException e) {
                e.printStackTrace();
            }
            session.invalidate();
        }
        resp.sendRedirect("/login");
    }
}
