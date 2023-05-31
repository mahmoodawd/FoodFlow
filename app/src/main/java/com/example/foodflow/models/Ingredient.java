package com.example.foodflow.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("idIngredient")
    private String id;
    @SerializedName("strIngredient")
    private String name;
    @SerializedName("strDescription")
    private String description;
    @SerializedName("strType")
    private String type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getThumbUrl() {
        String url = "https://www.themealdb.com/images/ingredients/" + name + "-Small.png";
        return url;
    }
}
