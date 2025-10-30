package com.mockito.test_mockito.service.impl;

import com.mockito.test_mockito.domian.Employee;
import com.mockito.test_mockito.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeRepository employeeRepository;
    private Employee employee;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach
    public void setUp() {
        employee = new Employee("Evgeniy", "Safronov");
        employee2 = new Employee("MIHAIL", "Ivanov");
        employee3 = new Employee("Vasiliy", "STALIN");
        employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        employeeRepository.add(employee2);
        employeeRepository.add(employee3);
    }

    @Test
    public void addEmployee() {
        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(employee.getId(), employee);
        expected.put(employee2.getId(), employee2);
        expected.put(employee3.getId(), employee3);

        Map<Integer, Employee> actual = employeeRepository.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void removeEmployee() {
        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(employee2.getId(), employee2);
        expected.put(employee3.getId(), employee3);

        employeeRepository.remove(employee.getId());
        Map<Integer, Employee> actual = employeeRepository.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void findEmployee() {
        Employee actual = employeeRepository.find(1);

        assertEquals(employee, actual);
    }
}