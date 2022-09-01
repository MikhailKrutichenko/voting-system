package com.graduation.votingSystem.util;

import com.graduation.votingSystem.model.User;
import com.graduation.votingSystem.model.Vote;
import com.graduation.votingSystem.util.exception.ConstrainException;
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

    public static User checkNotFound(User entity, String email) {
        if (entity == null) {
            throw new NotFoundException("Entity not found with email" + email);
        }
        return entity;
    }

    public static void checkVoteConstraint(Vote vote) {
        if (vote == null) {
            throw new ConstrainException("This is user already vote.");
        }
    }
}
