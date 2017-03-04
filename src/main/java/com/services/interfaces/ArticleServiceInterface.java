package com.services.interfaces;

import com.common.exceptions.MyException;
import com.models.pojo.Article;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface ArticleServiceInterface {
    public Article getArticleById(int id) throws MyException;
    public ArrayList<Article> getArticlesByTopicId(int id) throws MyException;
}
