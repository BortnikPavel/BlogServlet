package com.controllers.springMVC;

import com.common.exceptions.MyException;
import com.models.pojo.Article;
import com.models.pojo.Comment;
import com.services.interfaces.ArticleServiceInterface;
import com.services.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by admin on 07.03.2017.
 */
@Controller
public class ArticleController {
    private ArticleServiceInterface articleService;
    private CommentServiceInterface commentService;

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

    @RequestMapping(value = "article", method = RequestMethod.GET)
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


}
