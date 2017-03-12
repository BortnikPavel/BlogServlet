package com.common.validators;

import com.common.exceptions.MyException;
import com.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class NickNameValidator {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public boolean isValidNickName(String nick) throws MyException {
        if(nick.length() > 3 && userDao.isNickThere(nick)){
            return true;
        }else{
            return false;
        }
    }
}
