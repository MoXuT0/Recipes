package me.denis.recipes.controllers;

import me.denis.recipes.model.Recipe;
import me.denis.recipes.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.add(recipe);
    }

    @GetMapping("{id}")
    public Recipe getRecipe(@PathVariable("id") Integer id) {
        return this.recipeService.get(id);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") Integer id) {
        return recipeService.delete(id);
    }

    @PutMapping("/{id}")
    public Recipe editRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        return recipeService.edit(id, recipe);
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAll();
    }

}