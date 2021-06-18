package edu.miu.attendance.service;

import edu.miu.attendance.domain.Course;
import edu.miu.attendance.domain.CourseOffering;

import java.util.List;

public interface CourseService {

    Course findCourseById(long courseId);
    List<Course> getAllCourses();
    void deleteCourseById(long courseId);
    Course addCourse(Course course);

    List<CourseOffering> getCourseOfferingsOfCourse(long courseId);

}
