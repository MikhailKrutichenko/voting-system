package com.graduation.votingSystem.repository;

import com.graduation.votingSystem.model.Vote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepository {

    private final JpaVoteRepository repository;

    public VoteRepository(JpaVoteRepository repository) {
        this.repository = repository;
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void vote(int restaurantId, int userId) {
        Vote vote = repository.get(LocalDate.now(), userId);
        if (vote == null) {
            Vote newVote = new Vote(userId, restaurantId, LocalDate.now());
            repository.save(newVote);
        } else if (LocalTime.now().isBefore(LocalTime.parse("11:00"))) {
            Vote updateVote = repository.getReferenceById(vote.getId());
            updateVote.setRestaurantId(restaurantId);
            repository.save(updateVote);
        }
    }
}
