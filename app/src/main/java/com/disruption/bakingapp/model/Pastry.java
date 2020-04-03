package com.disruption.bakingapp.model;

import androidx.annotation.Nullable;

import java.util.List;

public class Pastry {

    private int id;

    private String name;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private int servings;

    private String image;

    @Nullable
    private String errorMessage;

    public Pastry(int id, String name, List<Ingredient> ingredients, List<Step> steps, int servings, String image, @Nullable String errorMessage) {
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

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public List<Step> getSteps() {
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

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(@Nullable String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
