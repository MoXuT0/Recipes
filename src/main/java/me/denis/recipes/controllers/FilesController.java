package me.denis.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.denis.recipes.service.FilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FilesController {

    private final FilesService filesServiceIngredient;
    private final FilesService filesServiceRecipe;


    public FilesController(@Qualifier("filesServiceIngredientImpl") FilesService filesServiceIngredient,
                           @Qualifier("filesServiceRecipeImpl") FilesService filesServiceRecipe) {
        this.filesServiceIngredient = filesServiceIngredient;
        this.filesServiceRecipe = filesServiceRecipe;
    }

    @GetMapping(value = "/recipe/download")
    @Operation(summary = "Скачать все рецепты в виде json-файла")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл скачан")
    })
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesServiceRecipe.getFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/recipe/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл с рецептами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл загружен")
    })
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile multipartFile) {
        filesServiceRecipe.cleanFile();
        File file = filesServiceRecipe.getFile();
        try (FileOutputStream fos = new FileOutputStream(file)){
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/ingredient/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл с ингредиентами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл загружен")
    })
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile multipartFile) {
        filesServiceIngredient.cleanFile();
        File file = filesServiceIngredient.getFile();
        try (FileOutputStream fos = new FileOutputStream(file)){
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
