package com.joelmmx.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joelmmx.entity.EmployeeWorkedHours;

public interface EmployeeWorkedHoursRepository extends JpaRepository<EmployeeWorkedHours,Integer>{
	
	public List<EmployeeWorkedHours> findAllByEmployees_IdAndWorkedDateGreaterThanEqualAndWorkedDateLessThanEqual(Integer id, Date start,Date end);

}
