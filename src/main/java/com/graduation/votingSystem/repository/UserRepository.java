package com.graduation.votingSystem.repository;

import com.graduation.votingSystem.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepository {

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Transactional
    public User create(User user) {
        return repository.save(user);
    }

    @Transactional
    public void update(User user) {
        User updated = repository.getReferenceById(user.getId());

    }
}
