package edu.miu.attendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name="registeration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @ToStringExclude
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private CourseOffering courseOffering;

    public Registration(Student student) {
        this.student = student;
    }
}
