package com.services.interfaces;

import com.common.exceptions.MyException;
import com.models.pojo.NewArticle;

import java.util.ArrayList;

/**
 * Created by admin on 13.03.2017.
 */
public interface NewArticleServiceInterface {
    boolean addNewArticle(NewArticle article) throws MyException;
    ArrayList<NewArticle> getArticles() throws MyException;
}
