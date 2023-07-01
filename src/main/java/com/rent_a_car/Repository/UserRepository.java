package com.rent_a_car.Repository;

import com.rent_a_car.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByNameContainingIgnoreCase(String name);



}
