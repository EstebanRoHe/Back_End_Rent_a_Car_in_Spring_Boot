package com.rent_a_car.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;
    private String licencePlate;
    private String description;
    private String image;
    private String cylinder_capacity;
    private int capacity;
    private String model_year;
    @OneToOne()
    @JoinColumn(name = "typeCar")
    private TypeCar typeCar;


}
