package com.joelmmx.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joelmmx.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees,Integer>{
	Set<Employees> findByName(String name);
	Set<Employees> findByLastName(String lastName);
}
