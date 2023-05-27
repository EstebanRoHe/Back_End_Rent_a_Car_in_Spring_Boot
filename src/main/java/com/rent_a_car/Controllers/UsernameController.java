package com.rent_a_car.Controllers;

import com.rent_a_car.Model.TypeCar;
import com.rent_a_car.Model.Username;
import com.rent_a_car.Repository.UsernameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/User")
public class UsernameController {
    @Autowired
    private UsernameRepository usernameRepository;

    @GetMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<List<Username>> findAll(){
        List<Username> list = new ArrayList<Username>();
        usernameRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{idUser}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Username>findById(@PathVariable Long idUser){

        if(!usernameRepository.findById(idUser).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.findById(idUser).get());
    }
    @PostMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Username> create(@RequestBody Username username){

        if (usernameRepository.existsByUsername(username.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        if (usernameRepository.existsByEmail(username.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.save(username));
    }

    @DeleteMapping("/{idUser}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Username> delete(@PathVariable Long idUser){
        if(!usernameRepository.findById(idUser).isPresent()){
            ResponseEntity.badRequest().build();
        }
        usernameRepository.delete(usernameRepository.findById(idUser).get());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Username> update(@RequestBody Username user){
        if(!usernameRepository.findById(user.getIdUser()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.save(user));
    }






}
