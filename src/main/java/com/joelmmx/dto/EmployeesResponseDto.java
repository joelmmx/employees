package com.joelmmx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeesResponseDto {
	
	private Integer id;
    
    private GenderResponseDto gender;
    
    private JobResponseDto job;
	
	private String name;
	
	@JsonProperty("last_name")
	private String lastName;
	
	private String birthdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GenderResponseDto getGender() {
		return gender;
	}

	public void setGender(GenderResponseDto gender) {
		this.gender = gender;
	}

	public JobResponseDto getJob() {
		return job;
	}

	public void setJob(JobResponseDto job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
}
