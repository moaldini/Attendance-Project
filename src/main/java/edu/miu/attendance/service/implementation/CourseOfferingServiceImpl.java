package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.repository.CourseOfferingRepository;
import edu.miu.attendance.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    CourseOfferingRepository courseOfferingDAO;

    @Override
    public List<CourseOffering> getAllCourseOfferings() {
        return courseOfferingDAO.findAll();
    }

    @Override
    public CourseOffering getCourseOfferingById(long id) {
        return courseOfferingDAO.findById(id).orElseThrow();
    }

    @Override
    public CourseOffering addCourseOffering(CourseOffering courseOffering) {
        return courseOfferingDAO.save(courseOffering);
    }

}
