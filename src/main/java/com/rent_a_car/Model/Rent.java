package com.rent_a_car.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
    @NotNull
    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @NotNull
    private Date dateRent;
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date dateRentFinal;
    private int rentPrice;
}
