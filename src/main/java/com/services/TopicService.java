package com.services;

import com.common.exceptions.MyException;
import com.models.dao.TopicDao;
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
    private TopicDao topicDao;

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public ArrayList<Topic> getAllTopics() throws MyException {
        return TopicDao.getAllTopics();
    }
}
