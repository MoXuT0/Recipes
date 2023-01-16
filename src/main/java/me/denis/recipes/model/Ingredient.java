package me.denis.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String title;
    private int amount;
    private String measureUnit;

}
