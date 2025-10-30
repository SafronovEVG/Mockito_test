package com.mockito.test_mockito.service.api;

import com.mockito.test_mockito.domian.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void setSalaryEmployee(Integer id, Integer salary);

    void addEmployee(Employee employee);

    void removeEmployee(Integer id);

    Employee findEmployee(Integer id);

    void setDepartmentEmployee(Integer id, Integer department);
}
