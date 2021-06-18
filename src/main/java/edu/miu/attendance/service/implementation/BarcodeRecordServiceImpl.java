package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.BarcodeRecord;
import edu.miu.attendance.domain.BarcodeStatus;
import edu.miu.attendance.domain.Location;
import edu.miu.attendance.domain.Student;
import edu.miu.attendance.model.BarcodeRequest;
import edu.miu.attendance.model.BarcodeStatusRequest;
import edu.miu.attendance.repository.BarcodeRecordRepository;
import edu.miu.attendance.repository.LocationRepository;
import edu.miu.attendance.security.SecurityUtils;
import edu.miu.attendance.service.BarcodeRecordService;
import edu.miu.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BarcodeRecordServiceImpl implements BarcodeRecordService {


    @Autowired
    BarcodeRecordRepository barcodeRecordDAO;

    @Autowired
    StudentService studentService;

    @Autowired
    LocationRepository locationDAO;

    @Override
    public BarcodeRecord findBarcodeRecordById(long id) {
        return barcodeRecordDAO.findById(id).get();
    }

    @Override
    public BarcodeRecord addBarcodeRecord(BarcodeRequest barcode) {
        String username = SecurityUtils.getUsername();
        Student student = studentService.findByUsername(username);
        List<Location> locations = locationDAO.findAll();
        System.out.println("locations" + locations);
        System.out.println("username" + username);
        System.out.println("barcodeRequest" + barcode);
        BarcodeRecord newBarcodeRecord = new BarcodeRecord();
        newBarcodeRecord.setStudent(student);
        newBarcodeRecord.setLocation(barcode.getLocation());
        newBarcodeRecord.setTimeSlot(barcode.getSlot());
        newBarcodeRecord.setDate(barcode.getDate());
        System.out.println("location" + barcode.getLocation());
        return barcodeRecordDAO.save(newBarcodeRecord);
    }

    @Override
    public List<BarcodeRecord> findAllByStudent(Student student) {
        return barcodeRecordDAO.findAllByStudent(student);
    }

    @Override
    public BarcodeRecord changeAttendanceStatusToAbscent(long barcodeRecordId) {
        BarcodeRecord barcodeRecord = findBarcodeRecordById(barcodeRecordId);
        if(barcodeRecord.getStatus() == BarcodeStatus.PRESENT){
            barcodeRecord.setStatus(BarcodeStatus.ABSCENT);
        }
        return barcodeRecordDAO.save(barcodeRecord);

    }
    @Override
    public BarcodeRecord changeAttendanceStatusToPresent(long barcodeRecordId) {
        BarcodeRecord barcodeRecord = findBarcodeRecordById(barcodeRecordId);
        if(barcodeRecord.getStatus() == BarcodeStatus.ABSCENT){
            barcodeRecord.setStatus(BarcodeStatus.PRESENT);
        }
        return barcodeRecordDAO.save(barcodeRecord);

    }


}
