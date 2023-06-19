package com.rent_a_car.Controllers;

import com.rent_a_car.Model.Rent;
import com.rent_a_car.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/Rent")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
public class RentController {
    @Autowired
    private RentRepository rentRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Rent>> findAll(){
        List<Rent> list = new ArrayList<Rent>();
        rentRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{idRent}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Rent> findById(@PathVariable Long idRent){
        if(!rentRepository.findById(idRent).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rentRepository.findById(idRent).get());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity create (@RequestBody Rent rent){
       if(rentRepository.existsByCar_IdCar(rent.getCar().getIdCar())){
            return ResponseEntity.badRequest().build();
        }

       if(rentRepository.existsByUsername_IdUser(rent.getUsername().getIdUser()) && rentRepository.existsByDateRent(rent.getDateRent()) ){
          return ResponseEntity.badRequest().build();

       }
        return ResponseEntity.ok(rentRepository.save(rent));

    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Rent> update(@RequestBody Rent rent){
        if(!rentRepository.findById(rent.getIdRent()).isPresent()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(rentRepository.save(rent));
    }

    @DeleteMapping("/{idRent}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Rent> delete(@PathVariable Long idRent){
        if(!rentRepository.findById(idRent).isPresent()){
            ResponseEntity.badRequest().build();
        }
        rentRepository.delete(rentRepository.findById(idRent).get());
        return ResponseEntity.ok().build();
    }
}
