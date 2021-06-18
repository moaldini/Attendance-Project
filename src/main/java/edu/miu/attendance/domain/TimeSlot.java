package edu.miu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="timeSlot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "begin_time")
    private LocalTime beginTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "description")
    private String description;
}
