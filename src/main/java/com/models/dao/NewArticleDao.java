package com.models.dao;

import com.common.exceptions.MyException;
import com.models.connectors.ConnectionDB;
import com.models.daoInterfaces.NewArticleDaoInterface;
import com.models.daoInterfaces.TopicDaoInterface;
import com.models.daoInterfaces.UserDaoInterface;
import com.models.pojo.Article;
import com.models.pojo.NewArticle;
import com.models.pojo.Topic;
import com.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by admin on 12.03.2017.
 */
@Component
public class NewArticleDao implements NewArticleDaoInterface {
    private UserDaoInterface userDao;
    private TopicDaoInterface topicDao;
    private static Logger logger = Logger.getLogger(ArticleDao.class);

    @Autowired
    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setTopicDao(TopicDaoInterface topicDao) {
        this.topicDao = topicDao;
    }



    @Override
    public boolean addNewArticle(NewArticle article) throws MyException {
        try{
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO newarticles " +
                    "(title, text, userId, topicId) " +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getTextArticle());
            preparedStatement.setInt(3, article.getUser().getId());
            preparedStatement.setInt(4, article.getTopic().getId());
            if(preparedStatement.execute()){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return false;
    }

    @Override
    public ArrayList<NewArticle> getArticles() throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM newarticles");
            ResultSet resultSet = preparedStatement.executeQuery();
            return getArticles(resultSet);
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    private ArrayList<NewArticle> getArticles(ResultSet resultSet) throws MyException {
        User user;
        Topic topic;
        ArrayList<NewArticle> articles = new ArrayList<>();
        try {
            while (resultSet.next()) {
                NewArticle article = new NewArticle();
                article.setId(resultSet.getInt(1));
                article.setTitle(resultSet.getString(2));
                article.setTextArticle(resultSet.getString(3));
                user = userDao.getUserById(resultSet.getInt(4));
                if (user != null) {
                    article.setUser(user);
                }
                topic = topicDao.getTopicById(resultSet.getInt(5));
                if (topic != null) {
                    article.setTopic(topic);
                }
                articles.add(article);
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return articles;
    }
}
