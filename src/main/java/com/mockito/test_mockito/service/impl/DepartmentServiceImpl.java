package com.mockito.test_mockito.service.impl;

import com.mockito.test_mockito.domian.Employee;
import com.mockito.test_mockito.exception.DepartmentNotFoundException;
import com.mockito.test_mockito.repository.EmployeeRepository;
import com.mockito.test_mockito.service.api.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findMinSalaryByDepartment(Integer department) {
        return employeeRepository.getAll().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentNotFoundException());
    }

    @Override
    public Employee findMaxSalaryByDepartment(Integer department) {
        return employeeRepository.getAll().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentNotFoundException());
    }


    @Override
    public Collection<Employee> findAllEmployeeByDepartment(Integer department) {
        return employeeRepository.getAll().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Integer allSumSalaryByDepartment(Integer department) {
        return employeeRepository.getAll().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployee() {
        return employeeRepository.getAll().values().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }


}
