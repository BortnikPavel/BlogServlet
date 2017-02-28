package common.validators;

import common.exceptions.MyException;
import models.dao.UserDao;

import java.sql.SQLException;

/**
 * Created by Павел on 25.02.2017.
 */
public class NickNameValidator {
    public static boolean isValidNickName(String nick) throws MyException {
        if(nick.length()>3&& UserDao.isNickThere(nick)){
            return true;
        }else{
            return false;
        }
    }
}
