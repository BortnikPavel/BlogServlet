package models.dao;

import common.exceptions.MyException;
import models.connectors.ConnectionDB;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Павел on 25.02.2017.
 */
public class UserDao{
    private static Logger logger = Logger.getLogger(UserDao.class);
    private static final String SQL_ADD_USER = "INSERT INTO users " +
            "(firstname, lastname, email, nickname, password, flagmail) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static User addUser(User user) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getNickName());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setInt(6,user.getNickName().hashCode());
            return getUserByLogAndPass(user.getNickName(), user.getPassword());
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public static boolean updateFlag(User user) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET flagmail = ? " +
                    "where id = ?");
            preparedStatement.setInt(1, user.getFlagMail());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public static User updateUser(User user) throws MyException {
            try {
                Connection connection = ConnectionDB.getConnectionDB();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname = ?," +
                        " lastname = ?, email = ?, nickname = ?, password = ? " +
                        "where id = ?");
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getNickName());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setInt(6, user.getId());
                preparedStatement.executeUpdate();
                return getUserById(user.getId());
            }catch (SQLException e){
                logger.error(e);
                throw new MyException("Sorry, we have some problem with our system!");
            }
    }

    public boolean deleteUser(User user) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setInt(1,user.getId());
            if(preparedStatement.execute()){
                return true;
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return false;
    }

    public static boolean isEmailThere(String email) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public static boolean isNickThere(String nickName) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1,nickName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }

    }

    public static User getUserByLogAndPass(String nickname, String password) throws MyException {
        User user;
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE nickname = ? AND password = ?" );
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUser(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return user;
    }

    public static User getUserById(int id) throws MyException {
        User user = new User();
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUser(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return user;
    }

    public static ArrayList getAllUsers() throws MyException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setNickName(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setFlagMail(resultSet.getInt(7));
                user.setArticles(ArticleDao.getArticleByUserId(user.getId()));
                user.setComments(CommentDao.getAllCommentsByUserId(user.getId()));
                users.add(user);
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return users;
    }

    public static User getUser(ResultSet resultSet) throws MyException {
        User user =  new User();
        try {
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setNickName(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setFlagMail(resultSet.getInt(7));
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return user;
    }
}
