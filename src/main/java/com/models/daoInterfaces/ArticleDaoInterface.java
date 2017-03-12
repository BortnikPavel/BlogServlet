package com.models.daoInterfaces;

import com.common.exceptions.MyException;
import com.models.pojo.Article;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface ArticleDaoInterface {
    public boolean addArticle(Article article) throws MyException;
    public boolean deleteArticle(int id) throws MyException;
    public Article getArticleById(int id) throws MyException;
    public ArrayList<Article> getArticleByUserId(int userId) throws MyException;
    public ArrayList<Article> getArticleByTopicId(int topicId) throws MyException;
    public ArrayList<Article> getAllArticle() throws MyException;

}
