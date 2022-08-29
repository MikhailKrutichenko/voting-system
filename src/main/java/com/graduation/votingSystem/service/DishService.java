package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.Dish;
import com.graduation.votingSystem.repository.dish.DishRepository;
import com.graduation.votingSystem.util.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id) {
        return ValidationUtil.checkNotFound(repository.get(id), id);
    }

    public Dish create(Dish dish) {
        return repository.save(dish);
    }

    public void update(Dish dish, int id) {
        dish.setId(id);
        ValidationUtil.checkNotFound(repository.save(dish), id);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFound(repository.delete(id), id);
    }
}
