package edu.miu.attendance.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String position;
}
