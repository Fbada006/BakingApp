package com.disruption.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.List;

public class Pastry implements Parcelable {

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

    protected Pastry(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();
        errorMessage = in.readString();
    }

    public static final Creator<Pastry> CREATOR = new Creator<Pastry>() {
        @Override
        public Pastry createFromParcel(Parcel in) {
            return new Pastry(in);
        }

        @Override
        public Pastry[] newArray(int size) {
            return new Pastry[size];
        }
    };

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

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(@Nullable String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeString(image);
        parcel.writeString(errorMessage);
    }
}
