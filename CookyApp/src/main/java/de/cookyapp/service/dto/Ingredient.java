package de.cookyapp.service.dto;

import de.cookyapp.persistence.entities.IngredientEntity;

/**
 * Created by Jasper on 12.04.2016.
 */
public class Ingredient {
    private int id;
    private String name;
    private String unit;
    private String amount;

    public Ingredient () {
    }

    public Ingredient(IngredientEntity ingredientEntity) {
        setId(ingredientEntity.getId());
        setName(ingredientEntity.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
