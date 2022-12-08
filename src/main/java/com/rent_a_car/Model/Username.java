package com.rent_a_car.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Username")
public class Username implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_username;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public Username() {
    }

    public Username(Long id_username, String name, String lastName, String username, String password, String email) {
        this.id_username = id_username;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId_username() {
        return id_username;
    }

    public void setId_username(Long id_username) {
        this.id_username = id_username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
