package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.Course;
import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.repository.CourseOfferingRepository;
import edu.miu.attendance.repository.CourseRepository;
import edu.miu.attendance.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseDAO;

    @Autowired
    CourseOfferingRepository courseOfferingDAO;


    @Override
    public Course findCourseById(long courseId) {
        return courseDAO.findById(courseId).get();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    @Override
    public void deleteCourseById(long courseId) {
         courseDAO.deleteById(courseId);
    }

    @Override
    public Course addCourse(Course course) {
        return  courseDAO.save(course);

    }

    @Override
    public List<CourseOffering> getCourseOfferingsOfCourse(long courseId) {
        Course course = findCourseById(courseId);
        return courseOfferingDAO.getCourseOfferingsByCourse(course);
    }
}
