package me.denis.recipes.service;

import me.denis.recipes.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe add(Recipe recipe);

    Recipe delete(Integer id);

    Recipe get(Integer id);

    Recipe edit(Integer id, Recipe recipe);

    List<Recipe> getAll();

}
