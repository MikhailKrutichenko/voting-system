package com.graduation.votingSystem.util;

import com.graduation.votingSystem.util.exception.NotFoundException;

public class ValidationUtil {

    public static <T> T checkNotFound(T entity, int id) {
        if (entity == null) {
            throw new NotFoundException("Entity not found with id=" + id);
        }
        return entity;
    }

    public static void checkNotFound(boolean found, int id) {
        if (!found) {
            throw new NotFoundException("Entity not found with id=" + id);
        }
    }
}
