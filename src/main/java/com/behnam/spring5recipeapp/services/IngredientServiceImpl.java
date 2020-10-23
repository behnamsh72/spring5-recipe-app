package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.commands.IngredientCommand;
import com.behnam.spring5recipeapp.convertors.IngredientToIngredientCommand;
import com.behnam.spring5recipeapp.model.Recipe;
import com.behnam.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found id: " + recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId)).map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if (!ingredientCommandOptional.isPresent()) {
            log.error("Ingredient id not found " + ingredientId);
        }
        return ingredientCommandOptional.get();
    }
}
