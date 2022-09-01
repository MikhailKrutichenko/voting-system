package com.graduation.votingSystem.service;

import com.graduation.votingSystem.model.Vote;
import com.graduation.votingSystem.repository.vote.VoteRepository;
import com.graduation.votingSystem.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id) {
        return ValidationUtil.checkNotFound(repository.get(id), id);
    }

    public void vote(int restId, int userId) {
        ValidationUtil.checkVoteConstraint(repository.vote(restId, userId));
    }

    public List<Vote> getAll() {
        return repository.getAll();
    }
}
