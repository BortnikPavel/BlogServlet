package com.common.validators;

import com.common.exceptions.MyException;
import com.models.dao.UserDao;
import com.models.daoInterfaces.UserDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class EmailValidator {

    private static Pattern pattern;
    private static Matcher matcher;
    private UserDaoInterface userDao;

    @Autowired
    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }


    public boolean validate(final String hex) throws MyException {
        matcher = pattern.matcher(hex);
        boolean flag = matcher.matches();
        if(flag && userDao.isEmailThere(hex)){
            return true;
        }else {
            return false;
        }
    }

}