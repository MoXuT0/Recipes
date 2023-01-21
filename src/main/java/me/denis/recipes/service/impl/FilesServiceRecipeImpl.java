package me.denis.recipes.service.impl;

import me.denis.recipes.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceRecipeImpl implements FilesService {
    @Value("${path.to.recipe.file}")
    private String recipeFilePath;
    @Value("${name.of.recipe.file}")
    private String recipeFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(recipeFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanFile() {
        try {
            Path path = Path.of(recipeFilePath, recipeFileName);
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
        return new File(recipeFilePath + "/" + recipeFileName);
    }

}
