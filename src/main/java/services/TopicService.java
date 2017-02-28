package services;

import common.exceptions.MyException;
import models.dao.TopicDao;
import models.pojo.Topic;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Павел on 26.02.2017.
 */
public class TopicService {
    public static ArrayList<Topic> getAllTopics() throws MyException {
        return TopicDao.getAllTopics();
    }
}
