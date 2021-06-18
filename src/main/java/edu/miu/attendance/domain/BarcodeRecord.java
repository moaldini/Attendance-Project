package edu.miu.attendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="barcode")
public class BarcodeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private BarcodeStatus status = BarcodeStatus.PRESENT;
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
    @ManyToOne(fetch = FetchType.EAGER)
    private TimeSlot timeSlot;
}
