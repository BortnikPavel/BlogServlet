package models.dao;


import com.mysql.jdbc.ConnectionFeatureNotAvailableException;
import models.connectors.ConnectionDB;
import models.pojo.Article;
import models.pojo.Comment;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
public class CommentDao {
    private static Logger logger = Logger.getLogger(CommentDao.class);

    public static boolean addComment(Comment comment) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
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
            throw new SQLException();
        }
    }

    public static Comment getCommentById(int id) throws SQLException {
        User user;
        UserDao userDao = new UserDao();
        Article article;
        ArticleDao articleDB = new ArticleDao();
        Comment comment = new Comment();
        Connection connection = ConnectionDB.getConnectionDB();
        try {
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
            throw new SQLException();
        }
        return comment;
    }

    public static ArrayList<Comment> getAllComments() throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comments");
            ResultSet resultSet = preparedStatement.executeQuery();
            return getComments(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }
    }

    public static ArrayList<Comment> getAllCommentsByUserId(int id) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE userId = " + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getComments(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }
    }

    private static ArrayList<Comment> getComments(ResultSet resultSet) throws SQLException {
        User user = new User();
        UserDao userDB = new UserDao();
        Article article = new Article();
        ArticleDao articleDB = new ArticleDao();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try {
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(1));
                comment.setText(resultSet.getString(2));
                comment.setDate(resultSet.getString(3));
                user = userDB.getUserById(comment.getId());
                comment.setUser(user);
                article = articleDB.getArticleById(comment.getId());
                comment.setArticle(article);
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }
        return comments;
    }

    public static void deleteComment() throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE comments");
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }
    }
}

