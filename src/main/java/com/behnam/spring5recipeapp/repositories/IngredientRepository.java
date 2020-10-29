package com.behnam.spring5recipeapp.repositories;

import com.behnam.spring5recipeapp.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
