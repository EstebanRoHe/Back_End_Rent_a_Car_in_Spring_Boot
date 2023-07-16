package com.rent_a_car.Controllers;

import com.rent_a_car.Controllers.request.ContactDTO;
import com.rent_a_car.Repository.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/Email")
@PreAuthorize("permitAll()")
@CrossOrigin(origins="https://stunning-chimera-6ffc5e.netlify.app/", maxAge = 3600)
public class EmailController {
    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendMessage")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody ContactDTO contactDTO){
        System.out.println("Messaje recibido" + contactDTO);
        emailService.sendEmail(contactDTO.getToUser(), contactDTO.getSubjet(), contactDTO.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");
        return  ResponseEntity.ok(response);
    };

};
