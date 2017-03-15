package com.services.implementation;

import com.common.exceptions.MyException;
import com.models.dao.TopicDao;
import com.models.daoInterfaces.TopicDaoInterface;
import com.models.pojo.Topic;
import com.services.interfaces.TopicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Павел on 26.02.2017.
 */
@Component
public class TopicService implements TopicServiceInterface {
    private TopicDaoInterface topicDao;

    @Autowired
    public void setTopicDao(TopicDaoInterface topicDao) {
        this.topicDao = topicDao;
    }

    public ArrayList<Topic> getAllTopics() throws MyException {
        return topicDao.getAllTopics();
    }

    public Topic getTopicByName(String name) throws MyException {
        return topicDao.getTopicByName(name);
    }
}
