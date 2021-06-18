package edu.miu.attendance.dto;

import edu.miu.attendance.domain.Course;
import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.domain.Faculty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseDto {
    private long id;
    private String name;
    private String code;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private int capacity;
    private String facultyName;

    public CourseDto(CourseOffering course) {
        this.id = course.getId();
        this.name = course.getCourse().getName();
        this.code = course.getCourse().getCode();
        this.description = course.getCourse().getDescription();
        this.start_date = course.getStart_date();
        this.end_date = course.getEnd_date();
        this.capacity = course.getCapacity();
        this.facultyName = course.getFaculty().getFirstName()+" "+course.getFaculty().getLastName();
    }

}
