package com.services;

import com.common.exceptions.MyException;
import com.models.dao.UserDao;
import com.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.services.interfaces.UserServiceInterface;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class UserService implements UserServiceInterface {
    private static User user;

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authorize(String log, String pass) throws MyException {
        user = userDao.getUserByLogAndPass(log,pass);
        if (user != null){
            return user;
        }else {
            return null;
        }
    }


    public boolean updateFlag(User user) throws MyException {
        return userDao.updateFlag(user);
    }


    public User registration(User user) throws MyException {
        UserService.user = userDao.addUser(user);
        if(UserService.user != null){
            return UserService.user;
        }else {
            return null;
        }
    }
}
