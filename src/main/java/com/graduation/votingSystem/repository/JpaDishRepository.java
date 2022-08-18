package com.graduation.votingSystem.repository;

import com.graduation.votingSystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.id=:id")
    Dish get(@Param("id") int id);
}
