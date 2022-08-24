package com.graduation.votingSystem.repository.user;

import com.graduation.votingSystem.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)

public class UserRepository {

    private final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name", "id");

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return repository.findAll(SORT_NAME);
    }

    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Transactional
    public User save(User user) {
        if (user.isNew() || repository.existsById(user.getId())) {
            return repository.save(user);
        }
        return null;
    }
}
