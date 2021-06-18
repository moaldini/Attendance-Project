package edu.miu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person{
    private String barcode;
    private String attendanceStatus;
    private LocalDate entry;

//    @OneToMany
//    private List<Registration> registrationList = new ArrayList<Registration>();


    public Student(long id, String firstName, String lastName, String email, String username, String password, Set<Role> roleList, String barcode, LocalDate entry) {
        super(id, firstName, lastName, email, username, password, roleList);
        this.barcode = barcode;
        this.entry = entry;
    }
}
