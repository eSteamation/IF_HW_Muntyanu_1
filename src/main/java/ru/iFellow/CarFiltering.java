package ru.iFellow;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarFiltering {

    public static void printList(List<Car> cars) {
        System.out.println("Model        | Year  | Color   | Transmission   | HP  | Price ");
        System.out.println("--------------------------------------------------------------");
        cars.forEach(Car::getInformationAboutCar);
    }

    public static void yearCheck(List<Car> cars) {
        System.out.println("Model        | Year  | Color   | Transmission   | HP  | Price ");
        System.out.println("--------------------------------------------------------------");
        cars.stream()
                .filter((car) -> car.getYear() > 2006)
                .forEach(Car::getInformationAboutCar);
        System.out.println("Outdated models:");
        cars.stream()
                .filter((car) -> car.getYear() <= 2006)
                .forEach(Car::outdatedCars);
    }

    public static void hpSorting(List<Car> cars) {
        System.out.println("Model        | Year  | Color   | Transmission   | HP  | Price ");
        System.out.println("--------------------------------------------------------------");
        List<Car> newCars = cars.stream()
                .sorted(Comparator.comparingInt(Car::getHorsepower))
                .collect(Collectors.toList());
        newCars.forEach(Car::getInformationAboutCar);
    }

    public static void colorChange(List<Car> cars) {
        System.out.println("Model        | Year  | Color   | Transmission   | HP  | Price ");
        System.out.println("--------------------------------------------------------------");
        List<Car> newCars = cars.stream()
                .map(car -> {
                    if ("Green".equals(car.getColor())) {
                        car.setColor("Red");
                    }
                    return car;
                })
                .collect(Collectors.toList());
        newCars.forEach(Car::getInformationAboutCar);
    }
}
