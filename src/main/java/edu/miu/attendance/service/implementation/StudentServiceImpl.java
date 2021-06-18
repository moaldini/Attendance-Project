package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.*;
import edu.miu.attendance.dto.CourseDto;
import edu.miu.attendance.model.StudentRequest;
import edu.miu.attendance.repository.BarcodeRecordRepository;
import edu.miu.attendance.repository.CourseOfferingRepository;
import edu.miu.attendance.repository.RegistrationRepository;
import edu.miu.attendance.repository.StudentRepository;
import edu.miu.attendance.security.SecurityUtils;
import edu.miu.attendance.service.CourseOfferingService;
import edu.miu.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentDAO;

    @Autowired
    RegistrationRepository registrationDAO;

    @Autowired
    CourseOfferingService courseOfferingService;

    @Autowired
    BarcodeRecordRepository barcodeRecordDAO;

    @Autowired
    CourseOfferingRepository courseOfferingDAO;


    @Override
    public Student findByUsername(String username) {
        Optional<Student> student = studentDAO.findByUsername(username);
        return student.get();
    }

    @Override
    public Student registerStudent(StudentRequest student) {
        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setEmail(student.getEmail());
        newStudent.setEntry(student.getEntry());
        newStudent.setBarcode(student.getBarcode());
        newStudent.setUsername(student.getUsername());
        newStudent.setPassword(student.getPassword());
        return studentDAO.save(newStudent);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDAO.findAll();
    }

    @Override
    public Student findStudentById(long id) {
        return studentDAO.findById(id).orElseThrow();
    }

    @Override
    public void deleteStudentById(long id) {
        studentDAO.deleteById(id);
    }

    @Override
    public List<CourseDto> getAllCoursesByStudent() {
        String username = SecurityUtils.getUsername();
        Student student = findByUsername(username);

        List<CourseOffering> courses = registrationDAO.findAllRegistrationByStudent(student).stream()
                .map(registration -> registration.getCourseOffering()).collect(Collectors.toList());

        return StreamSupport.stream(courses.spliterator(), false)
                .map(CourseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingsForStudent() {
        String username = SecurityUtils.getUsername();
        Student student = findByUsername(username);
        System.out.println("studentId" + student.getId());
//        return courseOfferingDAO.getCourseOfferingForStudent(student.getId());
        return registrationDAO.findAllRegistrationByStudent(student).stream()
                .map(registration -> registration.getCourseOffering()).collect(Collectors.toList());
    }

    @Override
    public List<BarcodeRecord> getAllBarcodeRecordForStudentByCourseOffering(long courseOfferingId) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(courseOfferingId);
        String username = SecurityUtils.getUsername();
        Student student = findByUsername(username);
        List<BarcodeRecord> barcodeRecords = barcodeRecordDAO.findAllByStudent(student);


        return barcodeRecords.stream()
                .filter(barcodeRecord -> barcodeRecord.getDate().isBefore(courseOffering.getEnd_date())
                        && barcodeRecord.getDate().isAfter(courseOffering.getStart_date())).collect(Collectors.toList());
    }

    @Override
    public List<BarcodeRecord> getAllBarcodeRecordForStudent() {
        String username = SecurityUtils.getUsername();
        Student student = findByUsername(username);
        System.out.println("username123" + username);
        List<BarcodeRecord> barcodeRecords = barcodeRecordDAO.findAllByStudent(student);
        return barcodeRecords;
    }
}
