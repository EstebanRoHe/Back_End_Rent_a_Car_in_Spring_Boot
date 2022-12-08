package com.rent_a_car.Controllers;

import com.rent_a_car.Model.Car;
import com.rent_a_car.Repository.CarRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/Car")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<List<Car>> findAll(){
        List<Car> list=new ArrayList<Car>();
        carRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody Car car){
        return ResponseEntity.ok(carRepository.save(car));
    }

    @GetMapping("/{id_car}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Car> findById(@PathVariable Long id_car){
        if(!carRepository.findById(id_car).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carRepository.findById(id_car).get());
    }

    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Car> update(@RequestBody Car car){
        if(!carRepository.findById(car.getId_car()).isPresent()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{id_car}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Car> delete(@PathVariable Long id_car){
        if(!carRepository.findById(id_car).isPresent()){
            ResponseEntity.badRequest().build();
        }

        carRepository.delete(carRepository.findById(id_car).get());
        return ResponseEntity.ok().build();
    }
}
