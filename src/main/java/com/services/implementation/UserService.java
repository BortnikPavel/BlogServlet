package com.services.implementation;

import com.common.exceptions.MyException;
import com.models.dao.UserDao;
import com.models.daoInterfaces.UserDaoInterface;
import com.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.services.interfaces.UserServiceInterface;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class UserService implements UserServiceInterface {
    private User user;

    private UserDaoInterface userDao;

    @Autowired
    public void setUserDao(UserDaoInterface userDao) {
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
        user = userDao.addUser(user);
        if(user != null){
            return user;
        }else {
            return null;
        }
    }
}
