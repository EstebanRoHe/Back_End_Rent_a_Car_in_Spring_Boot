package com.rent_a_car;

import com.rent_a_car.Model.ERole;
import com.rent_a_car.Model.RoleEntity;
import com.rent_a_car.Model.UserEntity;
import com.rent_a_car.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class RentACarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
    }
        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        UserRepository userRepository;

        @Bean
        CommandLineRunner init(){
            return args -> {
                UserEntity userEntity = UserEntity.builder()
                        .name("esteban")
                        .lastName("rojas")
                        .username("esteban")
                        .password(passwordEncoder.encode("1234"))
                        .email("esteban@gmail.com")
                        .roles(Set.of(RoleEntity.builder()
                                .name(ERole.valueOf(ERole.ADMIN.name()))
                                .build()))
                        .build();

                UserEntity userEntity2 = UserEntity.builder()
                        .name("emmanuel")
                        .lastName("rojas")
                        .username("emmanuel")
                        .password(passwordEncoder.encode("1234"))
                        .email("ema@gmail.com")
                        .roles(Set.of(RoleEntity.builder()
                                .name(ERole.valueOf(ERole.USER.name()))
                                .build()))
                        .build();

                userRepository.save(userEntity);
                userRepository.save(userEntity2);
            };



    }

}
