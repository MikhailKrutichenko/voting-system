package com.graduation.votingSystem.service;

import com.graduation.votingSystem.VotingConfigurationProperties;
import com.graduation.votingSystem.model.Vote;
import com.graduation.votingSystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

import static com.graduation.votingSystem.RestaurantTestData.MM_ID;
import static com.graduation.votingSystem.UserTestData.ADMIN_ID;
import static com.graduation.votingSystem.UserTestData.USER_1_ID;
import static com.graduation.votingSystem.VoteTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Autowired
    VotingConfigurationProperties properties;

    @Test
    void changeVoteBeforeConstrainTime() {
        properties.setTime(LocalTime.now().plusHours(1));
        Vote expect = service.get(VOTE_1_ID);
        service.vote(MM_ID, USER_1_ID);
        assertThat(service.get(VOTE_1_ID))
                .usingRecursiveComparison()
                .isNotEqualTo(expect);

        Vote changed = getChange();
        assertThat(service.get(VOTE_1_ID))
                .usingRecursiveComparison()
                .isEqualTo(changed);
    }

    @Test
    void changeVoteAfterConstrainTime() {
        properties.setTime(LocalTime.now().minusHours(1));
        Vote expect = service.get(VOTE_1_ID);
        service.vote(MM_ID, USER_1_ID);
        assertThat(service.get(VOTE_1_ID))
                .usingRecursiveComparison()
                .isEqualTo(expect);
    }

    @Test
    void firstVote() {
        Vote newVote = getNew();
        service.vote(MM_ID, ADMIN_ID);
        assertThat(service.get(newVote.getId()))
                .usingRecursiveComparison()
                .isEqualTo(newVote);
    }

    @Test
    void get() {
        Vote expect = VOTE_1;
        assertThat(service.get(VOTE_1_ID))
                .usingRecursiveComparison()
                .isEqualTo(expect);
    }

    @Test
    void notFoundTest() {
        Assertions.assertThrows(NotFoundException.class, () -> service.get(NOT_EXISTS_ID));
    }
}