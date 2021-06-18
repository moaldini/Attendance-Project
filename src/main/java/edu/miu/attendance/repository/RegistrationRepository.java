package edu.miu.attendance.repository;

import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.domain.Registration;
import edu.miu.attendance.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {


    List<Registration> findAllRegistrationByStudent(Student student);

    List<Registration> findAllRegistrationByCourseOffering(CourseOffering courseOffering);
}
