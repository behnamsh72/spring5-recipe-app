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

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);

    }

    @Test
    void getRecipes() {
        Recipe recipe=new Recipe();
        HashSet recipesDate=new HashSet();
        recipesDate.add(recipe);



        when(recipeService.getRecipes()).thenReturn(recipesDate);

        Set<Recipe> recipes=recipeService.getRecipes();

        assertEquals(recipes.size(),1);

        verify(recipeRepository,times(1)).findAll();

    }
}