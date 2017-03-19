package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.models.pojo.NewArticle;
import com.models.pojo.Topic;
import com.models.pojo.User;
import com.services.interfaces.NewArticleServiceInterface;
import com.services.interfaces.TopicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 13.03.2017.
 */
@Controller
public class NewArticleController {
    TopicServiceInterface topicService;
    NewArticleServiceInterface newArticleService;

    @Autowired
    public void setNewArticleService(NewArticleServiceInterface newArticleService) {
        this.newArticleService = newArticleService;
    }

    @Autowired
    public void setTopicService(TopicServiceInterface topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(value = "/user/addNewArticle", method = RequestMethod.GET)
    public ModelAndView addNewArticle(ModelAndView modelAndView) throws MyException {
        modelAndView.setViewName("user/addNewArticle");
        modelAndView.addObject("topics", topicService.getAllTopics());
        return modelAndView;
    }

    @RequestMapping(value = "/user/addNewArticle", method = RequestMethod.POST)
    public String addNewArticle(@RequestParam(name = "title") String title,
                                      @RequestParam(name = "text") String text,
                                      HttpSession session,
                                      @RequestParam(name = "menu") String name) throws MyException {
        User user = (User)session.getAttribute("user");
        Topic topic = topicService.getTopicByName(name);
        NewArticle article = new NewArticle(title, text, user, topic);
        newArticleService.addNewArticle(article);
        return "myPage";
    }
}
