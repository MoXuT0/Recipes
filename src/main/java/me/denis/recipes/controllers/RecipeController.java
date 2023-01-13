package me.denis.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.denis.recipes.model.Recipe;
import me.denis.recipes.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "Рецепты", description = "Операции для работы с рецептами")
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта", description = "Добавление рецепта по схеме")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getTitle()) || StringUtils.isEmpty(recipe.getTitle())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск рецепта", description = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт найден", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден"),
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") Integer id) {
        Recipe recipe = recipeService.get(id);
        if (Objects.isNull(recipe)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "Удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден"),
    })
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Integer id) {
        Recipe recipe = recipeService.delete(id);
        if (Objects.isNull(recipe)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта", description = "Редактирование рецепта по id и схеме")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт отредактирован", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден"),
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.edit(id, recipe));
    }

    @GetMapping
    @Operation(summary = "Список всех рецептов", description = "Получение списка всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты найдены", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
            })
    })
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }

}