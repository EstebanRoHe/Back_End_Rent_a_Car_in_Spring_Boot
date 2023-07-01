package com.rent_a_car.Repository;

import java.time.LocalDate;

public interface RentProjection {
    Long getIdCar();
    Long getIdUser();
    LocalDate getDateRent();
}

