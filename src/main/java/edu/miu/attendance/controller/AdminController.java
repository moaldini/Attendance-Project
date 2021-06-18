package edu.miu.attendance.controller;

import edu.miu.attendance.domain.Role;
import edu.miu.attendance.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RoleService roleService;

    @PostMapping("/assign/{id}/{role}")
    public Role assignRole(@PathVariable long id, @PathVariable String role){
        return roleService.addRole(id,role);
    }



}
