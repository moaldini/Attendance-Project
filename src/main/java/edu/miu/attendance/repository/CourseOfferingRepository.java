package edu.miu.attendance.repository;

import edu.miu.attendance.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

    List<CourseOffering> getCourseOfferingsByCourse(Course course);

    List<CourseOffering> getCourseOfferingsByFaculty(Faculty faculty);

//    @Query("select CourseOffering from CourseOffering as cof join cof.registrations as reg  where reg.student.id = :id")
//    List<CourseOffering> getCourseOfferingForStudent(@Param("id") Long studentId);
}
