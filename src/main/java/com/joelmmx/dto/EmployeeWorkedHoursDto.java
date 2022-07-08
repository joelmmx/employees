package com.joelmmx.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joelmmx.entity.EmployeeWorkedHours;
import com.joelmmx.entity.Employees;

public class EmployeeWorkedHoursDto {
	@JsonProperty("employee_id")
	private Integer employeeId;
	
	@JsonProperty("worked_hours")
	private Integer workedHours;
	
	@JsonProperty("worked_date")
	private String workedDate;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getWorkedHours() {
		return workedHours;
	}
	public void setWorkedHours(Integer workedHours) {
		this.workedHours = workedHours;
	}
	public String getWorkedDate() {
		return workedDate;
	}
	public void setWorkedDate(String workedDate) {
		this.workedDate = workedDate;
	}
	
	public EmployeeWorkedHours getEntity() throws ParseException {
		EmployeeWorkedHours employeeWorkedHours = new EmployeeWorkedHours();
		Employees employees = new Employees();
		employees.setId(this.employeeId);
		employeeWorkedHours.setEmployees(employees);
		employeeWorkedHours.setWorkedDate(new SimpleDateFormat("yyyy-MM-dd").parse(this.workedDate));
		employeeWorkedHours.setWorkedHours(this.workedHours);
		return employeeWorkedHours;
	}
}
