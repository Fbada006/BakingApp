package com.disruption.bakingapp.model;

import androidx.annotation.Nullable;

import java.util.List;

public class Pastry {

    private int id;

    private String name;

    private List<Ingredients> ingredients;

    private List<Steps> steps;

    private int servings;

    private String image;

    @Nullable
    private String errorMessage;

    public Pastry(int id, String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image, String errorMessage) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
        this.errorMessage = errorMessage;
    }

    public Pastry() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public List<Steps> getSteps() {
        return this.steps;
    }

    public int getServings() {
        return this.servings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
