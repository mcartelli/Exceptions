package com.santander.exceptions.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "dni", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

}
