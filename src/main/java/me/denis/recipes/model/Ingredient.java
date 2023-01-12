package me.denis.recipes.model;

import lombok.Data;

@Data
public class Ingredient {

    private String title;
    private int amount;
    private String measureUnit;

}
