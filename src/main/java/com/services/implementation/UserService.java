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

    @Override
    public User getUser(int id) throws MyException {
        return userDao.getUserById(id);
    }

    @Override
    public User updateUser(User user) throws MyException {
        return userDao.updateUser(user);
    }


    public User registration(User user) throws MyException {
        User user1 = userDao.addUser(user);
        if(user1 != null){
            return user;
        }else {
            return null;
        }
    }

    public User getUserByName(String nickname)throws MyException  {
        return userDao.getUserByName(nickname);
    }
}
