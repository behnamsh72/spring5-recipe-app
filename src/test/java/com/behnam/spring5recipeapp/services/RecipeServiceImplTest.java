package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.model.Recipe;
import com.behnam.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    //Injecting dependency with @mock
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {

        //Mockito give me a recipe repository
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);

    }

    @Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);

        //verify that the recipeRepository times once and or saying that the method findAll calls once and only once.
        verify(recipeRepository, times(1)).findAll();
    }
}