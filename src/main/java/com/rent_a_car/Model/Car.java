package com.rent_a_car.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_car;
    private String licence_plate;
    private String description;
    private String image;
    private String cylinder_capacity;
    private int capacity;
    private String model_year;
    @OneToOne()
    @JoinColumn(name = "typeCar")
    private TypeCar typeCar;

    public Car() {
    }

    public Car(Long id_car, String licence_plate, String description, String image, String cylinder_capacity, int capacity, String model_year, TypeCar typeCar) {
        this.id_car = id_car;
        this.licence_plate = licence_plate;
        this.description = description;
        this.image = image;
        this.cylinder_capacity = cylinder_capacity;
        this.capacity = capacity;
        this.model_year = model_year;
        this.typeCar = typeCar;
    }

    public Long getId_car() {
        return id_car;
    }

    public void setId_car(Long id_car) {
        this.id_car = id_car;
    }

    public String getLicence_plate() {
        return licence_plate;
    }

    public void setLicence_plate(String licence_plate) {
        this.licence_plate = licence_plate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCylinder_capacity() {
        return cylinder_capacity;
    }

    public void setCylinder_capacity(String cylinder_capacity) {
        this.cylinder_capacity = cylinder_capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }
}
