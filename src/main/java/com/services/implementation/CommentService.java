package com.services.implementation;

import com.common.exceptions.MyException;
import com.models.dao.CommentDao;
import com.models.daoInterfaces.CommentDaoInterface;
import com.models.pojo.Comment;
import com.services.interfaces.CommentServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
@Component
public class CommentService implements CommentServiceInterface {
    private CommentDaoInterface commentDao;

    @Autowired
    public void setCommentDao(CommentDaoInterface commentDao) {
        this.commentDao = commentDao;
    }

    public ArrayList<Comment> getCommentByArticleId(int id) throws MyException {
        return commentDao.getAllCommentsByArticleId(id);
    }
}
