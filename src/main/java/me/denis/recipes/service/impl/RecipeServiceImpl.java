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
    public Recipe get(Integer id) {
        return recipes.get(id);
    }

}
