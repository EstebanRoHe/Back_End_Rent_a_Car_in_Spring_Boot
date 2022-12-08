package com.rent_a_car.Controllers;

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

    @GetMapping("/{id_username}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Username>findById(@PathVariable Long id_username){
        if(!usernameRepository.findById(id_username).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.findById(id_username).get());
    }

    @PostMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<Username> create(@RequestBody Username username){

        return ResponseEntity.ok(usernameRepository.save(username));
    }


    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Username> update(@RequestBody Username username){
        if(!usernameRepository.findById(username.getId_username()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.save(username));
    }

    @DeleteMapping("/{id_username}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Username> delete(@PathVariable Long id_username){
        if(!usernameRepository.findById(id_username).isPresent()){
            ResponseEntity.badRequest().build();
        }
        usernameRepository.delete(usernameRepository.findById(id_username).get());
        return ResponseEntity.ok().build();
    }





}
