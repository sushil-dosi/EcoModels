package com.eco.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eco.entities.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllEmployee();
	
	public void addEmployee(Employee employee);
	

}
