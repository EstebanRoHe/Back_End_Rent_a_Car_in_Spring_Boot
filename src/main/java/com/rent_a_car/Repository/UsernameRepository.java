package com.rent_a_car.Repository;

import com.rent_a_car.Model.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsernameRepository extends JpaRepository<Username,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
