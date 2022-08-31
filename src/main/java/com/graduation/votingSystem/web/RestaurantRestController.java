package com.graduation.votingSystem.web;

import com.graduation.votingSystem.model.Restaurant;
import com.graduation.votingSystem.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api("Restaurant controller")
public class RestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    public RestaurantService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/restaurants/{id}")
    @ApiOperation("Get")
    public Restaurant get(@PathVariable int id) {
        log.info("get id={}", id);
        return service.get(id);
    }

    @GetMapping("/restaurants/{id}/dishes")
    @ApiOperation("Get with dishes for date")
    public Restaurant getWithDishes(@PathVariable int id, @RequestParam LocalDate date) {
        log.info("get with dishes id={}", id);
        return service.getWithDishes(id, date);
    }

    @ApiOperation("Get with votes for date")
    @GetMapping("/restaurants/votingResult")
    public List<Restaurant> getAllWithVotesPerDay(@RequestParam LocalDate date) {
        log.info("get voting result for {}", date);
        return service.getAllWithVotesPerDay(date);
    }

    @ApiOperation("Delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/restaurants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete id={}", id);
        service.delete(id);
    }

    @ApiOperation("Get all")
    @GetMapping("/restaurants")
    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @ApiOperation("Create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/admin/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurants" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ApiOperation("Update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/admin/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody Restaurant restaurant) {
        log.info("update entity id={}", restaurant.getId());
        service.update(restaurant, id);
    }
}
