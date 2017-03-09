package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.models.pojo.Topic;
import com.services.interfaces.TopicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by admin on 07.03.2017.
 */
@Controller
public class TopicsController {
    private TopicServiceInterface topicService;

    @Autowired
    public void setTopicService(TopicServiceInterface topicService) {
        this.topicService = topicService;
    }

    @RequestMapping({"/", "/welcomePage"})
    public String showWelcomePage(){
        return "welcomePage";
    }

    @ExceptionHandler

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public ModelAndView showTopics() {
        ModelAndView model = new ModelAndView();
        model.setViewName("topicsList");
        ArrayList<Topic> topics = null;
        try {
            topics = topicService.getAllTopics();
            model.addObject("topics", topics );
        } catch (MyException e) {
            model.addObject("mess", "Sorry some problem with our system, try later)");
        }
        return model;
    }
}
