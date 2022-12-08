package com.rent_a_car.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rent;

    @ManyToOne
    @JoinColumn(name = "username")
    private Username username;


    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;
    private String date_rent;

    public Rent() {
    }

    public Rent(Long id_rent, Username username, Car car, String date_rent) {
        this.id_rent = id_rent;
        this.username = username;
        this.car = car;
        this.date_rent = date_rent;
    }

    public Long getId_rent() {
        return id_rent;
    }

    public void setId_rent(Long id_rent) {
        this.id_rent = id_rent;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDate_rent() {
        return date_rent;
    }

    public void setDate_rent(String date_rent) {
        this.date_rent = date_rent;
    }
}
