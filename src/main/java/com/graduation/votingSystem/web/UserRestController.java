package com.graduation.votingSystem.web;

import com.graduation.votingSystem.model.User;
import com.graduation.votingSystem.service.UserService;
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
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = UserRestController.USERS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api("User controller")
public class UserRestController {

    public static final String USERS_URL = "admin/users";

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService service;

    @ApiOperation("Get")
    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get id={}", id);
        return service.get(id);
    }

    @ApiOperation("Delete")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete id={}", id);
        service.delete(id);
    }

    @ApiOperation("Get all")
    @GetMapping
    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @ApiOperation("Create")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        log.info("Create");
        User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(USERS_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ApiOperation("Update")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody User user) {
        log.info("Update id={}", id);
        service.update(user, id);
    }
}
