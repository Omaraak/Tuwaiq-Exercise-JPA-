package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @NotNull(message = "id can't be empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 4, message = "name should be between 4 and 16 char.")
    @Column(columnDefinition = "varchar not null")
    private String name;

    @NotNull(message = "price can't be empty")
    @Positive(message = "price have to be a positive value")
    @Column(columnDefinition = "double not null")
    private double price;

    @NotNull(message = "categoryID can't be empty")
    @Column(columnDefinition = "int not null")
    private int categoryID;

    @Column(columnDefinition = "DATETIME not null")
    private LocalDate boughtDate;

    public Product(int id, String name, double price, int categoryID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
    }
}
