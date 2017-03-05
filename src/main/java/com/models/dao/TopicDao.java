package com.models.dao;


import com.common.exceptions.MyException;
import com.models.connectors.ConnectionDB;
import com.models.daoInterfaces.TopicDaoInterface;
import com.models.pojo.Topic;
import com.services.interfaces.TopicServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
@Component
public class TopicDao implements TopicDaoInterface{
    private static Logger logger = Logger.getLogger(TopicDao.class);

    public Topic addTopic(Topic topic) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO topics (name) VALUES (?)");
            preparedStatement.setString(1,topic.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            return getTopicByName(topic.getName());
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public Topic getTopicByName(String name) throws MyException {
        Topic topic = new Topic();
        try{
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM topics WHERE name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                topic.setId(resultSet.getInt(1));
                topic.setName(resultSet.getString(2));
                return topic;
            }else {
                return null;
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public void deleteTopic(Topic topic) throws MyException {
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM topics WHERE id = ?");
            preparedStatement.setInt(1,topic.getId());
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    public Topic getTopicById(int id) throws MyException {
        Topic topic = new Topic();
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM topics where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            topic.setId(resultSet.getInt(1));
            topic.setName(resultSet.getString(2));
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return topic;
    }

    public ArrayList<Topic> getAllTopics() throws MyException {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        try {
            Connection connection = ConnectionDB.getConnectionDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM topics");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setId(resultSet.getInt(1));
                topic.setName(resultSet.getString(2));
                topics.add(topic);
            }
        }catch (SQLException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
        return topics;
    }
}
