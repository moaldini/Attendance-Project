package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.CourseSession;
import edu.miu.attendance.repository.CourseSessionRepository;
import edu.miu.attendance.service.CourseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CourseSessionServiceImpl implements CourseSessionService {

    @Autowired
    CourseSessionRepository courseSessionDAO;

    @Override
    public CourseSession addCourseSession(CourseSession courseSession) {
        return courseSessionDAO.save(courseSession);
    }

    @Override
    public CourseSession findCourseSessionById(long id) {
        return courseSessionDAO.findById(id).get();
    }

    @Override
    public void deleteCourseSession(long id) {
        courseSessionDAO.deleteById(id);
    }
}
