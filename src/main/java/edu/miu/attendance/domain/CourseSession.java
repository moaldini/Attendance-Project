package edu.miu.attendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="session")
public class CourseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    @ManyToOne
    private CourseOffering courseOffering;

    @ManyToOne
    private TimeSlot timeSlot;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Location> locationList = new ArrayList<Location>();

    public CourseSession(LocalDate date, CourseOffering courseOffering,TimeSlot timeSlot) {
        this.date = date;
        this.courseOffering = courseOffering;
        this.timeSlot = timeSlot;
    }
}
