package edu.miu.attendance.service;

import edu.miu.attendance.domain.BarcodeRecord;
import edu.miu.attendance.domain.BarcodeStatus;
import edu.miu.attendance.domain.Student;
import edu.miu.attendance.model.BarcodeRequest;
import edu.miu.attendance.model.BarcodeStatusRequest;

import java.util.List;

public interface BarcodeRecordService {
    BarcodeRecord findBarcodeRecordById(long id);

    BarcodeRecord addBarcodeRecord(BarcodeRequest barcodeRecord);

    List<BarcodeRecord> findAllByStudent(Student student);

    BarcodeRecord changeAttendanceStatusToAbscent(long barcodeRecordId);

    BarcodeRecord changeAttendanceStatusToPresent(long barcodeRecordId);
}
