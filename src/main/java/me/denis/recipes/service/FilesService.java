package me.denis.recipes.service;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

}
