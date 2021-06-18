package edu.miu.attendance.repository;

import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.domain.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    List<CourseSession> findAllByCourseOffering(CourseOffering courseOffering);
}
