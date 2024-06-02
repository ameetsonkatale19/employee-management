package com.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;


@ExtendWith(MockitoExtension.class)
class EmployeeManagementApplicationTests {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Test
	public void testAddEmployee() {
		
		Employee employee = new Employee();
		
		employee.setName("sumit");
		employee.setAddress("karve nagar");
		employee.setSalary(BigDecimal.valueOf(45445));
		
		
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		
		
		Employee savedEmployee = employeeService.addEmployee(employee);
		
		assertNotNull(savedEmployee);
		assertNotNull(savedEmployee.getId());
	}
	
	@Test
	public void testFindAllEmployees() {
		
		Employee employee = new Employee();
	    employee.setName("Raj");
	    employee.setAddress("gandhi nagar");
	    employee.setSalary(BigDecimal.valueOf(23445));
		
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee);
		
		
		when(employeeRepository.findAll()).thenReturn(employeeList);
		
		
		List<Employee> foundEmployees = employeeService.findAllEmployees();
		
		
		assertEquals(1, foundEmployees.size());
		assertEquals(employeeList, foundEmployees);
		
	}
	
    @Test
    public void testFindEmployeeById() {
        
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Raj");
        employee.setAddress("gandhi nagar");
        employee.setSalary(BigDecimal.valueOf(23445));

        
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

       
        Optional<Employee> foundEmployee = employeeService.findEmployeeById(1);

       
        assertTrue(foundEmployee.isPresent());
        assertEquals(employee, foundEmployee.get());
    }

    @Test
    public void testUpdateEmployee() {
       
        Employee employee = new Employee();
        employee.setId(3);
        employee.setName("Raj");
        employee.setAddress("gandhi nagar");
        employee.setSalary(BigDecimal.valueOf(23445));

        
        Employee result = employeeService.updateEmployee(3, employee); // Pass the correct ID
        
   
        assertNotNull(result);
        assertEquals(employee, result);
    }

    @Test
    public void testDeleteEmployee() {
       
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Raj");
        employee.setAddress("gandhi nagar");
        employee.setSalary(BigDecimal.valueOf(23445));

        
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        
        boolean result = employeeService.deleteEmployee(1);

        
        verify(employeeRepository, times(1)).findById(1);

        
        verify(employeeRepository, times(1)).deleteById(1);

        
        assertTrue(result, "Expected employee to be deleted successfully");

        
        boolean nonExistentResult = employeeService.deleteEmployee(2);

        
        verify(employeeRepository, times(1)).findById(2);

        
        verify(employeeRepository, never()).deleteById(2);

        
        assertFalse(nonExistentResult, "Expected employee not found");
       
    }
    

}
