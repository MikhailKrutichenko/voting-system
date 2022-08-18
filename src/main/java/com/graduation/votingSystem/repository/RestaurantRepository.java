package com.graduation.votingSystem.repository;

import com.graduation.votingSystem.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name", "name");

    private final JpaRestaurantRepository restaurantRepository;

    public RestaurantRepository(JpaRestaurantRepository repository) {
        this.restaurantRepository = repository;
    }

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_NAME);
    }

    public Restaurant getWithDishes(int id) {
        return restaurantRepository.getWithDishes(id, LocalDate.now());
    }

    public List<Restaurant> getAllWithVotesPerDay(LocalDate date) {
        return restaurantRepository.getAllWithVotesPerDay(date);
    }

    @Transactional
    public boolean delete(int id) {
        return restaurantRepository.delete(id) != 0;
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void update(Restaurant restaurant) {
        Restaurant updated = restaurantRepository.getReferenceById(restaurant.getId());
        updated.setName(restaurant.getName());
        restaurantRepository.save(updated);
    }
}
