package com.behnam.spring5recipeapp.controller;


import com.behnam.spring5recipeapp.model.Category;
import com.behnam.spring5recipeapp.model.UnitOfMeasure;
import com.behnam.spring5recipeapp.repositories.CategoryRepository;
import com.behnam.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.behnam.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");
        model.addAttribute("recipes",recipeService.getRecipes());

        return "index";
    }
}
