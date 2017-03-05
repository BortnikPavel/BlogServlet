package com.services.implementation;

import com.common.exceptions.MyException;
import com.models.dao.ArticleDao;
import com.models.daoInterfaces.ArticleDaoInterface;
import com.models.pojo.Article;
import com.services.interfaces.ArticleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Павел on 26.02.2017.
 */
@Component
public class ArticleService implements ArticleServiceInterface {
    private ArticleDaoInterface articleDao;

    @Autowired
    public void setArticleDao(ArticleDaoInterface articleDao) {
        this.articleDao = articleDao;
    }

    public Article getArticleById(int id) throws MyException {
        return articleDao.getArticleById(id);
    }

    public ArrayList<Article> getArticlesByTopicId(int id) throws MyException {
        return articleDao.getArticleByTopicId(id);
    }
}
