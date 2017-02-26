package services;

import models.dao.ArticleDao;
import models.pojo.Article;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Павел on 26.02.2017.
 */
public class ArticleService {
    public static Article getArticleById(int id) throws SQLException {
        return ArticleDao.getArticleById(id);
    }

    public static ArrayList<Article> getArticlesByTopicId(int id) throws SQLException {
        return ArticleDao.getArticleByTopicId(id);
    }
}
