package me.denis.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.denis.recipes.model.Ingredient;
import me.denis.recipes.service.FilesService;
import me.denis.recipes.service.IngredientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Integer id = 1;
    private Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();
    private final FilesService filesService;

    public IngredientServiceImpl(@Qualifier("filesServiceIngredientImpl") FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        Ingredient newIngredient = ingredients.put(id++, ingredient);
        saveToFile();
        return newIngredient;
    }

    @Override
    public Ingredient get(Integer id) {
        return ingredients.get(id);
    }

    @Override
    public Ingredient delete(Integer id) {
        return ingredients.remove(id);
    }

    @Override
    public Ingredient edit(Integer id, Ingredient ingredient) {
        if (!ingredients.containsKey(id)) {
            throw new RuntimeException("Ингредиент не найден");
        } else {
            ingredients.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredients.values());
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json =  filesService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
