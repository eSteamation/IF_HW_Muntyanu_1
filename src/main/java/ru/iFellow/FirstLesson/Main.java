package ru.iFellow.FirstLesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //its main origin

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Tesla("Model S", "Automatic", "Green", 2003, 670, 89990));
        cars.add(new BMW("M5", "Automatic", "Black", 1976, 617, 108900));
        cars.add(new Volkswagen("Arteon", "DSG", "Green", 2012, 268, 39995));
        cars.add(new Suzuki("Swift", "Manual", "White", 2023, 90, 19500));
        cars.add(new Ford("Mustang", "Manual", "Green", 2009, 450, 42890));
        cars.add(new Tesla("Model 3", "Automatic", "White", 2013, 283, 42990));
        cars.add(new BMW("X7", "Automatic", "Green", 2005, 523, 77800));
        cars.add(new Volkswagen("Golf GTI", "DSG", "Gray", 2006, 241, 31890));
        cars.add(new Suzuki("Vitara", "Automatic", "Green", 2007, 115, 24999));
        cars.add(new Ford("F-150", "Automatic", "Black", 2021, 400, 45670));

        CarFiltering.printList(cars);

        Scanner myInput = new Scanner(System.in);
        System.out.print("Input '1' to change color of car models.\nInput '2' sort cars by their model year (produced after 2006).\nInput '3' to sort by HP;\n");
        String choiceStr = myInput.nextLine();
        int choiceInt = Integer.parseInt(choiceStr);

        if (choiceInt == 1) {
            CarFiltering.colorChange(cars);
        } else if (choiceInt == 2) {
            CarFiltering.yearCheck(cars);
        } else if (choiceInt == 3) {
            CarFiltering.hpSorting(cars);
        } else {
            System.out.println("Incorrect input, try again.");
        }

    }
}


