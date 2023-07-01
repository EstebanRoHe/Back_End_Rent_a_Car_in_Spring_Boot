package com.rent_a_car.Repository;

public interface IEmailService {
    void sendEmail(String[] toUser, String subject, String message);

}