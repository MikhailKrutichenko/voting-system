package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.Restaurant;
import com.graduation.votingSystem.repository.restaurant.RestaurantRepository;
import com.graduation.votingSystem.util.ValidationUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFound(repository.get(id), id);
    }

    public Restaurant getWithDishes(int id, LocalDate date) {
        return ValidationUtil.checkNotFound(repository.getWithDishes(id, date), id);
    }

    public List<Restaurant> getAllWithVotesPerDay(LocalDate date) {
        return repository.getAllWithVotesPerDay(date);
    }

    @CacheEvict(value = {"restaurants", "restaurantsWithDishes"}, allEntries = true)
    public void delete(int id) {
        ValidationUtil.checkNotFound(repository.delete(id), id);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant, int id) {
        restaurant.setId(id);
        ValidationUtil.checkNotFound(repository.save(restaurant), id);
    }
}
