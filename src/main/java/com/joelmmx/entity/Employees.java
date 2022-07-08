package com.joelmmx.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEES")
public class Employees {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name="GENDER_ID")
	private Genders genders;
	
	@ManyToOne
    @JoinColumn(name="JOB_ID")
	private Jobs jobs;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "BIRTHDATE")
	private Date birthdate;
	
	@OneToMany(mappedBy="employees")
	private Set<EmployeeWorkedHours> employeeWorkedHours;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Genders getGenders() {
		return genders;
	}

	public void setGenders(Genders genders) {
		this.genders = genders;
	}

	public Jobs getJobs() {
		return jobs;
	}

	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Set<EmployeeWorkedHours> getEmployeeWorkedHours() {
		return employeeWorkedHours;
	}

	public void setEmployeeWorkedHours(Set<EmployeeWorkedHours> employeeWorkedHours) {
		this.employeeWorkedHours = employeeWorkedHours;
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", genders=" + genders + ", jobs=" + jobs + ", name=" + name + ", lastName="
				+ lastName + ", birthdate=" + birthdate + ", employeeWorkedHours=" + employeeWorkedHours + "]";
	}
	
	
}
