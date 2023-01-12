package me.denis.recipes.service;

import me.denis.recipes.model.Ingredient;

import java.util.List;

public interface IngredientService {

    Ingredient add(Ingredient ingredient);

    Ingredient get(Integer id);

    Ingredient delete(Integer id);

    Ingredient edit(Integer id, Ingredient ingredient);

    List<Ingredient> getAll();

}
