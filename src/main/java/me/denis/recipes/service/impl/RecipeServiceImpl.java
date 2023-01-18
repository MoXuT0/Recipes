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
        return recipes.get(id);
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
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json =  filesService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
