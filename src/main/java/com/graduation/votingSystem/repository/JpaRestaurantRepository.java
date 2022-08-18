package com.graduation.votingSystem.repository;

import com.graduation.votingSystem.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @EntityGraph(attributePaths = {"dish"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN Dish d ON r.id=:id AND d.date=:date")
    Restaurant getWithDishes(@Param("id") int id, @Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"votesPerDay"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN Vote v ON v.date=:date ORDER BY SIZE(r.votesPerDay) DESC")
    List<Restaurant> getAllWithVotesPerDay(@Param("date") LocalDate date);

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);
}
