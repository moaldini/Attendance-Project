package edu.miu.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Faculty extends Person {
    private String position;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToStringExclude
    private List<CourseOffering> courseOfferingList = new ArrayList<CourseOffering>();


    public Faculty(String firstName, String lastName, String email, String username, String password, String position) {
        super(firstName, lastName, email, username, password);
        this.position = position;
    }

    void addCourseOffering(CourseOffering courseOffering) {
        courseOfferingList.add(courseOffering);
    }
}
