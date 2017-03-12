package com.models.daoInterfaces;

import com.common.exceptions.MyException;
import com.models.pojo.NewArticle;

import java.util.ArrayList;

/**
 * Created by admin on 12.03.2017.
 */
public interface NewArticleDaoInterface {
    boolean addNewArticle(NewArticle article) throws MyException;
    ArrayList<NewArticle> getArticles() throws MyException;
}
