package com.services.interfaces;

import com.common.exceptions.MyException;
import com.models.pojo.User;

/**
 * Created by admin on 01.03.2017.
 */
public interface UserServiceInterface {
    public User authorize(String log, String pass) throws MyException;
    public User registration(User user) throws MyException;
    public boolean updateFlag(User user) throws MyException;
}
