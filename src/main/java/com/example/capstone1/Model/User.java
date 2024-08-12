package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
public class User {
    @NotNull(message = "id can't be empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "userName can't be empty")
    @Size(min = 6, message = "User name has to be more then 5 char")
    @Column(columnDefinition = "varchar not null")
    private String userName;

    @NotEmpty(message = "password can't be empty")
    @Pattern(regexp = "^([a-zA-Z0-9]+[_-])*[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    @Column(columnDefinition = "varchar not null")
    private String password;

    @Email(message = "Must be email format.")
    @NotEmpty(message = "email can;t be empty")
    @Column(columnDefinition = "varchar not null")
    private String email;

    @NotEmpty(message = "role can't be empty")
    @Pattern(regexp = "(Admin|Customer)")
    @Column(columnDefinition = "varchar not null check(role=Admin or role=Customer)")
    private String role;

    @NotNull(message = "balance can't be empty")
    @Positive(message = "balance should be a positive number.")
    @Column(columnDefinition = "int not null")
    private double balance;


    public User(int id, String userName, String password, String email, String role, double balance) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.balance = balance;
    }
}
