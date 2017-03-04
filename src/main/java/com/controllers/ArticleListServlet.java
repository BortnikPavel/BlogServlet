package com.controllers;

import com.common.exceptions.MyException;
import com.models.pojo.Article;
import com.services.interfaces.ArticleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import com.services.ArticleService;
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
public class ArticleListServlet extends HttpServlet {
    private ArticleServiceInterface articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        int id = Integer.parseInt(param);
        try {
            ArrayList<Article> articles = articleService.getArticlesByTopicId(id);
            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/articlesList.jsp").forward(req, resp);
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
