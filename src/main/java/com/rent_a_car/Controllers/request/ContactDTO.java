package com.rent_a_car.Controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private String[] toUser;
    private String subjet;
    private String message;
}
