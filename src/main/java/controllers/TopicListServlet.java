package controllers;

import models.pojo.Topic;
import org.apache.log4j.Logger;
import services.TopicService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Павел on 26.02.2017.
 */
public class TopicListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(TopicListServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Topic> topics = TopicService.getAllTopics();
            req.setAttribute("topics", topics);
            req.getRequestDispatcher("topicsList.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("mess", "Sorry some problem with our system, try later)");
            req.getRequestDispatcher("topicsList.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
