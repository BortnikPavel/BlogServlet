package com.models.daoInterfaces;

import com.common.exceptions.MyException;
import com.models.pojo.User;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface UserDaoInterface {
    public User addUser(User user) throws MyException;
    public boolean updateFlag(User user) throws MyException;
    public User updateUser(User user) throws MyException;
    public boolean deleteUser(User user) throws MyException;
    public boolean isEmailThere(String email) throws MyException;
    public boolean isNickThere(String nickName) throws MyException;
    public User getUserByLogAndPass(String nickname, String password) throws MyException;
    public User getUserById(int id) throws MyException;
    public ArrayList getAllUsers() throws MyException;
}
