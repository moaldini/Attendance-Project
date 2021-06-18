package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.*;
import edu.miu.attendance.model.FacultyRequest;
import edu.miu.attendance.repository.BarcodeRecordRepository;
import edu.miu.attendance.repository.CourseOfferingRepository;
import edu.miu.attendance.repository.FacultyRepository;
import edu.miu.attendance.repository.RegistrationRepository;
import edu.miu.attendance.security.SecurityUtils;
import edu.miu.attendance.service.CourseOfferingService;
import edu.miu.attendance.service.FacultyService;
import edu.miu.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyDAO;

    @Autowired
    CourseOfferingRepository courseOfferingDAO;

    @Autowired
    CourseOfferingService courseOfferingService;

    @Autowired
    RegistrationRepository registrationDAO;

    @Autowired
    BarcodeRecordRepository barcodeRecordDAO;

    @Autowired
    StudentService studentService;


    @Override
    public Faculty findByUsername(String username) {
        Optional<Faculty> faculty = facultyDAO.findByUsername(username);
        return faculty.get();
    }

    @Override
    public Faculty registerFaculty(FacultyRequest faculty) {
        Faculty newFaculty = new Faculty();
        newFaculty.setFirstName(faculty.getFirstName());
        newFaculty.setLastName(faculty.getLastName());
        newFaculty.setEmail(faculty.getEmail());
        newFaculty.setPosition(faculty.getPosition());
        newFaculty.setUsername(faculty.getUsername());
        newFaculty.setPassword(faculty.getPassword());
        return facultyDAO.save(newFaculty);
    }

    @Override
    public Faculty getFacultyById(long id) {
        return facultyDAO.findById(id).get();
    }

    @Override
    public List<Student> getAllStudentForFaculty(long courseOfferingId) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(courseOfferingId);
        return registrationDAO.findAllRegistrationByCourseOffering(courseOffering)
                .stream()
                .map(registration -> registration.getStudent()).collect(Collectors.toList());

    }

    @Override
    public List<Course> findCoursesByFaculty() {
        //TODO add filter six months
        String username = SecurityUtils.getUsername();
        Faculty faculty = findByUsername(username);
        return courseOfferingDAO.getCourseOfferingsByFaculty(faculty)
                .stream()
                .filter(courseOffering -> courseOffering.getStart_date().isAfter(LocalDate.now().minusMonths(6)))
                .map(courseOffering -> courseOffering.getCourse()).collect(Collectors.toList());
    }

    @Override
    public List<CourseOffering> findCourseOfferingByFaculty() {
        String username = SecurityUtils.getUsername();
        Faculty faculty = findByUsername(username);
        return courseOfferingDAO.getCourseOfferingsByFaculty(faculty);
    }

    @Override
    public List<BarcodeRecord> getBarcodeRecordsByCourseOfferingForFaculty(long courseOfferingId, long studentId) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(courseOfferingId);
        Student student = studentService.findStudentById(studentId);
        List<BarcodeRecord> barcodeRecords = barcodeRecordDAO.findAllByStudent(student);

        return barcodeRecords.stream()
                .filter(barcodeRecord -> barcodeRecord.getDate().isBefore(courseOffering.getEnd_date())
                        && barcodeRecord.getDate().isAfter(courseOffering.getStart_date())).collect(Collectors.toList());
    }


}
