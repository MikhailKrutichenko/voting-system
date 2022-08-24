package com.graduation.votingSystem.repository.vote;

import com.graduation.votingSystem.VotingConfigurationProperties;
import com.graduation.votingSystem.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepository {

    private final JpaVoteRepository repository;

    @Autowired
    private VotingConfigurationProperties properties;

    public VoteRepository(JpaVoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void vote(int restaurantId, int userId) {
        Vote vote = repository.get(LocalDate.of(2022, Month.AUGUST, 17), userId);
        if (vote == null) {
            Vote newVote = new Vote(userId, restaurantId, LocalDate.of(2022, Month.AUGUST, 17));
            repository.save(newVote);
        } else if (LocalTime.now().isBefore(properties.getTime())) {
            vote.setRestaurantId(restaurantId);
            vote.setUserId(userId);
            repository.save(vote);
        }
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }
}
