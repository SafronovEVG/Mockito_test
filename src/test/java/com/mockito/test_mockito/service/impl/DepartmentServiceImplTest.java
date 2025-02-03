package com.mockito.test_mockito.service.impl;

import com.mockito.test_mockito.domian.Employee;
import com.mockito.test_mockito.exception.DepartmentNotFoundException;
import com.mockito.test_mockito.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    private final Employee employee = new Employee("Evgeniy", "Safronov", 10000, 1);
    private final Employee employee2 = new Employee("Oleg", "Tinkoff", 20000, 1);
    private final Employee employee3 = new Employee("Galina", "Mironova", 15000, 2);


    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void getEmployeeMap() {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(employee.getId(), employee);
        employeeMap.put(employee2.getId(), employee2);
        employeeMap.put(employee3.getId(), employee3);
        when(employeeRepositoryMock.getAll()).thenReturn(employeeMap);
    }

    @Test
    public void findMinSalaryByDepartment() {

        assertEquals(departmentService.findMinSalaryByDepartment(1),
                employee);
        verify(employeeRepositoryMock, times(1)).getAll();
        verify(employeeRepositoryMock, never()).find(1);
        verify(employeeRepositoryMock, never()).remove(1);
        verify(employeeRepositoryMock, never()).add(employee);
    }

    @Test
    public void findMinSalaryByDepartmentTwo() {
        assertEquals(departmentService.findMinSalaryByDepartment(2),
                employee3);
        verify(employeeRepositoryMock, times(1)).getAll();
        verify(employeeRepositoryMock, never()).find(1);
        verify(employeeRepositoryMock, never()).remove(1);
        verify(employeeRepositoryMock, never()).add(employee);
    }

    @Test
    public void findMaxSalaryByDepartment() {
        assertEquals(departmentService.findMaxSalaryByDepartment(1),
                employee2);
        verify(employeeRepositoryMock, times(1)).getAll();
        verify(employeeRepositoryMock, never()).find(1);
        verify(employeeRepositoryMock, never()).remove(1);
        verify(employeeRepositoryMock, never()).add(employee);
    }

    @Test
    public void findAllSumSalaryByDepartment() {
        assertEquals(departmentService.allSumSalaryByDepartment(1),
                30000);
        verify(employeeRepositoryMock, times(1)).getAll();
        verify(employeeRepositoryMock, never()).find(1);
        verify(employeeRepositoryMock, never()).remove(1);
        verify(employeeRepositoryMock, never()).add(employee);
    }

    @Test
    public void findAllEmployeeByDepartment() {
        List<Employee> employeeList = List.of(employee, employee2);

        assertEquals(departmentService.findAllEmployeeByDepartment(1), employeeList);
    }

    @Test
    public void getAllEmployee() {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(employee.getId(), employee);
        employeeMap.put(employee2.getId(), employee2);
        employeeMap.put(employee3.getId(), employee3);
        Map<Integer, List<Employee>> expected = employeeMap.values().stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.groupingBy(Employee::getDepartment));

        assertEquals(expected,
                departmentService.getAllEmployee());
    }

    @Test
    public void getDepartmentNotFoundExceptionIsMinSalary() {
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.findMinSalaryByDepartment(9));
    }

    @Test
    public void detDepartmentNotFoundExceptionIsMaxSalary() {
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.findMaxSalaryByDepartment(7));
    }
}