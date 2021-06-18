package edu.miu.attendance.repository;

import edu.miu.attendance.domain.Location;
import edu.miu.attendance.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.attendance.domain.BarcodeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BarcodeRecordRepository extends JpaRepository<BarcodeRecord,Long> {

    List<BarcodeRecord> findAllByStudent(Student student);

    BarcodeRecord findFirstByStudent(Student student);


}
