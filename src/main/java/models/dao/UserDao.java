package models.dao;

import models.connectors.ConnectionDB;
import models.pojo.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
public class UserDao{
    public static User addUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO users " +
                "(firstname, lastname, email, nickname, password) " +
                "VALUES (\"" + user.getFirstName() + "\", \"" + user.getLastName() +
                "\", \"" + user.getEmail() + "\", \"" + user.getNickName() + "\", \"" +
                user.getPassword() + "\")");
        return getUserByLogAndPass(user.getNickName(), user.getPassword());
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
                preparedStatement.setString(4,user.getNickName());
                preparedStatement.setString(5,user.getPassword());
                preparedStatement.setInt(6, user.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return getUserById(user.getId());
    }

    public void deleteUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM user WHERE id = " + user.getId());
    }

    public static boolean isEmailThere(String email) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email = \"" +
                email + "\"");
        if(resultSet.next()){
            return false;
        }else {
            return true;
        }
    }

    public static boolean isNickThere(String nickName) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nickname = \"" +
                nickName + "\"");
        if(resultSet.next()){
            return false;
        }else {
            return true;
        }
    }

    public static User getUserByLogAndPass(String nickname, String password) throws SQLException {
        User user;
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nickname = \"" +
                nickname + "\" AND password = \"" + password + "\"");
        user = getUser(resultSet);
        return user;
    }

    public static User getUserById(int id) throws SQLException {
        User user = new User();
        Connection connection = ConnectionDB.getConnectionDB();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setNickName(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
        }
        return user;
    }

    public static ArrayList getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
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
            user.setArticles(ArticleDao.getArticleByUserId(user.getId()));
            user.setComments(CommentDao.getAllCommentsByUserId(user.getId()));
            users.add(user);
        }
        return users;
    }

    public static User getUser(ResultSet resultSet) throws SQLException {
        User user =  new User();
        if(resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setNickName(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setArticles(ArticleDao.getArticleByUserId(user.getId()));
            user.setComments(CommentDao.getAllCommentsByUserId(user.getId()));
        }
        return user;
    }
}
