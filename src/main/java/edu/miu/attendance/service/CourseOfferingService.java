package edu.miu.attendance.service;

import edu.miu.attendance.domain.CourseOffering;

import java.util.List;

public interface CourseOfferingService {

    List<CourseOffering> getAllCourseOfferings();

    CourseOffering getCourseOfferingById(long id);

    CourseOffering addCourseOffering(CourseOffering courseOffering);

}
