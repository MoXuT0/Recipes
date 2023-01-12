package me.denis.recipes.controllers;

import me.denis.recipes.model.Ingredient;
import me.denis.recipes.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") Integer id) {
        return ingredientService.get(id);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") Integer id) {
        return ingredientService.delete(id);
    }

    @PutMapping("/{id}")
    public Ingredient editIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        return ingredientService.edit(id, ingredient);
    }

    @GetMapping
    public List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

}