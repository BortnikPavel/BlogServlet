package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.models.pojo.Article;
import com.models.pojo.Comment;
import com.models.pojo.User;
import com.services.interfaces.ArticleServiceInterface;
import com.services.interfaces.CommentServiceInterface;
import com.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by admin on 07.03.2017.
 */
@Controller
public class ArticleController {
    private ArticleServiceInterface articleService;
    private CommentServiceInterface commentService;
    private UserServiceInterface userService;

    @Autowired
    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(CommentServiceInterface commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setArticleService(ArticleServiceInterface articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ModelAndView showAllArticles(@RequestParam(name = "id") String param){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("articlesList");
        try {
            ArrayList<Article> articles = articleService.getArticlesByTopicId(Integer.parseInt(param));
            modelAndView.addObject("articles", articles);
        } catch (MyException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView showArticle(@RequestParam(name = "id") String param){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("article");
        try {
            Article article = articleService.getArticleById(Integer.parseInt(param));
            ArrayList<Comment> comments = commentService.getCommentByArticleId(article.getId());
            modelAndView.addObject("article", article);
            modelAndView.addObject("comments", comments);
        } catch (MyException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/userArticles", method = RequestMethod.GET)
    public ModelAndView userArticles(HttpSession session,
                                     ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickName = authentication.getName();
        try {
            User user = userService.getUserByName(nickName);
            ArrayList<Article> articles = articleService.getArticleByUserId(user.getId());
            modelAndView.setViewName("user/userArticles");
            modelAndView.addObject("articles",articles);
        } catch (MyException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/userArticle", method = RequestMethod.GET)
    public ModelAndView showUserArticle(@RequestParam(name = "id") int param){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userArticle");
        try {
            Article article = articleService.getArticleById(param);
            ArrayList<Comment> comments = commentService.getCommentByArticleId(article.getId());
            modelAndView.addObject("article", article);
            modelAndView.addObject("comments", comments);
        } catch (MyException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/deleteArticle", method = RequestMethod.GET)
    public ModelAndView deleteArticle(@RequestParam(name = "id") int param,
                              HttpSession session,
                              ModelAndView modelAndView){
        try {
            articleService.deleteArticle(param);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String nickName = authentication.getName();
            User user = userService.getUserByName(nickName);
            ArrayList<Article> articles = articleService.getArticleByUserId(user.getId());
            modelAndView.setViewName("user/delete");
            modelAndView.addObject("articles",articles);
            return modelAndView;
        } catch (MyException e) {
            e.printStackTrace();
            return null;
        }
    }
}
