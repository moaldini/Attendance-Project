package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.domain.Registration;
import edu.miu.attendance.repository.RegistrationRepository;
import edu.miu.attendance.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationDAO;

    @Override
    public Registration register(Registration registration) {
        return registrationDAO.save(registration);
    }

    @Override
    public List<Registration> getAllRegistrationByCourseOffering(CourseOffering courseOffering) {
        return null;
    }

    @Override
    public List<Registration> getAllRegistration() {
        return registrationDAO.findAll();
    }

    /*
     * @Override public List<Registration>
     * getAllRegistrationByCourseOffering(CourseOffering courseOffering) {
     * 
     * }
     */
}
