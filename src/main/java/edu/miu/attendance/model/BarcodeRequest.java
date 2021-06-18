package edu.miu.attendance.model;

import edu.miu.attendance.domain.Location;
import edu.miu.attendance.domain.TimeSlot;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BarcodeRequest {
    LocalDate date;
    Location location;
    TimeSlot slot;
}
