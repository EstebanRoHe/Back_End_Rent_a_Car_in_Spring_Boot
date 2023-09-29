package com.rent_a_car.Controllers;

import com.rent_a_car.Controllers.request.CreateUserDTO;
import com.rent_a_car.Controllers.request.UpdatePasswordDTO;
import com.rent_a_car.Model.ERole;
import com.rent_a_car.Model.RoleEntity;
import com.rent_a_car.Model.UserEntity;
import com.rent_a_car.Repository.RoleRepository;
import com.rent_a_car.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
public class UsernameController {
    @Autowired
    private UserRepository usernameRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserEntity>> findAll(){
        List<UserEntity> list = new ArrayList<UserEntity>();
        usernameRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{idUser}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')" )
    public ResponseEntity<UserEntity>findById(@PathVariable Long idUser){

        if(!usernameRepository.findById(idUser).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.findById(idUser).get());
    }
    @PostMapping
    @PreAuthorize("permitAll()")
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
    public ResponseEntity<UserEntity> delete(@PathVariable Long idUser){
        if(!usernameRepository.findById(idUser).isPresent()){
            ResponseEntity.badRequest().build();
        }
        usernameRepository.delete(usernameRepository.findById(idUser).get());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')" )
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user){
        if(!usernameRepository.findById(user.getIdUser()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.save(user));
    }

    @GetMapping("/name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserEntity>> getUsersByName(@RequestParam String name) {
        List<UserEntity> users = usernameRepository.findByNameContainingIgnoreCase(name);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<String>> getAllEmails() {
        List<String> emails = usernameRepository
                              .findAll()
                              .stream()
                              .map(UserEntity::getEmail)
                              .collect(Collectors.toList());
        if (!emails.isEmpty()) {
            return ResponseEntity.ok(emails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<String>> getAllUsernames() {
        List<String> usernames = usernameRepository
                                .findAll().stream()
                                .map(UserEntity::getUsername)
                                .collect(Collectors.toList());
        if (!usernames.isEmpty()) {
            return ResponseEntity.ok(usernames);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')" )
    public ResponseEntity<UserEntity>findByIdUsername(@PathVariable String username){
        if(!usernameRepository.existsByUsername(username)){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usernameRepository.findByUsername(username).get() );
    }

    @PutMapping("/updatePassword")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')" )
    public ResponseEntity<UserEntity> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        UserEntity user = usernameRepository.findById(updatePasswordDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(updatePasswordDTO.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        user.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));
        UserEntity updatedUser = usernameRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

}


