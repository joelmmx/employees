package com.joelmmx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.joelmmx.dto.GenericResponse;
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
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(employeesDto.getLastName()))
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(serviceProject.existsByName(employeesDto.getName())&&serviceProject.existsByLastName(employeesDto.getLastName()))
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(AgeCalculator.getAge(new SimpleDateFormat("yyyy-MM-dd").parse(employeesDto.getBirthdate()))<18)
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(!serviceProject.existsGenderById(employeesDto.getGender()))
        	return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(!serviceProject.existsJobsById(employeesDto.getJob()))
        	return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        Employees employees = serviceProject.saveEmployees(employeesDto.getEntity());
        logger.info("employees: "+employees);
        
        Servicio1Response ok= new Servicio1Response();
        ok.setSuccess(Boolean.TRUE);
        ok.setId(employees.getId());
        return new ResponseEntity(ok, HttpStatus.OK);
    }
	
	@PostMapping("/segundo")
    public ResponseEntity<?> createHour(@RequestBody EmployeeWorkedHoursDto employeeWorkedHoursDto) throws ParseException{

        if(StringUtils.isBlank(employeeWorkedHoursDto.getWorkedDate()))
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(!serviceProject.existsEmployeeById(employeeWorkedHoursDto.getEmployeeId()))
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(employeeWorkedHoursDto.getWorkedHours()>20)
            return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        if(new SimpleDateFormat("yyyy-MM-dd").parse(employeeWorkedHoursDto.getWorkedDate()).after(new Date()))
        	return new ResponseEntity(new GenericResponse(), HttpStatus.BAD_REQUEST);
        
        EmployeeWorkedHours employeeWorkedHours = serviceProject.saveEmployeeWorkedHours(employeeWorkedHoursDto.getEntity());
        logger.info("employeeWorkedHours: "+employeeWorkedHours);
        
        Servicio1Response ok= new Servicio1Response();
        ok.setSuccess(Boolean.TRUE);
        ok.setId(employeeWorkedHours.getId());
        return new ResponseEntity(ok, HttpStatus.OK);
    }

}
