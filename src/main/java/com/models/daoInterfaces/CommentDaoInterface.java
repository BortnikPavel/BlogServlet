package com.models.daoInterfaces;

import com.common.exceptions.MyException;
import com.models.pojo.Comment;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by admin on 04.03.2017.
 */
public interface CommentDaoInterface {
    public boolean addComment(Comment comment) throws MyException;
    public Comment getCommentById(int id) throws MyException;
    public ArrayList<Comment> getAllComments() throws MyException;
    public ArrayList<Comment> getAllCommentsByUserId(int id) throws MyException;
    public boolean deleteComment(Comment comment) throws MyException;
}
