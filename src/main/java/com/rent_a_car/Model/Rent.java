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
//@Projection(name = "rentProjection", types = { Rent.class })
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


}
