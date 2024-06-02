package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
@RequestMapping("/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/findAllEmployees")
	public List<Employee> getAllEmployees(){
		return employeeService.findAllEmployees();
		
	}
	
	@GetMapping("/findEmployeeById/{id}")
	public Optional<Employee> getEmployeeById (@PathVariable int id) {
		return employeeService.findEmployeeById(id);
	}
	
	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@PutMapping("updateEmployee/{id}")
	public Employee updateEmployee (@PathVariable Integer id, @RequestBody Employee updateEmployee) {
		
		return employeeService.updateEmployee(id, updateEmployee);
	}
	
	@DeleteMapping("deleteEmployee/{id}")
	public boolean deleteById (@PathVariable Integer id) {
		return employeeService.deleteEmployee(id);
	}
}
