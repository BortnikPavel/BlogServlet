package com.controllers;

import com.common.exceptions.MyException;
import com.models.pojo.Article;
import com.services.interfaces.ArticleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 04.03.2017.
 */
public class ArticleServlet extends HttpServlet {
    private ArticleServiceInterface articleServlet;

    @Autowired
    public void setArticleServlet(ArticleServiceInterface articleServlet) {
        this.articleServlet = articleServlet;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        try {
            Article article = articleServlet.getArticleById(Integer.parseInt(param));
            req.setAttribute("article", article);
            req.getRequestDispatcher("/article.jsp").forward(req, resp);
        } catch (MyException e) {
            req.setAttribute("mess", "Sorry some problem with our system, try later)");
            req.getRequestDispatcher("articlesList.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
}
