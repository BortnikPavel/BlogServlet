package common.validators;

import models.dao.UserDao;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Павел on 25.02.2017.
 */
public class EmailValidator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }


    public static boolean validate(final String hex) throws SQLException {
        matcher = pattern.matcher(hex);
        boolean flag = matcher.matches();
        if(flag&& UserDao.isEmailThere(hex)){
            return true;
        }else {
            return false;
        }
    }

}