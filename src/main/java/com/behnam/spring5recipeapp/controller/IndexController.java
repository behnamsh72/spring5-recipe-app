package com.behnam.spring5recipeapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    public IndexController() {
        System.out.println("Controller constructor");
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(){
        System.out.println("Some message to say... 1234");
        return "index";
    }
}
