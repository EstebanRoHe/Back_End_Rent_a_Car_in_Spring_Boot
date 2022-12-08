package com.rent_a_car.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TypeCar")
public class TypeCar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_typeCar;
    private String description;

    public TypeCar() {
    }

    public TypeCar(Long id_typeCar, String description) {
        this.id_typeCar = id_typeCar;
        this.description = description;
    }

    public Long getId_typeCar() {
        return id_typeCar;
    }

    public void setId_typeCar(Long id_typeCar) {
        this.id_typeCar = id_typeCar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
