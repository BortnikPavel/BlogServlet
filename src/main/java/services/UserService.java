package services;

import models.dao.UserDao;
import models.pojo.User;

import java.sql.SQLException;

/**
 * Created by Павел on 25.02.2017.
 */
public class UserService {
    private static User user;
    public static User authorize(String log, String pass) throws SQLException {
        user = UserDao.getUserByLogAndPass(log,pass);
        if (user != null){
            return user;
        }else {
            return null;
        }
    }

    public static User registration(User user) throws SQLException {
        UserService.user = UserDao.addUser(user);
        if(UserService.user != null){
            return UserService.user;
        }else {
            return null;
        }
    }
}
