package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.commands.IngredientCommand;
import com.behnam.spring5recipeapp.convertors.IngredientCommandToIngredient;
import com.behnam.spring5recipeapp.convertors.IngredientToIngredientCommand;
import com.behnam.spring5recipeapp.convertors.UnitOfMeasureCommandToUnitOfMeasure;
import com.behnam.spring5recipeapp.convertors.UnitOfMeasureToUnitOfMeasureCommand;
import com.behnam.spring5recipeapp.model.Ingredient;
import com.behnam.spring5recipeapp.model.Recipe;
import com.behnam.spring5recipeapp.repositories.IngredientRepository;
import com.behnam.spring5recipeapp.repositories.RecipeRepository;
import com.behnam.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientRepository ingredientRepository;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository, unitOfMeasureRepository, ingredientCommandToIngredient, recipeService, ingredientRepository,unitOfMeasureService);
    }

    @Test
    public void findByRecipeIdAndIngredientId() throws Exception {

    }

    @Test
    public void findByRecipeIdAndIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteById() throws Exception{
        //given
        Recipe recipe=new Recipe();
        Ingredient ingredient=new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional=Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        //when
        ingredientService.deleteById(1L,3L);

        //then

        verify(recipeRepository,times(1)).findById(anyLong());
/*
        verify(recipeRepository,times(1)).save(Recipe.class);
*/
    }

}
