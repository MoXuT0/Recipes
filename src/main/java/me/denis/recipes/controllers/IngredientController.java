package me.denis.recipes.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.denis.recipes.model.Ingredient;
import me.denis.recipes.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Ингредиенты", description = "операции для работы с ингредиентами")
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ingredientService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.edit(id, ingredient));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }

}