package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.TimeSlot;
import edu.miu.attendance.repository.TimeSlotRepository;
import edu.miu.attendance.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    TimeSlotRepository timeSlotDAO;
    @Override
    public TimeSlot addTimeSlot(TimeSlot timeSlot) {
        return timeSlotDAO.save(timeSlot);
    }
}
