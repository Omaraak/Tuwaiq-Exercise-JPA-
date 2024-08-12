package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {
    @NotNull(message = "id can't be empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "productID can't be empty")
    @Column(columnDefinition = "int not null")
    private int productID;

    @NotNull(message = "merchantID can't be empty")
    @Column(columnDefinition = "int not null")
    private int merchantID;

    @NotNull(message = "stock can't be empty")
    @Min(value = 10, message = "it has to be 10 at start")
    @Column(columnDefinition = "int not null")
    private int stock;
}
