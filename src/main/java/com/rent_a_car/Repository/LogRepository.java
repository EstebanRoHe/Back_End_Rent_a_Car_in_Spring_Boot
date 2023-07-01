package com.rent_a_car.Repository;

import com.rent_a_car.Model.Log;
import com.rent_a_car.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface LogRepository  extends JpaRepository<Log, Long> {
    List<Log> findByUsuarioContainingIgnoreCase (String username);
    List<Log> findByMetodoContainingIgnoreCase (String metodo);
}
