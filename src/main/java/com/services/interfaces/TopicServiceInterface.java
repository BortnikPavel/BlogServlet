package com.services.interfaces;

import com.common.exceptions.MyException;
import com.models.dao.TopicDao;
import com.models.pojo.Topic;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface TopicServiceInterface {
    public ArrayList<Topic> getAllTopics() throws MyException;
}
