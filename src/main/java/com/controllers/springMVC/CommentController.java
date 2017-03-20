package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.models.pojo.Article;
import com.models.pojo.Comment;
import com.models.pojo.User;
import com.services.interfaces.ArticleServiceInterface;
import com.services.interfaces.CommentServiceInterface;
import com.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by admin on 12.03.2017.
 */
@Controller
public class CommentController {
    CommentServiceInterface commentService;
    ArticleServiceInterface articleService;
    UserServiceInterface userService;

    @Autowired
    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Autowired
    public void setArticleService(ArticleServiceInterface articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCommentService(CommentServiceInterface commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/user/commentAdd", method = RequestMethod.POST)
    public ModelAndView addComment(@RequestParam String comment,
                                   @RequestParam int articleId,
                                   HttpSession session,
                                   ModelAndView modelAndView){
        if(comment.length() < 256 && comment.length() > 0) {
            try {
                Article article = articleService.getArticleById(articleId);
                User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
                Comment c = new Comment(comment, "1 april 2017", user, article);
                modelAndView.setViewName("/user/commentAdd");
                if (commentService.addComment(c)) {
                    ArrayList<Comment> comments = commentService.getCommentByArticleId(article.getId());
                    modelAndView.addObject("article", article);
                    modelAndView.addObject("comments", comments);
                }
                return modelAndView;
            } catch (MyException e) {
                e.printStackTrace();
                return null;
            }
        }else {
            try {
                Article article = articleService.getArticleById(articleId);
                ArrayList<Comment> comments = commentService.getCommentByArticleId(articleId);
                modelAndView.addObject("article", article);
                modelAndView.addObject("comments", comments);
                return modelAndView;
            } catch (MyException e) {
                e.printStackTrace();
            }
            modelAndView.setViewName("/user/Articles");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/user/commentAdd", method = RequestMethod.GET)
    public String showArticleWithComment(){
        return "/user/commentAdd";
    }
}
