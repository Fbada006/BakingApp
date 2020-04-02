package com.disruption.bakingapp.model;

import java.util.List;

public class PastryResponse {

    private int id;

    private String name;

    private List<Ingredients> ingredients;

    private List<Steps> steps;

    private int servings;

    private String image;

    public PastryResponse(int id, String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public PastryResponse() {
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

    public String getImage() {
        return this.image;
    }
}
