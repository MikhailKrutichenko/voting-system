package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.User;
import com.graduation.votingSystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.graduation.votingSystem.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    void get() {
        assertThat(service.get(ADMIN_ID))
                .usingRecursiveComparison()
                .isEqualTo(ADMIN);
    }

    @Test
    void delete() {
        service.delete(ADMIN_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(ADMIN_ID));
    }

    @Test
    void deleteNotExistId() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(NOT_EXISTS_ID));
    }

    @Test
    void getAll() {
        assertThat(service.getAll())
                .usingRecursiveComparison()
                .isEqualTo(List.of(ADMIN, USER_1, USER_2, USER_3, USER_4, USER_5, USER_6));
    }

    @Test
    void create() {
        User created = service.create(getNew());
        User expected = getNew();
        expected.setId(created.getId());
        assertThat(created)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void update() {
        User expected = getUpdated();
        service.update(expected);
        assertThat(service.get(expected.getId()))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void updateNotExistId() {
        User withNotExistId = getWithNotExistId();
        Assertions.assertThrows(NotFoundException.class, () -> service.update(withNotExistId));
    }
}