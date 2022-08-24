package com.graduation.votingSystem;

import com.graduation.votingSystem.model.Dish;
import com.graduation.votingSystem.model.Restaurant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.graduation.votingSystem.VoteTestData.*;

public class RestaurantTestData {

    public static final int NOT_EXIST_ID = 7;

    public static final int MAC_ID = 10007;
    public static final int IBURGER_ID = 10008;
    public static final int MM_ID = 10009;

    public static final Restaurant MAC = new Restaurant(10007, "mac");
    public static final Restaurant IBURGER = new Restaurant(10008, "iburger");
    public static final Restaurant MM = new Restaurant(10009, "m&m");

    public static Restaurant withDishes() {
        Restaurant restaurantWithDishes = new Restaurant();
        restaurantWithDishes.setName(MAC.getName());
        restaurantWithDishes.setId(MAC_ID);
        List<Dish> menu = Arrays.asList(DishTestData.DISH_1_MAC, DishTestData.DISH_2_MAC);
        restaurantWithDishes.setDish(menu);
        return restaurantWithDishes;
    }

    public static List<Restaurant> withVotesPerDay() {
        MM.setVotesPerDay(List.of(VOTE_3, VOTE_4, VOTE_5, VOTE_6));
        IBURGER.setVotesPerDay(List.of(VOTE_1, VOTE_2));
        MAC.setVotesPerDay(Collections.emptyList());
        List<Restaurant> list = List.of(MM, IBURGER, MAC);
        return list;
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MAC_ID, "New");
    }

    public static Restaurant getWithNotExistId() {
        return new Restaurant(NOT_EXIST_ID, "Not Exist Id");
    }
}
