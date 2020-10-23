package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.commands.RecipeCommand;
import com.behnam.spring5recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long deleteId);

}
