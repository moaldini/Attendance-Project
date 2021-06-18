package edu.miu.attendance.controller;

import edu.miu.attendance.domain.Registration;
import edu.miu.attendance.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;


    @GetMapping("/registration")
    public List<Registration> getAllRegistration() {
        return registrationService.getAllRegistration();
    }


}
