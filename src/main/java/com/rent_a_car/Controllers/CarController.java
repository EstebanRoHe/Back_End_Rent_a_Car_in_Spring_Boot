package com.rent_a_car.Controllers;

import com.rent_a_car.Model.Car;
import com.rent_a_car.Repository.CarRepository;
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
        if(carRepository.existsByLicencePlate(car.getLicencePlate())){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carRepository.save(car));
    }

    @GetMapping("/{idCar}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Car> findById(@PathVariable Long idCar){
        if(!carRepository.findById(idCar).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carRepository.findById(idCar).get());
    }

    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Car> update(@RequestBody Car car){
        if(!carRepository.findById(car.getIdCar()).isPresent()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{idCar}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Car> delete(@PathVariable Long idCar){
        if(!carRepository.findById(idCar).isPresent()){
            ResponseEntity.badRequest().build();
        }

        carRepository.delete(carRepository.findById(idCar).get());
        return ResponseEntity.ok().build();
    }
}
