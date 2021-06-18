package edu.miu.attendance.dto;

import edu.miu.attendance.config.JPAPersonDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UserDataDto {
    private String fullName;
    private String email;
    private String role;
    private ArrayList<Map<String,String>> ability;

    public UserDataDto(JPAPersonDetails userDetails) {
        this.fullName = userDetails.getFirstName() + " " + userDetails.getLastName();
        this.email = userDetails.getEmail();
        this.ability = new ArrayList<>();
        Map<String,String> a = new HashMap<>();

        if(userDetails.getRoles().stream().anyMatch(r -> r.getRole().equals("ROLE_ADMIN"))) {
            this.role = "ADMIN";
        }
        else if(userDetails.getRoles().stream().anyMatch(r -> r.getRole().equals("ROLE_PERSONNEL"))) {
            this.role = "PERSONNEL";
        }
        else if(userDetails.getRoles().stream().anyMatch(r -> r.getRole().equals("ROLE_STUDENT"))) {
            this.role = "STUDENT";
        }
        else if(userDetails.getRoles().stream().anyMatch(r -> r.getRole().equals("ROLE_FACULTY"))) {
            this.role = "FACULTY";
        }
        else {
            this.role = "STUDENT";
        }
        a.put("action", "manage");
        a.put("subject", "all");
        ability.add(a);
    }
}