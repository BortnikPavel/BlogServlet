package models.dao;

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
    public static User addUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("");
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getNickName());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setInt(6,user.getNickName().hashCode());
            return getUserByLogAndPass(user.getNickName(), user.getPassword());
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static boolean updateFlag(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET flagmail = ? " +
                    "where id = ?");
            preparedStatement.setInt(1, user.getFlagMail());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static User updateUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
            try {
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
                throw new SQLException();
            }
    }

    public void deleteUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setInt(1,user.getId());
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static boolean isEmailThere(String email) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
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
            throw new SQLException();
        }
    }

    public static boolean isNickThere(String nickName) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
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
            throw new SQLException();
        }

    }

    public static User getUserByLogAndPass(String nickname, String password) throws SQLException {
        User user;
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE nickname = ? AND password = ?" );
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUser(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
        return user;
    }

    public static User getUserById(int id) throws SQLException {
        User user = new User();
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUser(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
        return user;
    }

    public static ArrayList getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        Connection connection = ConnectionDB.getConnectionDB();
        try {
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
            throw new SQLException();
        }
        return users;
    }

    public static User getUser(ResultSet resultSet) throws SQLException {
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
            throw new SQLException();
        }
        return user;
    }
}
