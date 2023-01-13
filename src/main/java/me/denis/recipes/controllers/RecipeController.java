package me.denis.recipes.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.denis.recipes.model.Recipe;
import me.denis.recipes.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Рецепты", description = "операции для работы с рецептами")
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(recipeService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.edit(id, recipe));
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }

}