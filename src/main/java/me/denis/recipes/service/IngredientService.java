package me.denis.recipes.service;

import me.denis.recipes.model.Ingredient;

public interface IngredientService {

    Ingredient add(Ingredient ingredient);

    Ingredient get(Integer id);

}
