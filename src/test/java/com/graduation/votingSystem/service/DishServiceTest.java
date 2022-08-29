package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.Dish;
import com.graduation.votingSystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.graduation.votingSystem.DishTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    void get() {
        Dish expected = DISH_1_MAC;
        Dish actual = service.get(DISH_1_MAC_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void create() {
        Dish created = service.create(getNew());
        Dish expected = getNew();
        expected.setId(created.getId());
        assertThat(created).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void update() {
        Dish expected = getUpdated();
        service.update(expected, DISH_1_MAC_ID);
        assertThat(service.get(expected.getId())).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateNotExistId() {
        Dish withNotExistId = getUpdatedWithNotExistId();
        Assertions.assertThrows(NotFoundException.class, () -> service.update(withNotExistId, NOT_EXIST_ID));
    }

    @Test
    void updateWithForeignRestaurantId() {
        Dish withForeignRestaurantId = getUpdatedWithForeignRestaurantId();
        Assertions.assertThrows(NotFoundException.class, () -> service.update(withForeignRestaurantId,DISH_1_MAC_ID));
    }

    @Test
    void delete() {
        Integer deletedId = DISH_1_MAC_ID;
        service.delete(deletedId);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(deletedId));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(NOT_EXISTS_ID));
    }
}