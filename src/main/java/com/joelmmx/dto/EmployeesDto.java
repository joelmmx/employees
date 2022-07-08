package com.joelmmx.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joelmmx.entity.Employees;
import com.joelmmx.entity.Genders;
import com.joelmmx.entity.Jobs;

public class EmployeesDto {
    
	@JsonProperty("gender_id")
    private int gender;
    
	@JsonProperty("job_id")
    private int job;
	
	private String name;
	
	@JsonProperty("last_name")
	private String lastName;
	
	private String birthdate;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
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

	public Employees getEntity() throws ParseException {
		Employees employees = new Employees();
		employees.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(this.birthdate));
		Genders genders = new Genders();
		genders.setId(this.gender);
		employees.setGenders(genders);
		Jobs jobs = new Jobs();
		jobs.setId(this.job);
		employees.setJobs(jobs);
		employees.setLastName(this.lastName);
		employees.setName(this.name);
		
		return employees;
	}
	
}
