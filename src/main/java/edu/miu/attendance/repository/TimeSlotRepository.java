package edu.miu.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.miu.attendance.domain.TimeSlot;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

}
