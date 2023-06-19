package com.rent_a_car.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRent;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserEntity username;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;
    private String dateRent;

    public Rent() {
    }

    public Rent(Long idRent, UserEntity username, Car car, String dateRent) {
        this.idRent = idRent;
        this.username = username;
        this.car = car;
        this.dateRent = dateRent;
    }

    public Long getIdRent() {
        return idRent;
    }

    public void setIdRent(Long idRent) {
        this.idRent = idRent;
    }

    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDateRent() {
        return dateRent;
    }

    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }
}
