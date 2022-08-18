package com.graduation.votingSystem.repository;

import com.graduation.votingSystem.model.Dish;
import org.springframework.stereotype.Repository;

@Repository
public class DishRepository {

    private final JpaDishRepository repository;

    public DishRepository(JpaDishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id) {
        return repository.get(id);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public void update(Dish dish) {
        Dish updated = repository.getReferenceById(dish.getId());
        updated.setDescription(dish.getDescription());
        updated.setDate(dish.getDate());
        updated.setPrice(dish.getPrice());
        repository.save(updated);
    }
}
