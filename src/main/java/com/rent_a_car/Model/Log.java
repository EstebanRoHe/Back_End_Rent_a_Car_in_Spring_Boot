package com.rent_a_car.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_log;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "metodo")
    private String metodo;
    @Column(name = "usuario")
    private String usuario;


}
