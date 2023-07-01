package com.rent_a_car.Repository;

import com.rent_a_car.Model.Car;
import com.rent_a_car.Model.Rent;
import com.rent_a_car.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
    boolean existsByCar_IdCar(Long id_car);
    boolean existsByUsername_IdUser(Long idUser);
    boolean existsByDateRent(String dateRent);
    List<Rent> findByUsername_UsernameContainingIgnoreCase(String username);
    List<Rent> findByUsername_Username (String username);
}
