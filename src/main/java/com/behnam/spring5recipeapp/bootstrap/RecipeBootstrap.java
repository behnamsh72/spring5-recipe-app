package com.behnam.spring5recipeapp.bootstrap;

import com.behnam.spring5recipeapp.model.*;
import com.behnam.spring5recipeapp.repositories.CategoryRepository;
import com.behnam.spring5recipeapp.repositories.RecipeRepository;
import com.behnam.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM NOT FOUND");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM NOT FOUND");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM NOT FOUND");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM NOT FOUND");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM NOT FOUND");
        }
        Optional<UnitOfMeasure> cupSpoonUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM NOT FOUND");
        }


        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoon = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupSpoonUomOptional.get();


        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Excepted Category not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Excepted Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("" +
                "1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "\n"
                + "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
                + "\n" + "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown." +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."
                + "\n" + "\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("" +
                "\n" +
                "\n" +
                "I have used this recipe for years and always get raves. I like it a little chunky, so instead of mashing the avocado with a fork, I put it in the mixing bowl and cut it up with a knife, mixing other ingredients in with the sharp edge of the knife as well. One thing I noticed with the current recipe is that it left off the instructions to add the tomatoes just before serving. That is important to keep the guac from browning.\n");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient(" fresh lime juice or lemon juice", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom, guacRecipe));


        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections(" 1 Make the spice rub: In a small bowl, whisk together the ingredients for the spice rub."
                + "2 Combine everything in the pressure cooker: Add the chicken to the pressure cooker. Arrange the thighs in a single layer in the pot. Sprinkle with the spice blend, then pour over the pineapple juice and ketchup. Stir to coat the chicken evenly." +
                "3 Pressure cook the chicken: Place the lid on your pressure cooker. Make sure that the pressure regulator is set to the “Sealing” position. Select the “Poultry” or “Manual” program and set the time to 15 minutes at high pressure.\n" +
                "\n" +
                "It will take about 15 minutes for your pressure cooker to come up to pressure, and then the actual cooking will begin. The total time from when you seal the pressure cooker to the finished dish is about 30 minutes." +
                " 4 Release the pressure: You can either perform a quick pressure release by moving the vent from “Sealing” to “Venting,” or you can let the pot depressurize naturally (this takes about 15 minutes).");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("" +
                "\n" +
                "\n" +
                "For stovetop pressure cookers, cook for 12 minutes at high pressure and perform a quick pressure release after cooking. Reduce the cooking liquid over high heat on the stovetop.\n");
        tacosRecipe.setNotes(tacoNotes);
        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoon, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom, tacosRecipe));


        tacosRecipe.getCategories().add(americanCategory);
        recipes.add(tacosRecipe);
        return recipes;
    }

    @Transactional //for a Eager get recipes method in hibernate
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("loading bootstrap data");
    }
}
