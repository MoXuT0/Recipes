package me.denis.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.denis.recipes.model.Recipe;
import me.denis.recipes.service.FilesService;
import me.denis.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Integer id = 1;
    private Map<Integer, Recipe> recipes = new LinkedHashMap<>();
    private final FilesService filesService;

    public RecipeServiceImpl(@Qualifier("filesServiceRecipeImpl") FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe add(Recipe recipe) {
        Recipe newRecipe = recipes.put(id++, recipe);
        saveToFile();
        return newRecipe;
    }

    @Override
    public Recipe get(Integer id) {
        if(!recipes.containsKey(id)) {
            throw new RuntimeException("Рецепт не найден");
        } else {
            return recipes.get(id);
        }
    }

    @Override
    public Recipe delete(Integer id) {
        return recipes.remove(id);
    }

    @Override
    public Recipe edit(Integer id, Recipe recipe) {
        if (!recipes.containsKey(id)) {
            throw new RuntimeException("Рецепт не найден");
        } else {
            recipes.put(id, recipe);
            saveToFile();
            return recipe;
        }
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipes.values());
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            String json =  filesService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File createTxtFile() {
        Path path = filesService.createTempFile("recipes");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            for (Recipe recipe : recipes.values()) {
                writer.append(recipe.getTitle())
                        .append(System.lineSeparator());
                writer.append("Время приготовления: ")
                        .append(String.valueOf(recipe.getCookingTime()))
                        .append(" минут.")
                        .append(System.lineSeparator());
                writer.append("Ингредиенты:")
                        .append(System.lineSeparator());
                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                    writer.append(recipe.getIngredients().get(i).getTitle())
                            .append(" - ")
                            .append(String.valueOf(recipe.getIngredients().get(i).getAmount()))
                            .append(" ").append(recipe.getIngredients().get(i).getMeasureUnit())
                            .append(System.lineSeparator());
                }
                writer.append("Инструкция приготовления:")
                        .append(System.lineSeparator());
                for (int j = 0; j < recipe.getSteps().size(); j++) {
                    writer.append(recipe.getSteps().get(j))
                            .append(System.lineSeparator());
                }
                writer.append("----------------------------------------------------")
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path.toFile();
    }

}
