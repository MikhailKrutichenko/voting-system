package com.graduation.votingSystem.repository.restaurant;

import com.graduation.votingSystem.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final JpaRestaurantRepository repository;

    public RestaurantRepository(JpaRestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }

    public Restaurant getWithDishes(int id, LocalDate date) {
        return repository.getWithDishes(id, date);
    }

    public List<Restaurant> getAllWithVotesPerDay(LocalDate date) {
        return repository.getAllWithVotesPerDay(date);
    }

    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            return repository.save(restaurant);
        } else if (repository.existsById(restaurant.getId())) {
            Restaurant updated = repository.getReferenceById(restaurant.getId());
            updated.setName(restaurant.getName());
            return repository.save(updated);
        }
        return null;
    }
}
