package com.rent_a_car.Controllers;

import com.rent_a_car.Controllers.request.CreateUserDTO;
import com.rent_a_car.Model.ERole;
import com.rent_a_car.Model.RoleEntity;
import com.rent_a_car.Model.UserEntity;
import com.rent_a_car.Repository.RoleRepository;
import com.rent_a_car.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/User")
@AllArgsConstructor
public class UsernameController {
    @Autowired
    private UserRepository usernameRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("permitAll()")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<List<UserEntity>> findAll(){
        List<UserEntity> list = new ArrayList<UserEntity>();
        usernameRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<UserEntity>findById(@PathVariable Long idUser){

        if(!usernameRepository.findById(idUser).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.findById(idUser).get());
    }
    @PostMapping
    @PreAuthorize("permitAll()")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO){
        if (usernameRepository.existsByUsername(createUserDTO.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        if (usernameRepository.existsByEmail(createUserDTO.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        RoleEntity userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(userRole);

        UserEntity userEntity = UserEntity.builder()
                .name(createUserDTO.getName())
                .lastName(createUserDTO.getLastName())
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        usernameRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }


    @DeleteMapping("/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<UserEntity> delete(@PathVariable Long idUser){
        if(!usernameRepository.findById(idUser).isPresent()){
            ResponseEntity.badRequest().build();
        }
        usernameRepository.delete(usernameRepository.findById(idUser).get());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user){
        if(!usernameRepository.findById(user.getIdUser()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.save(user));
    }

}

/*
    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity username){

        if (usernameRepository.existsByUsername(username.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        if (usernameRepository.existsByEmail(username.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.save(username));
    }

     */

 /*
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO){
        if (usernameRepository.existsByUsername(createUserDTO.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        if (usernameRepository.existsByEmail(createUserDTO.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build()
                ).collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .name(createUserDTO.getName())
                .lastName(createUserDTO.getLastName())
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();
        usernameRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }
     */
