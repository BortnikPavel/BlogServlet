package controllers;

import models.pojo.Article;
import services.ArticleService;

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
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        int id = Integer.parseInt(param);
        try {
            ArrayList<Article> articles = ArticleService.getArticlesByTopicId(id);
            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/articlesList.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
