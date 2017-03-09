package com.services.interfaces;

import com.common.exceptions.MyException;
import com.models.pojo.Comment;

import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface CommentServiceInterface {
    public ArrayList<Comment> getCommentByArticleId(int id) throws MyException;
}
