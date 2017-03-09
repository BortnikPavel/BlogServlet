package com.models.dao;


import com.common.exceptions.MyException;
import com.models.connectors.ConnectionDB;
import com.models.daoInterfaces.CommentDaoInterface;
import com.models.pojo.Article;
import com.models.pojo.Comment;
import com.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class CommentDao implements CommentDaoInterface {
    private static Logger logger = Logger.getLogger(CommentDao.class);
    ArticleDao articleDB;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setArticleDB(ArticleDao articleDB) {
        this.articleDB = articleDB;
    }

    public boolean addComment(Comment comment) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comments " +
                    "(text, date, userId, articleId) " +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setString(2, comment.getDate());
            preparedStatement.setInt(3, comment.getUser().getId());
            preparedStatement.setInt(4, comment.getArticle().getId());
            if (preparedStatement.execute()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public Comment getCommentById(int id) throws MyException {
        User user;
        UserDao userDao = new UserDao();
        Article article;
        ArticleDao articleDB = new ArticleDao();
        Comment comment = new Comment();
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comments where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
                comment.setText(resultSet.getString(2));
                comment.setDate(resultSet.getString(3));
                user = userDao.getUserById(comment.getId());
                comment.setUser(user);
                article = articleDB.getArticleById(comment.getId());
                comment.setArticle(article);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return comment;
    }

    public ArrayList<Comment> getAllComments() throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comments");
            ResultSet resultSet = preparedStatement.executeQuery();
            return getComments(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public ArrayList<Comment> getAllCommentsByUserId(int id) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE userId = " + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getComments(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public ArrayList<Comment> getAllCommentsByArticleId(int id) throws MyException {
        try {
            ArrayList<Comment> comments = new ArrayList<Comment>();
            User user;
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM comments WHERE articleId = " + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getComments(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    private ArrayList<Comment> getComments(ResultSet resultSet) throws MyException {
        User user;
        Article article;
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try {
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(1));
                comment.setText(resultSet.getString(2));
                comment.setDate(resultSet.getString(3));
                user = userDao.getUserById(comment.getId());
                comment.setUser(user);
                article = articleDB.getArticleById(comment.getId());
                comment.setArticle(article);
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return comments;
    }

    public boolean deleteComment(Comment comment) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE id = ?");
            preparedStatement.setInt(1, comment.getId());
            if(preparedStatement.execute()){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return false;
    }
}

