package edu.miu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="courseOffering")
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate start_date;
    private LocalDate end_date;
    private int capacity;

    @ToStringExclude
    @ManyToOne
    private Faculty faculty;

    @ManyToOne
    @ToStringExclude
    private Course course;

    @OneToMany(fetch = FetchType.EAGER)
    @ToStringExclude
    private List<CourseSession> courseSessions = new ArrayList<CourseSession>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Registration> registrations = new ArrayList<Registration>();

    public CourseOffering(LocalDate start_date, LocalDate end_date, int capacity, Faculty faculty, Course course) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.capacity = capacity;
        this.faculty = faculty;
        this.course = course;
    }

    public Registration register(Student student){
        Registration registration = new Registration(student);
        this.registrations.add(registration);
        return registration;
    }

    public CourseSession addSession(LocalDate date, CourseOffering courseOffering, TimeSlot timeSlot){
        CourseSession session = new CourseSession(date,courseOffering,timeSlot);
        this.courseSessions.add(session);
        return session;
    }
}
