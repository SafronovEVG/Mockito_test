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
        Map<Integer, Employee> expected = employeeRepository.getAll();

        Map<Integer, Employee> actual = new HashMap<>();
        actual.put(employee.getId(), employee);
        actual.put(employee2.getId(), employee2);
        actual.put(employee3.getId(), employee3);

        assertEquals(expected, actual);
    }

    @Test
    public void removeEmployee() {
        Map<Integer, Employee> expected = employeeRepository.getAll();
        employeeRepository.remove(employee.getId());

        Map<Integer, Employee> actual = new HashMap<>();
        actual.put(employee2.getId(), employee2);
        actual.put(employee3.getId(), employee3);

        assertEquals(expected, actual);
    }

    @Test
    public void findEmployee() {
        Employee expected = employeeRepository.find(1);

        Employee actual = employee;

        assertEquals(expected, actual);
    }
}