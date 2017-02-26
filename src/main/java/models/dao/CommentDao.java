package models.dao;


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
    public static void addComment(Comment comment) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO comments " +
                    "(id, text, date, userId, articleId) " +
                    "VALUES (\"" + comment.getId() + "\", \"" + comment.getText() + "\", \"" + comment.getDate() +
                    "\", \"" + comment.getUser().getId() + "\", \"" + comment.getArticle().getId() + "\")");
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static Comment getCommentById(int id) throws SQLException {
        User user = new User();
        UserDao userDao = new UserDao();
        Article article = new Article();
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
                user = (User) userDao.getUserById(comment.getId());
                comment.setUser(user);
                article = (Article) articleDB.getArticleById(comment.getId());
                comment.setArticle(article);
            }
        }catch (SQLException e){
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
        }catch (SQLException e){
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
        }catch (SQLException e){
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
                user = (User) userDB.getUserById(comment.getId());
                comment.setUser(user);
                article = (Article) articleDB.getArticleById(comment.getId());
                comment.setArticle(article);
                comments.add(comment);
            }
        }catch (SQLException e){
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
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }
}
