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
        Map<Integer, Employee> expected = employeeRepository.getAll();

        Map<Integer, Employee> actual = new HashMap<>();
        actual.put(employee.getId(), employee);
        actual.put(employee2.getId(), employee2);

        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        Map<Integer, Employee> expected = employeeRepository.getAll();
        expected.remove(employee.getId());

        Map<Integer, Employee> actual = new HashMap<>();
        actual.put(employee.getId(), employee);
        actual.put(employee2.getId(), employee2);
        actual.remove(employee.getId());

        assertEquals(expected, actual);
    }

    @Test
    void find() {
        Employee expected = employeeRepository.find(employee.getId());

        Employee actual = employee;

        assertEquals(expected, actual);
    }
}