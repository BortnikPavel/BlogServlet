package com.controllers.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 13.03.2017.
 */
@Controller
public class NewArticleController {

    @RequestMapping(value = "/addNewArticle", method = RequestMethod.GET)
    public String addNewArticle(){
        return "addNewArticle";
    }
}
