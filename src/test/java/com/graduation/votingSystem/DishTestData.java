package com.graduation.votingSystem;

import com.graduation.votingSystem.model.Dish;

import java.time.LocalDate;
import java.time.Month;

import static com.graduation.votingSystem.RestaurantTestData.*;

public class DishTestData {

    public static final int NOT_EXIST_ID = 2;

    public static final Dish DISH_1_MAC = new Dish(10010, MAC_ID, "Chicken",
            LocalDate.of(2022, Month.AUGUST, 17), 30000);
    public static final Dish DISH_2_MAC = new Dish(10011, MAC_ID, "Tea",
            LocalDate.of(2022, Month.AUGUST, 17), 15000);
    public static final Dish DISH_1_IBURGER = new Dish(10012, IBURGER_ID, "Burger",
            LocalDate.of(2022, Month.AUGUST, 17), 25000);
    public static final Dish DISH_2_IBURGER = new Dish(10013, IBURGER_ID, "Coffee",
            LocalDate.of(2022, Month.AUGUST, 17), 20000);
    public static final Dish DISH_1_MM = new Dish(10014, MM_ID, "Ice-cream",
            LocalDate.of(2022, Month.AUGUST, 17), 15500);
    public static final Dish DISH_2_MM = new Dish(10015, MM_ID, "Juice",
            LocalDate.of(2022, Month.AUGUST, 17), 25000);

    public static Integer DISH_1_MAC_ID = 10010;

    public static Dish getNew() {
        return new Dish(null, MAC_ID, "New",
                LocalDate.of(2022, Month.AUGUST, 22), 100000);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_1_MAC_ID, MAC_ID, "Updated Dish",
                LocalDate.of(2022, Month.AUGUST, 22), 5000);
    }

    public static Dish getUpdatedWithNotExistId() {
        return new Dish(NOT_EXIST_ID, MAC_ID, "Update Not Exist Id",
                LocalDate.of(2022, Month.AUGUST, 22), 10000);
    }

    public static Dish getUpdatedWithForeignRestaurantId() {
        return new Dish(DISH_1_MAC_ID, MM_ID, "Not Exist Restaurant Id",
                LocalDate.of(2022, Month.AUGUST, 22), 10000);
    }
}
