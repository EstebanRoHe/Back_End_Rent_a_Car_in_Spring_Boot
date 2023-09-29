package com.rent_a_car.Repository;

import com.rent_a_car.Model.TypeCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeCarRepository extends CrudRepository<TypeCar,Long> {
    List<TypeCar> findByDescriptionContainingIgnoreCase(String name);
}
