package me.denis.recipes.service.impl;

import me.denis.recipes.model.Recipe;
import me.denis.recipes.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Integer id = 1;
    private final Map<Integer, Recipe> recipes = new HashMap<>();

    @Override
    public Recipe add(Recipe recipe) {
        return recipes.put(id++, recipe);
    }

    @Override
    public Recipe delete(Integer id) {
        return recipes.remove(id);
    }

    @Override
    public Recipe get(Integer id) {
        return recipes.get(id);
    }

    @Override
    public Recipe edit(Integer id, Recipe recipe) {
        if (!recipes.containsKey(id)) {
            throw new RuntimeException("Рецепт не найден");
        } else
            return recipes.put(id, recipe);
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipes.values());
    }

}
