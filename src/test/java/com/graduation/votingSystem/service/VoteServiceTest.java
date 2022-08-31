package com.graduation.votingSystem.service;

import com.graduation.votingSystem.PropertiesConfig;
import com.graduation.votingSystem.model.Vote;
import com.graduation.votingSystem.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
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
    PropertiesConfig properties;

    @Test
    void changeVoteBeforeConstrainTime() {
        LocalTime requiredTime = properties.getTime().minusHours(1);
        try (MockedStatic<LocalTime> mockTime = Mockito.mockStatic(LocalTime.class)) {
            mockTime.when(LocalTime::now).thenReturn(requiredTime);
            Vote expect = service.get(VOTE_1_ID);
            service.vote(MM_ID, USER_1_ID);
            assertThat(service.get(VOTE_1_ID))
                    .usingRecursiveComparison()
                    .isNotEqualTo(expect);

            Vote changed = getChanged();
            assertThat(service.get(VOTE_1_ID))
                    .usingRecursiveComparison()
                    .isEqualTo(changed);
        }
    }

    @Test
    void changeVoteAfterConstrainTime() {
        LocalTime requiredTime = properties.getTime().plusHours(1);
        try (MockedStatic<LocalTime> mockTime = Mockito.mockStatic(LocalTime.class)) {
            mockTime.when(LocalTime::now).thenReturn(requiredTime);
            Vote expect = service.get(VOTE_1_ID);
            service.vote(MM_ID, USER_1_ID);
            assertThat(service.get(VOTE_1_ID))
                    .usingRecursiveComparison()
                    .isEqualTo(expect);
        }
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
    void notFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.get(NOT_EXISTS_ID));
    }
}