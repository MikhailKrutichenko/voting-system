package com.graduation.votingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends AbstractBaseEntity {

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
