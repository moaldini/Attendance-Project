package edu.miu.attendance.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String barcode;
    private LocalDate entry;
}
