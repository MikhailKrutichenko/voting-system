package com.graduation.votingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(name="name", nullable = false)
    private String description;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Size(min = 100)
    @Column(name = "price", nullable = false)
    private Integer price;

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
