package com.services.implementation;

import com.common.exceptions.MyException;
import com.models.daoInterfaces.NewArticleDaoInterface;
import com.models.pojo.NewArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by admin on 13.03.2017.
 */
@Component
public class NewArticleService implements NewArticleDaoInterface {
    NewArticleDaoInterface newArticleDao;

    @Autowired
    public void setNewArticleDao(NewArticleDaoInterface newArticleDao) {
        this.newArticleDao = newArticleDao;
    }

    @Override
    public boolean addNewArticle(NewArticle article) throws MyException {
        return newArticleDao.addNewArticle(article);
    }

    @Override
    public ArrayList<NewArticle> getArticles() throws MyException {
        return newArticleDao.getArticles();
    }
}
