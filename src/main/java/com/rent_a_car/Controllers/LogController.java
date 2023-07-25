package com.rent_a_car.Controllers;

import com.rent_a_car.Model.Log;
import com.rent_a_car.Model.UserEntity;
import com.rent_a_car.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/Log")
@CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
public class LogController {
    @Autowired
    private LogRepository logRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
    public ResponseEntity<List<Log>> findAll() {
        List<Log> list = new ArrayList<Log>();
        logRepository.findAll().forEach(e-> list.add(e));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    @CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
    public ResponseEntity create(@RequestBody Log log){

        return  ResponseEntity.ok(logRepository.save(log));
    }

    @GetMapping("/filtrousername")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
    public ResponseEntity<List<Log>> getLogByUsername(@RequestParam String username) {
        List<Log> logs = logRepository.findByUsuarioContainingIgnoreCase(username);
        if (!logs.isEmpty()) {
            return ResponseEntity.ok(logs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/filtrometodo")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
    public ResponseEntity<List<Log>> getLogByMetodo(@RequestParam String metodo) {
        List<Log> logs = logRepository.findByMetodoContainingIgnoreCase(metodo);
        if (!logs.isEmpty()) {
            return ResponseEntity.ok(logs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
