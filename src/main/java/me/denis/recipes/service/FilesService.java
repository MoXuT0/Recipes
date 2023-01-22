package me.denis.recipes.service;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanFile();

    File getFile();

    Path createTempFile(String suffix);

}
