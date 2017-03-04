package com.controllers;

import com.common.exceptions.MyException;
import com.models.pojo.Topic;
import com.services.interfaces.TopicServiceInterface;
import org.apache.log4j.Logger;
import com.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Павел on 26.02.2017.
 */
public class TopicListServlet extends HttpServlet {
    private TopicServiceInterface topicService;
    private static Logger logger = Logger.getLogger(TopicListServlet.class);

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Topic> topics = topicService.getAllTopics();
            req.setAttribute("topics", topics);
            req.getRequestDispatcher("topicsList.jsp").forward(req, resp);
        } catch (MyException e) {
            req.setAttribute("mess", "Sorry some problem with our system, try later)");
            req.getRequestDispatcher("topicsList.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
