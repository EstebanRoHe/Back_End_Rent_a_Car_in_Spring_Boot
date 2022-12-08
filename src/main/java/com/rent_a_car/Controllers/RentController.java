package com.rent_a_car.Controllers;

import com.rent_a_car.Model.Rent;
import com.rent_a_car.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/Rent")
public class RentController {
    @Autowired
    private RentRepository rentRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Rent>> findAll(){
        List<Rent> list = new ArrayList<Rent>();
        rentRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id_rent}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Rent> findById(@PathVariable Long id_rent){
        if(!rentRepository.findById(id_rent).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rentRepository.findById(id_rent).get());
    }

    @PostMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity create (@RequestBody Rent rent){
        return ResponseEntity.ok(rentRepository.save(rent));
    }

    @PutMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Rent> update(@RequestBody Rent rent){
        if(!rentRepository.findById(rent.getId_rent()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rentRepository.save(rent));
    }

    @DeleteMapping("/{id_rent}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Rent> delete(@PathVariable Long id_rent){
        if(!rentRepository.findById(id_rent).isPresent()){
            ResponseEntity.badRequest().build();
        }
        rentRepository.delete(rentRepository.findById(id_rent).get());
        return ResponseEntity.ok().build();
    }
}
