package ru.iFellow.API.PartOne.Data;


import lombok.Getter;

@Getter
public class CharacterParameters {
    protected String name;
    protected String location;
    protected String species;

    public CharacterParameters(String name, String location, String species) {
        this.name = name;
        this.location = location;
        this.species = species;
    }

}