package models.dao;


import models.connectors.ConnectionDB;
import models.pojo.Topic;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
public class TopicDao{
    private static Logger logger = Logger.getLogger(TopicDao.class);
    public static void addTopic(Topic topic) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO topics " +
                    "(id, name) " +
                    "VALUES (\"" + topic.getId() + "\", \"" + topic.getName() + "\")");
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }

    }

    public static void deleteTopic() throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE topics");
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
    }

    public static Topic getTopicById(int id) throws SQLException {
        Topic topic = new Topic();
        Connection connection = ConnectionDB.getConnectionDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM topics where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            topic.setId(resultSet.getInt(1));
            topic.setName(resultSet.getString(2));
        }catch (SQLException e){
            logger.error(e);
            throw new SQLException();
        }
        return topic;
    }

    public static ArrayList<Topic> getAllTopics() throws SQLException {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        Connection connection = ConnectionDB.getConnectionDB();
        try {
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
            throw new SQLException();
        }
        return topics;
    }
}
