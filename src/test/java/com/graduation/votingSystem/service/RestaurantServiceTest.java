package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.Restaurant;
import com.graduation.votingSystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.graduation.votingSystem.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void get() {
        Restaurant expected = MAC;
        assertThat(service.get(MAC_ID)).usingRecursiveComparison()
                .ignoringFields("dish", "votesPerDay")
                .isEqualTo(expected);
    }

    @Test
    void getWithDishes() {
        Restaurant expected = withDishes();
        assertThat(service.getWithDishes(MAC_ID, LocalDate.now()))
                .usingRecursiveComparison()
                .ignoringFields("votesPerDay")
                .isEqualTo(expected);
    }

    @Test
    void getAllWithVotesPerDay() {
        List<Restaurant> expected = withVotesPerDay();
        assertThat(service.getAllWithVotesPerDay(LocalDate.now()))
                .usingRecursiveComparison()
                .ignoringFields("dish")
                .isEqualTo(expected);
    }

    @Test
    void delete() {
        service.delete(MAC_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(MAC_ID));
    }

    @Test
    void deleteNotExistId() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(NOT_EXISTS_ID));
    }

    @Test
    void getAll() {
        assertThat(service.getAll())
                .usingRecursiveComparison()
                .ignoringFields("dish", "votesPerDay")
                .isEqualTo(List.of(IBURGER, MM, MAC));
    }

    @Test
    void create() {
        Restaurant created = service.create(getNew());
        Restaurant expected = getNew();
        expected.setId(created.getId());
        assertThat(created)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void update() {
        Restaurant expected = getUpdated();
        service.update(expected);
        assertThat(service.get(expected.getId()))
                .usingRecursiveComparison()
                .ignoringFields("dish", "votesPerDay")
                .isEqualTo(expected);
    }

    @Test
    void updateNotExistId() {
        Restaurant withNotExistId = getWithNotExistId();
        Assertions.assertThrows(NotFoundException.class, () -> service.update(withNotExistId));
    }
}