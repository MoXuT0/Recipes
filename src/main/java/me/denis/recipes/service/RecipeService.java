package me.denis.recipes.service;

import me.denis.recipes.model.Recipe;

public interface RecipeService {

    Recipe add(Recipe recipe);

    Recipe get(Integer id);

}
