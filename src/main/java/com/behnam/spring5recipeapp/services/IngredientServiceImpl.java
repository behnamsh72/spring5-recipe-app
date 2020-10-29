package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.commands.IngredientCommand;
import com.behnam.spring5recipeapp.convertors.IngredientCommandToIngredient;
import com.behnam.spring5recipeapp.convertors.IngredientToIngredientCommand;
import com.behnam.spring5recipeapp.model.Ingredient;
import com.behnam.spring5recipeapp.model.Recipe;
import com.behnam.spring5recipeapp.repositories.IngredientRepository;
import com.behnam.spring5recipeapp.repositories.RecipeRepository;
import com.behnam.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeService recipeService;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeService recipeService, IngredientRepository ingredientRepository, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeService = recipeService;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Recipe recipe = recipeService.findById(ingredientCommand.getRecipeId());

        Optional<Ingredient> optionalIngredient = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        if (optionalIngredient.isPresent()) {
            Ingredient foundIngredient = optionalIngredient.get();
            foundIngredient.setAmount(ingredientCommand.getAmount());
            foundIngredient.setDescription(ingredientCommand.getDescription());
            foundIngredient.setUom(unitOfMeasureService.findById(ingredientCommand.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("The UnitOfMeasure must exist before saving an Ingredient with it. The following UnitOfMeasure could not be found: " + ingredientCommand.getUom().toString())));

        } else {
            Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                .filter(recipeIngredient -> recipeIngredient.getAmount().equals((ingredientCommand.getAmount())))
                .filter(recipeIngredient -> recipeIngredient.getDescription().equals((ingredientCommand.getDescription())))
                .filter(recipeIngredient -> recipeIngredient.getUom().getId().equals((ingredientCommand.getUom().getId())))
                .findFirst()
                .get());
    }

    @Override
    @Transactional
    public void deleteById(Long ingredientId, Long recipeId) {
        log.debug("Deleting ingredient: " + recipeId + ":" + ingredientId);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<Ingredient> ingredientOptional =
                    recipe.getIngredients()
                            .stream()
                            .filter(ingredient -> ingredient.getId().equals(ingredientId))
                            .findFirst();
            if (ingredientOptional.isPresent()) {
                log.debug("found ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            log.debug("Recipe id not found. Id" + recipeId);
        }
    }

}
