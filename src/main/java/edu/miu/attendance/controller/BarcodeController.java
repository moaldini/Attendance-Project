package edu.miu.attendance.controller;

import edu.miu.attendance.service.BarcodeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_FACULTY")
public class BarcodeController {

    @Autowired
    BarcodeRecordService barcodeRecordService;

    @PatchMapping("barcodeRecords/{id}/abscent")
    public void changeAttendanceStatusToAbscent(@PathVariable long id) {
        barcodeRecordService.changeAttendanceStatusToAbscent(id);
    }

    @PatchMapping("barcodeRecords/{id}/present")
    public void changeAttendanceStatusToPresent(@PathVariable long id) {
        barcodeRecordService.changeAttendanceStatusToPresent(id);
    }
}
