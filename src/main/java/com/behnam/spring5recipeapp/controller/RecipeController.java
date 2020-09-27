package com.behnam.spring5recipeapp.controller;


import com.behnam.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        log.debug("getting show page id: " + id);
        model.addAttribute(recipeService.findById(new Long(id)));
        return "recipe/show";
    }
}