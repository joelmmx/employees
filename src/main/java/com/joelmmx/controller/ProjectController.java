package com.joelmmx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joelmmx.dto.EmployeeWorkedHoursDto;
import com.joelmmx.dto.EmployeesDto;
import com.joelmmx.dto.EmployeesResponseDto;
import com.joelmmx.dto.GenderResponseDto;
import com.joelmmx.dto.GenericResponse;
import com.joelmmx.dto.GetEmployeesDto;
import com.joelmmx.dto.JobResponseDto;
import com.joelmmx.dto.RequestServicio4;
import com.joelmmx.dto.ResponseGetEmployees;
import com.joelmmx.dto.ResponseGetHours;
import com.joelmmx.dto.Servicio1Response;
import com.joelmmx.entity.EmployeeWorkedHours;
import com.joelmmx.entity.Employees;
import com.joelmmx.service.ServiceProject;
import com.joelmmx.util.AgeCalculator;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
public class ProjectController {
	Logger logger = LogManager.getLogger(ProjectController.class);
	
	@Autowired
	private ServiceProject serviceProject;
	
	@PostMapping("/primero")
    public ResponseEntity<?> creaEmployee(@RequestBody EmployeesDto employeesDto) throws ParseException{

        if(StringUtils.isBlank(employeesDto.getName()))
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(employeesDto.getLastName()))
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(serviceProject.existsByName(employeesDto.getName())&&serviceProject.existsByLastName(employeesDto.getLastName()))
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(AgeCalculator.getAge(new SimpleDateFormat("yyyy-MM-dd").parse(employeesDto.getBirthdate()))<18)
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(!serviceProject.existsGenderById(employeesDto.getGender()))
        	return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(!serviceProject.existsJobsById(employeesDto.getJob()))
        	return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        Employees employees = serviceProject.saveEmployees(employeesDto.getEntity());
        logger.info("employees: "+employees);
        
        Servicio1Response ok= new Servicio1Response();
        ok.setSuccess(Boolean.TRUE);
        ok.setId(employees.getId());
        return new ResponseEntity<>(ok, HttpStatus.OK);
    }
	
	@PostMapping("/segundo")
    public ResponseEntity<?> createHour(@RequestBody EmployeeWorkedHoursDto employeeWorkedHoursDto) throws ParseException{

        if(StringUtils.isBlank(employeeWorkedHoursDto.getWorkedDate()))
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(!serviceProject.existsEmployeeById(employeeWorkedHoursDto.getEmployeeId()))
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(employeeWorkedHoursDto.getWorkedHours()>20)
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(new SimpleDateFormat("yyyy-MM-dd").parse(employeeWorkedHoursDto.getWorkedDate()).after(new Date()))
        	return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        EmployeeWorkedHours employeeWorkedHours = serviceProject.saveEmployeeWorkedHours(employeeWorkedHoursDto.getEntity());
        logger.info("employeeWorkedHours: "+employeeWorkedHours);
        
        Servicio1Response ok= new Servicio1Response();
        ok.setSuccess(Boolean.TRUE);
        ok.setId(employeeWorkedHours.getId());
        return new ResponseEntity<>(ok, HttpStatus.OK);
    }
	
	@PostMapping("/tercero")
    public ResponseEntity<?> getEmployees(@RequestBody GetEmployeesDto getEmployeesDto) throws ParseException{
        
        List<Employees> employees= serviceProject.getEmployees(getEmployeesDto.getJobId());
        List<EmployeesResponseDto> result = new ArrayList<>();
        
        for (Employees employee : employees) {
        	EmployeesResponseDto employeesResponseDto = new EmployeesResponseDto();
        	employeesResponseDto.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthdate()));
        	employeesResponseDto.setLastName(employee.getLastName());
        	employeesResponseDto.setName(employee.getName());
        	employeesResponseDto.setId(employee.getId());
        	GenderResponseDto genderResponseDto = new GenderResponseDto();
        	genderResponseDto.setId(employee.getGenders().getId());
        	genderResponseDto.setName(employee.getGenders().getName());
        	employeesResponseDto.setGender(genderResponseDto);
        	JobResponseDto jobResponseDto = new JobResponseDto();
        	jobResponseDto.setId(employee.getJobs().getId());
        	jobResponseDto.setName(employee.getJobs().getName());
        	jobResponseDto.setSalary(employee.getJobs().getSalary());
        	employeesResponseDto.setJob(jobResponseDto);
        	result.add(employeesResponseDto);
		}
        
        ResponseGetEmployees ok= new ResponseGetEmployees();
        ok.setSuccess(Boolean.TRUE);
        ok.setEmployees(result);
        return new ResponseEntity<>(ok, HttpStatus.OK);
    }
	
	@PostMapping("/cuarto")
    public ResponseEntity<?> calculateHours(@RequestBody RequestServicio4 requestServicio4) throws ParseException{
		
		 if(!serviceProject.existsEmployeeById(requestServicio4.getEmployeeId()))
	            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
	        
		 if(StringUtils.isBlank(requestServicio4.getEndDate()))
	            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
		 
		 if(StringUtils.isBlank(requestServicio4.getStartDate()))
	            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(requestServicio4.getStartDate());
		Date endDate   = new SimpleDateFormat("yyyy-MM-dd").parse(requestServicio4.getEndDate());
		
		if(startDate.after(endDate))
            return new ResponseEntity<>(new GenericResponse(), HttpStatus.BAD_REQUEST);
		
		List<EmployeeWorkedHours> employeeWorkedHours = serviceProject.getListEmployedHours(requestServicio4.getEmployeeId(), startDate, endDate);
		
		Integer sum = employeeWorkedHours.stream()
				  .mapToInt(x -> x.getWorkedHours())
				  .sum();
        
        ResponseGetHours ok= new ResponseGetHours();
        ok.setSuccess(Boolean.TRUE);
        ok.setTotalWorkedHours(sum);
        return new ResponseEntity<>(ok, HttpStatus.OK);
    }

}
