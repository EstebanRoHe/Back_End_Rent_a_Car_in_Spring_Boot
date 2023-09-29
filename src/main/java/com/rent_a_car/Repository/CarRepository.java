package com.rent_a_car.Repository;

import com.rent_a_car.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    boolean existsByLicencePlate (String licencePlate);
    List<Car> findByLicencePlateContainingIgnoreCase(String licencePlate);
    List<Car> findByTypeCar_DescriptionContainingIgnoreCase(String description);
}
