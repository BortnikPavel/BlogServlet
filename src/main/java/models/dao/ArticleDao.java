package models.dao;


import models.connectors.ConnectionDB;
import models.pojo.Article;
import models.pojo.Topic;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
public class ArticleDao {
    private static Logger logger = Logger.getLogger(ArticleDao.class);
    public static void addArticle(Article article) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("INSERT INTO articles " +
                    "(id, text,publicationdate, userId) " +
                    "VALUES (\"" + article.getId() + "\", \"" + article.getTextArticle() + "\", \"" + article.getDatePublication() +
                    "\"" + article.getUser().getId() + "\", \"" + article.getTopic().getId() +
                    "\")");
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }
    }

    public static void deleteArticle() throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE articles");
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static Article getArticleById(int id) throws SQLException {
        Article article = new Article();
        User user = new User();
        UserDao userDB = new UserDao();
        Topic topic = new Topic();
        TopicDao topicDB =new TopicDao();
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articles where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
                article.setTitle(resultSet.getString(2));
                article.setTextArticle(resultSet.getString(3));
                article.setDatePublication(resultSet.getString(4));
                user = userDB.getUserById(resultSet.getInt(5));
                if (user != null) {
                    article.setUser(user);
                }
                topic = topicDB.getTopicById(resultSet.getInt(6));
                if (topic != null) {
                    article.setTopic(topic);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
        return article;
    }

    public static ArrayList<Article> getArticleByUserId(int userId) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articles WHERE userId = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getArticles(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static ArrayList<Article> getArticleByTopicId(int topicId) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articles WHERE topicId = ?");
            preparedStatement.setInt(1, topicId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getArticles(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }


    public static ArrayList<Article> getAllArticle() throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articles");
            ResultSet resultSet = preparedStatement.executeQuery();
            return getArticles(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    private static ArrayList<Article> getArticles(ResultSet resultSet) throws SQLException {
        User user;
        Topic topic;
        ArrayList<Article> articles = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt(1));
                article.setTitle(resultSet.getString(2));
                article.setTextArticle(resultSet.getString(3));
                article.setDatePublication(resultSet.getString(4));
                user = UserDao.getUserById(resultSet.getInt(5));
                if (user != null) {
                    article.setUser(user);
                }
                topic = TopicDao.getTopicById(resultSet.getInt(6));
                if (topic != null) {
                    article.setTopic(topic);
                }
                articles.add(article);
            }
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
        return articles;
    }
}
