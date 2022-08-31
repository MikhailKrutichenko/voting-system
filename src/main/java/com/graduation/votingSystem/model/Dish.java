package com.graduation.votingSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
public class Dish extends AbstractBaseEntity {

    @NotNull(message = "Restaurant id must be not null.")
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @NotBlank(message = "Descriptions must be not blank.")
    @Size(min = 3, max = 30, message = "Description size min 3 max 30.")
    @Column(name = "name", nullable = false)
    private String description;

    @NotNull(message = "Date must be not null.")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull(message = "Price must be not null.")
    @Min(value = 100, message = "Min price 100.")
    @Max(value = 10000000, message = "Max price 10000000.")
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
