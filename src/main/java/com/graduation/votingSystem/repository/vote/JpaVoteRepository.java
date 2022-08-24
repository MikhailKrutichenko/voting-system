package com.graduation.votingSystem.repository.vote;

import com.graduation.votingSystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

interface JpaVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.date=:date AND v.userId=:userId")
    Vote get(@Param("date") LocalDate date, @Param("userId") int userId);
}
