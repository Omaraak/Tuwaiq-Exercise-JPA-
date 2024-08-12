package com.example.capstone1.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @NotNull(message = "id can't be empty")
    private int id;

    @NotEmpty(message = "The name can't be empty.")
    @Size(min = 4, message = "The name should be more then 3 char.")
    @Column(columnDefinition = "varchar not null")
    private String name;
}
