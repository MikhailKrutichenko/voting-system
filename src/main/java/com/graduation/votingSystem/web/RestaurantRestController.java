package com.graduation.votingSystem.web;

import com.graduation.votingSystem.model.Restaurant;
import com.graduation.votingSystem.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.RESTAURANT_URI, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    public static final String RESTAURANT_URI = "/restaurants";

    @Autowired
    public RestaurantRepository repository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get id={}", id);
        return repository.get(id);
    }

    @GetMapping("/{id}/dishes")
    public Restaurant getWithDishes(@PathVariable int id) {
        log.info("get with dishes id={}", id);
        return repository.getWithDishes(id);
    }

    @GetMapping("/votingResult")
    public List<Restaurant> getAllWithVotesPerDay(@RequestParam LocalDate date) {
        log.info("get voting result for {}", date);
        return repository.getAllWithVotesPerDay(date);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete id={}", id);
        repository.delete(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        Restaurant created = repository.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT_URI + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        log.info("update entity id={}", restaurant.getId());
        repository.update(restaurant);
    }
}
