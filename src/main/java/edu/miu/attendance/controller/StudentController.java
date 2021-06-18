package edu.miu.attendance.controller;

import edu.miu.attendance.domain.BarcodeRecord;
import edu.miu.attendance.domain.Course;
import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.domain.Student;
import edu.miu.attendance.dto.CourseDto;
import edu.miu.attendance.model.BarcodeRequest;
import edu.miu.attendance.model.StudentRequest;
import edu.miu.attendance.service.BarcodeRecordService;
import edu.miu.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured("ROLE_STUDENT")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    BarcodeRecordService barcodeRecordService;


    @GetMapping("students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.findStudentById(id);
    }

    @PostMapping("students")
    public Student registerStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.registerStudent(studentRequest);
    }

    @GetMapping("student/courses")
    public List<CourseDto> getCoursesForStudent() {
        return studentService.getAllCoursesByStudent();
    }

    @GetMapping("students/courseOfferings")
    public List<CourseOffering> getCourseOfferingsForStudent() {
        return studentService.getAllCourseOfferingsForStudent();
    }

    @PostMapping("students/checkinBarcode")
    public BarcodeRecord addBarcodeRecord(@RequestBody BarcodeRequest barcodeRequest) {
        return barcodeRecordService.addBarcodeRecord(barcodeRequest);
    }

    @GetMapping("students/barcodeRecords")
    public List<BarcodeRecord> getAllBarcodeRecordsOfStudent(){
        return studentService.getAllBarcodeRecordForStudent();

    }
    @GetMapping("students/courseOfferings/{courseOfferingId}/barcodeRecords")
    public List<BarcodeRecord> getAttendanceForStudent(@PathVariable long courseOfferingId) {
        return studentService.getAllBarcodeRecordForStudentByCourseOffering(courseOfferingId);

    }
}
