package models.dao;


import models.connectors.ConnectionDB;
import models.pojo.Topic;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
public class TopicDao{
    public static void addTopic(Topic topic) throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO topics " +
                "(id, name) " +
                "VALUES (\"" + topic.getId() + "\", \"" + topic.getName() + "\")");

    }

    public static void updateTopic(Topic topic, int id) throws SQLException {

    }

    public static void deleteTopic() throws SQLException {
        Connection connection = ConnectionDB.getConnectionDB();
        Statement statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE topics");
    }

    public static Topic getTopicById(int id) throws SQLException {
        Topic topic = new Topic();
        Connection connection = ConnectionDB.getConnectionDB();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM topics where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        topic.setId(resultSet.getInt(1));
        topic.setName(resultSet.getString(2));
        return topic;
    }

    public static ArrayList<Topic> getAllTopics() throws SQLException {
        ArrayList<Topic> topics = new ArrayList<Topic>();
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
        return topics;
    }
}
