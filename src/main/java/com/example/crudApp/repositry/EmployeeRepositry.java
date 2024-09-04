package com.example.crudApp.repositry;

import com.example.crudApp.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositry extends JpaRepository<EmployeeDetails,Long> {
}
