package me.denis.recipes.service;

import me.denis.recipes.model.Ingredient;

public interface IngredientService {

    Ingredient add(Ingredient ingredient);

    Ingredient delete(Integer id);

    Ingredient get(Integer id);

    Ingredient edit(Integer id, Ingredient ingredient);

}
