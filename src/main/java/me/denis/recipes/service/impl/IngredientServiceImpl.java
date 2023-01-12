package me.denis.recipes.service.impl;

import me.denis.recipes.model.Ingredient;
import me.denis.recipes.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Integer id = 1;
    private final Map<Integer, Ingredient> ingredients = new HashMap<>();

    @Override
    public Ingredient add(Ingredient ingredient) {
        return ingredients.put(id++, ingredient);
    }

    @Override
    public Ingredient get(Integer id) {
        return ingredients.get(id);
    }

}
