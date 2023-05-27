package com.rent_a_car.Repository;

import com.rent_a_car.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    boolean existsByLicencePlate (String licencePlate);
}
