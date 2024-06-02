package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {


	@Autowired
	private EmployeeRepository employeeRepo;
	
	public List<Employee> findAllEmployees() {
		 return employeeRepo.findAll();
	}
	
	public Optional<Employee> findEmployeeById(int id) {
		return employeeRepo.findById(id);
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public Employee updateEmployee (int id,  Employee updateemployee) {
		
		Employee existingEmployee = employeeRepo.findById(id).orElse(null);
		
		if (existingEmployee != null) {
			
			existingEmployee.setName( updateemployee.getName());
			existingEmployee.setAddress(updateemployee.getAddress());
			existingEmployee.setSalary(updateemployee.getSalary());
			
			return employeeRepo.save(existingEmployee);
			
		}
		
		return updateemployee;
	}
	
	public boolean deleteEmployee (int id) {
		
			Optional<Employee> employee=  employeeRepo.findById(id);
			
			if (employee.isPresent()) {
				employeeRepo.deleteById(id);
				System.out.println("Employee id "+ id +" deleted successfully");
				return true;
			}
			else {
				System.out.println( "Employee id : "+ id + "Not Found"  );
				return false;
			}
		
			}
}
