package me.denis.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.denis.recipes.model.Ingredient;
import me.denis.recipes.service.IngredientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "Ингредиенты", description = "Операции для работы с ингредиентами")
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента", description = "Добавление ингредиента по схеме")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент добавлен", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getTitle()) || StringUtils.isEmpty(ingredient.getTitle())) {
            return ResponseEntity.badRequest().body("Название не заполено");
        }
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск ингредиента", description = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент найден", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") Integer id) {
        Ingredient ingredient = ingredientService.get(id);
        if (Objects.isNull(ingredient)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента", description = "Удаление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент удален"),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Integer id) {
        Ingredient ingredient = ingredientService.delete(id);
        if (Objects.isNull(ingredient)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента", description = "Редактирование ингредиента по id и схеме")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент отредактирован", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<?> editIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getTitle()) || StringUtils.isEmpty(ingredient.getTitle())) {
            return ResponseEntity.badRequest().body("Название индгредиента не заполено");
        }
        return ResponseEntity.ok(ingredientService.edit(id, ingredient));
    }

    @GetMapping
    @Operation(summary = "Список всех ингредиентов", description = "Получение списка всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты найдены", content = {
                    @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "Ингредиенты не найдены"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }

}