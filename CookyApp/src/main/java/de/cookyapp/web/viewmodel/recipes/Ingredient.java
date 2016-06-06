package de.cookyapp.web.viewmodel.recipes;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jasper on 08.12.2015.
 */
public class Ingredient {

    @NotBlank( message = "Der Zutatenname darf höchstens 100 Zeichen lang sein" )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß][a-zA-ZäöüÄÖÜß]*$", message = "Der Zutatenname darf nur aus Buchstaben bestehen" )
    @Size( max = 100, message = "Der Zutatenname kann höchstens 100 Zeichen lang sein" )
    private String name;

    @NotBlank( message = "Die Einheit darf höchstens 10 Zeichen lang sein" )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß][a-zA-ZäöüÄÖÜß]*$", message = "Die Einheit darf nur aus Buchstaben bestehen" )
    @Size( max = 10, message = "Die Unit darf höchstesn 10 Zeichen lang sein" )
    private String unit;

    @NotBlank( message = "Die Mengenangabe darf nur aus Zahlen bestehen" )
    @Pattern( regexp = "^[0-9][0-9]*$", message = "Die Mengenangaben dürfen nur Zahlen enthalten" )
    @Size( max = 1000000 )
    private String amount;

    private int id;

    public Ingredient() {

    }

    public Ingredient( de.cookyapp.service.dto.Ingredient ingredient ) {
        setId( ingredient.getId() );
        setName( ingredient.getName() );
        setAmount( ingredient.getAmount() );
        setUnit( ingredient.getUnit() );
    }

    public Ingredient( String name, String unit, String amount ) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit( String unit ) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount( String amount ) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }
}
