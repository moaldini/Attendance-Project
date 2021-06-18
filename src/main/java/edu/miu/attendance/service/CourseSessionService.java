package edu.miu.attendance.service;

import edu.miu.attendance.domain.CourseSession;

public interface CourseSessionService {

    CourseSession addCourseSession(CourseSession courseSession);

    CourseSession findCourseSessionById(long id);

    void deleteCourseSession(long id);
}
