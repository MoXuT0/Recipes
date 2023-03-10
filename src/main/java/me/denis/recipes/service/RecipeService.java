package me.denis.recipes.service;

import me.denis.recipes.model.Recipe;

import java.io.File;
import java.util.List;

public interface RecipeService {

    Recipe add(Recipe recipe);

    Recipe get(Integer id);

    Recipe delete(Integer id);

    Recipe edit(Integer id, Recipe recipe);

    List<Recipe> getAll();

    File createTxtFile();

}
