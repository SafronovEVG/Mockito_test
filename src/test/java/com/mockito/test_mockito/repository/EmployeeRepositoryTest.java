package com.mockito.test_mockito.repository;

import com.mockito.test_mockito.domian.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EmployeeRepositoryTest {
    private Employee employee;
    private Employee employee2;
    private EmployeeRepository employeeRepository;

    public Map setEmployeeMapExpected() {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(employee.getId(), employee);
        employeeMap.put(employee2.getId(), employee2);
        return employeeMap;
    }

    @BeforeEach
    public void setUp() {
        employee = new Employee("Evgeniy", "Safronov");
        employee2 = new Employee("VAsiliy", "Stalin");

        employeeRepository = new EmployeeRepository();

        employeeRepository.add(employee);
        employeeRepository.add(employee2);
    }


   @Test
    void getAll() {
        Map<Integer, Employee> actual = employeeRepository.getAll();

        assertEquals(setEmployeeMapExpected(), actual);
    }

    @Test
    void remove() {
        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(employee.getId(), employee);
        expected.put(employee2.getId(), employee2);
        expected.remove(employee.getId());

        employeeRepository.remove(employee.getId());
        Map<Integer, Employee> actual = employeeRepository.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void find() {
        Employee actual = employeeRepository.find(employee.getId());

        assertEquals(employee,actual);
    }
}