package edu.miu.attendance.service;

import edu.miu.attendance.domain.*;
import edu.miu.attendance.model.FacultyRequest;
import edu.miu.attendance.model.StudentRequest;

import java.util.List;


public interface FacultyService {

    Faculty findByUsername(String username);

    Faculty registerFaculty(FacultyRequest faculty);

    Faculty getFacultyById(long id);

    List<Student> getAllStudentForFaculty(long courseOfferingId);

    List<Course> findCoursesByFaculty();

    List<CourseOffering> findCourseOfferingByFaculty();

    List<BarcodeRecord> getBarcodeRecordsByCourseOfferingForFaculty(long courseOfferingId, long studentId);



}
