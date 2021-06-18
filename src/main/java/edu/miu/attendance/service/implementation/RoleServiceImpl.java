package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.Faculty;
import edu.miu.attendance.domain.Role;
import edu.miu.attendance.domain.Student;
import edu.miu.attendance.repository.FacultyRepository;
import edu.miu.attendance.repository.RoleRepository;
import edu.miu.attendance.repository.StudentRepository;
import edu.miu.attendance.service.FacultyService;
import edu.miu.attendance.service.RoleService;
import edu.miu.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleDAO;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FacultyService facultyService;

    @Autowired
    FacultyRepository facultyRepository;


    @Override
    public List<Role> getAllRoles() {
        return roleDAO.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findRoleByRole(name).get();
    }

    @Override
    public Role addRole(long id,String role) {
        Role roleObj=new Role();
        roleObj.setRole(role);
        if (role.equals("ROLE_STUDENT")){
            Student student = studentService.findStudentById(id);
            roleObj.addPerson(student);
            student.addRole(roleObj);
            studentRepository.save(student);
        }else if(role.equals("ROLE_FACULTY") ||role.equals("ROLE_ADMINISTRATOR") ||role.equals("ROLE_PERSONNEL") ){
            Faculty faculty = facultyService.getFacultyById(id);
            roleObj.addPerson(faculty);
            faculty.addRole(roleObj);
            facultyRepository.save(faculty);
        }

        return roleDAO.save(roleObj);
    }


}
