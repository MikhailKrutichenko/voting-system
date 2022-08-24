package com.graduation.votingSystem.repository.dish;

import com.graduation.votingSystem.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class DishRepository {

    private final JpaDishRepository repository;

    public DishRepository(JpaDishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id) {
        return repository.get(id);
    }

    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Transactional
    public Dish save(Dish dish) {
        Dish d;
        if (dish.isNew() || (d = repository.get(dish.getId())) != null &&
                Objects.equals(d.getRestaurantId(), dish.getRestaurantId())) {
            return repository.save(dish);
        }
        return null;
    }
}
