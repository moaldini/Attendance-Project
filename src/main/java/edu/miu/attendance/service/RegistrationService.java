package edu.miu.attendance.service;

import edu.miu.attendance.domain.CourseOffering;
import edu.miu.attendance.domain.Registration;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface RegistrationService {

    Registration register(Registration registration);
    List<Registration> getAllRegistrationByCourseOffering(CourseOffering courseOffering);
    List<Registration> getAllRegistration();

}
