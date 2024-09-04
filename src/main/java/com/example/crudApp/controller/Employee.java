package com.example.crudApp.controller;

import com.example.crudApp.component.ResourceNotFoundException;
import com.example.crudApp.entity.EmployeeDetails;
import com.example.crudApp.repositry.EmployeeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobsheet")
public class Employee {
@Autowired
private EmployeeRepositry employeeRepositry;
    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeDetails>> getUsers(@RequestParam("limit") int limit) {
        if (limit < 1) {
            throw new ResourceNotFoundException("Limit must be greater than zero");
        }

        List<EmployeeDetails> users = employeeRepositry.findAll();
        return ResponseEntity.ok(users);
    }
}
