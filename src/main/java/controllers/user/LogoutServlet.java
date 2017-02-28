package controllers.user;

import common.exceptions.MyException;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Павел on 28.02.2017.
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        if(session != null&&user!=null&&user.getFlagMail()==0){
            user.setFlagMail(1);
            try {
                UserService.updateFlag(user);
            } catch (MyException e) {

            }
            session.invalidate();
        }else if(session != null&&user!=null&&user.getFlagMail()==1){
            user.setFlagMail(0);
            try {
                UserService.updateFlag(user);
            } catch (MyException e) {
                e.printStackTrace();
            }
            session.invalidate();
        }
        resp.sendRedirect("/login");
    }
}
