package com.mockito.test_mockito.service.api;

import com.mockito.test_mockito.domian.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee findMaxSalaryByDepartment(Integer department);

    Employee findMinSalaryByDepartment(Integer department);

    Collection<Employee> findAllEmployeeByDepartment(Integer department);

    Integer allSumSalaryByDepartment(Integer department);

    Map<Integer, List<Employee>> getAllEmployee();
}
