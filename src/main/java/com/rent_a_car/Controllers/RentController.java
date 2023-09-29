package com.rent_a_car.Controllers;

import com.rent_a_car.Model.Car;
import com.rent_a_car.Model.Rent;
import com.rent_a_car.Repository.CarRepository;
import com.rent_a_car.Repository.RentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Rent")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
@CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
public class RentController {
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<List<Rent>> findAll(){
        List<Rent> list = new ArrayList<Rent>();
        rentRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{idRent}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<Rent> findById(@PathVariable Long idRent){
        if(!rentRepository.findById(idRent).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rentRepository.findById(idRent).get());
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity create(@RequestBody Rent rent) {
        try {
            Car car = rent.getCar();
            Date startDate = rent.getDateRent();
            Date endDate = rent.getDateRentFinal();

            long difference = endDate.getTime() - startDate.getTime();
            long numberOfDays = 1 + (difference/ (1000 * 60 * 60 * 24));
            int carPrice = car.getPrice();
            int rentPrice = (int) (numberOfDays * carPrice);
            rent.setRentPrice(rentPrice);

            List<Rent> rango = rentRepository.findOverlappingRents(car, startDate, endDate);
            if(rango.isEmpty()) {
                ResponseEntity.ok(carRepository.save(car));
                return ResponseEntity.ok(rentRepository.save(rent));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<Rent> update(@RequestBody Rent rent){
        if(!rentRepository.findById(rent.getIdRent()).isPresent()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(rentRepository.save(rent));
    }

    @DeleteMapping("/{idRent}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<Rent> delete(@PathVariable Long idRent){
        if(!rentRepository.findById(idRent).isPresent()){
            ResponseEntity.badRequest().build();
        }
        rentRepository.delete(rentRepository.findById(idRent).get());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/filtro")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<List<Rent>> getRentUsername(@RequestParam String username) {
        List<Rent> Rent = rentRepository.findByUsername_UsernameContainingIgnoreCase(username);
        if (!Rent.isEmpty()) {
            return ResponseEntity.ok(Rent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Rent>> getRentByUsername(@PathVariable String username) {
        List<Rent> rentList = rentRepository.findByUsername_Username(username);
        if (!rentList.isEmpty()) {
            return ResponseEntity.ok(rentList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
