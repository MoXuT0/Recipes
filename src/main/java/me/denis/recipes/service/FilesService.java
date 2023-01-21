package me.denis.recipes.service;

import java.io.File;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanFile();

    File getFile();

}
