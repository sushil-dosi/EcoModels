package com.eco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eco.entities.Employee;
import com.eco.repositories.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> findAllEmployee() {
		return repository.findAll();
	}

	@Transactional
	public void addEmployee(Employee employee) {
		repository.save(employee);
	}

}
