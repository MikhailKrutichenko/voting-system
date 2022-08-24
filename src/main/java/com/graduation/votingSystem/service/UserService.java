package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.User;
import com.graduation.votingSystem.repository.user.UserRepository;
import com.graduation.votingSystem.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return ValidationUtil.checkNotFound(repository.get(id), id);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFound(repository.delete(id), id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void update(User user) {
        ValidationUtil.checkNotFound(repository.save(user), user.getId());
    }
}
