package com.joelmmx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joelmmx.entity.EmployeeWorkedHours;
import com.joelmmx.entity.Employees;

public interface EmployeeWorkedHoursRepository extends JpaRepository<EmployeeWorkedHours,Integer>{

}
