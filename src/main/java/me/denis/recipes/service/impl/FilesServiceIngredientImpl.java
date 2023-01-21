package me.denis.recipes.service.impl;

import me.denis.recipes.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceIngredientImpl implements FilesService {

    @Value("${path.to.ingredient.file}")
    private String ingredientFilePath;

    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getFile() {
        return new File(ingredientFilePath + "/" + ingredientFileName);
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(ingredientFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
