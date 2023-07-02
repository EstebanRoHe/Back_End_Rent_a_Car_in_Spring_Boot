package com.rent_a_car.Controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDTO {
    private Long id;
    private String currentPassword;
    private String newPassword;
}


