package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.models.pojo.Article;
import com.models.pojo.Comment;
import com.models.pojo.User;
import com.services.interfaces.ArticleServiceInterface;
import com.services.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 12.03.2017.
 */
@Controller
public class CommentController {
    CommentServiceInterface commentService;
    ArticleServiceInterface articleService;

    @Autowired
    public void setArticleService(ArticleServiceInterface articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCommentService(CommentServiceInterface commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/commentAdd", method = RequestMethod.POST)
    public ModelAndView addComment(@RequestParam String comment,
                             @RequestParam int articleId,
                             HttpSession session){
        try {
            Article article = articleService.getArticleById(articleId);
            Comment c = new Comment(comment, "1 april 2017", (User) session.getAttribute("user"), article);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("commentAdd");
            if(commentService.addComment(c)){
                Article art = articleService.getArticleById(article.getId());
                ArrayList<Comment> comments = commentService.getCommentByArticleId(article.getId());
                modelAndView.addObject("article", article);
                modelAndView.addObject("comments", comments);
            }
            return modelAndView;
        } catch (MyException e) {
            e.printStackTrace();
            return null;
        }
    }
}
