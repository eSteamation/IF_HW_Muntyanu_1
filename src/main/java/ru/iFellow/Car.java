package ru.iFellow;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Car {
    private String modelName;
    private String transmissionType;
    private String color;
    private String brand;
    private int year;
    private int horsepower;
    private int price;

    public Car(String brand, String modelName, String transmissionType, String color, int year, int horsepower, int price) {
        this.modelName = modelName;
        this.color = color;
        this.transmissionType = transmissionType;
        this.year = year;
        this.horsepower = horsepower;
        this.price = price;
        this.brand = brand;
    }

    public void getInformationAboutCar() {
        System.out.println(brand + " | " + this.modelName + " | " + this.year + " | " + this.color + " | " + this.transmissionType + " | " + this.horsepower + " | " + this.price);
    }

    public void outdatedCars() {
        System.out.println(brand + ", " + this.modelName + ";");
    }
}