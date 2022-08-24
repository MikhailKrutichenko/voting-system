package com.graduation.votingSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
public class Dish extends AbstractBaseEntity {

    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(name = "name", nullable = false)
    private String description;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Size(min = 100)
    @Column(name = "price", nullable = false)
    private Integer price;

    public Dish(Integer restaurantId, String description, LocalDate date, Integer price) {
        this.restaurantId = restaurantId;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public Dish(Integer id, Integer restaurantId, String description, LocalDate date, Integer price) {
        super(id);
        this.restaurantId = restaurantId;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
