package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId);


    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long ingredientId, Long recipeId);
}
