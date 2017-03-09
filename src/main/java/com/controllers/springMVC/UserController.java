package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.common.validators.EmailValidator;
import com.common.validators.NickNameValidator;
import com.models.pojo.User;
import com.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 07.03.2017.
 */
@Controller
public class UserController {
    private UserServiceInterface service;
    private EmailValidator emailValidator;

    @Autowired
    public void setEmailValidator(EmailValidator emailValidator){
        this.emailValidator = emailValidator;
    }

    @Autowired
    public void setService(UserServiceInterface service) {
        this.service = service;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(ModelAndView modelAndView,
                              HttpSession session,
                              @RequestParam(name = "login") String login,
                              @RequestParam(name = "password") String password){
        try {
            User user = service.authorize(login,password);
            if(password.length()>0 && login.length()>3 && user != null) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60*60);
                modelAndView.setViewName("/welcomePage");
                return modelAndView;
            }else{
                //logger.trace("can not find");
                modelAndView.setViewName("/login");
                return modelAndView;
            }
        } catch (MyException e) {
            e.printStackTrace();
            modelAndView.setViewName("/login");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "welcomePage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("nickName") String nickName,
                               @RequestParam("password") String password){
        User user;
        try {
            if(emailValidator.validate(email)&&firstName!=null&&
                    lastName!=null&& new NickNameValidator().isValidNickName(nickName)&&password!=null) {
                user = new User(firstName, lastName, email, nickName, password);
                if (service.registration(user) != null) {
                    return "login";
                } else {
                    return "registration";
                }
            } else {
                return "registration";
            }
        } catch (MyException e) {
            return "registration";
        }
    }
}
