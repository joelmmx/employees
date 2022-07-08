package com.joelmmx.dto;

import java.util.List;

public class ResponseGetEmployees extends GenericResponse {
	private List<EmployeesResponseDto> employees;

	public List<EmployeesResponseDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeesResponseDto> employees) {
		this.employees = employees;
	}
}
