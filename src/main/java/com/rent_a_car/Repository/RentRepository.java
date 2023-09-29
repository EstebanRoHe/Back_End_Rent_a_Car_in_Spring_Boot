package com.rent_a_car.Repository;
import com.rent_a_car.Model.Car;
import com.rent_a_car.Model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
    List<Rent> findByUsername_UsernameContainingIgnoreCase(String username);
    List<Rent> findByUsername_Username (String username);
    @Query("SELECT r FROM Rent r WHERE r.car = :car " +
            "AND ( " +
            "(:startDate BETWEEN r.dateRent AND r.dateRentFinal) OR " +
            "(:endDate BETWEEN r.dateRent AND r.dateRentFinal) OR " +
            "(:startDate >= r.dateRent AND :endDate <= r.dateRentFinal) OR " +
            "(:startDate <= r.dateRent AND :endDate >= r.dateRentFinal))")

    List<Rent> findOverlappingRents(@Param("car") Car car,
                                    @Param("startDate") Date startDate,
                                    @Param("endDate") Date endDate);

   // boolean findByCar_State (Boolean carState);
  //  boolean existsByCar_IdCar(Long id_car);
  //  boolean existsByUsername_IdUser(Long idUser);
  // boolean existsByDateRent(String dateRent);

}
