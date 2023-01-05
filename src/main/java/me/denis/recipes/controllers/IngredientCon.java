package me.denis.recipes.controllers;

import me.denis.recipes.model.Ingredient;
import me.denis.recipes.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientCon {

    private final IngredientService ingredientService;

    public IngredientCon(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") Integer id) {
        return ingredientService.delete(id);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") Integer id) {
        return ingredientService.get(id);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        return ingredientService.edit(id, ingredient);
    }

}