package com.models.daoInterfaces;

import com.common.exceptions.MyException;
import com.models.pojo.Topic;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface TopicDaoInterface {
    public Topic addTopic(Topic topic) throws MyException;
    public Topic getTopicByName(String name) throws MyException;
    public void deleteTopic(Topic topic) throws MyException;
    public Topic getTopicById(int id) throws MyException;
    public ArrayList<Topic> getAllTopics() throws MyException;
}
