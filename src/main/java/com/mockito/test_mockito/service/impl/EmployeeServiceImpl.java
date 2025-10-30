package com.mockito.test_mockito.service.impl;

import com.mockito.test_mockito.domian.Employee;
import com.mockito.test_mockito.exception.DepartmentNotFoundException;
import com.mockito.test_mockito.exception.IncorrectNameOrSurnameUsersException;
import com.mockito.test_mockito.repository.EmployeeRepository;
import com.mockito.test_mockito.service.api.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void setSalaryEmployee(Integer id, Integer salary) {
        employeeRepository.find(id).setSalary(salary);
    }

    @Override
    public void addEmployee(Employee employee) {
        if (!StringUtils.isAlpha(employee.getName())
                || !StringUtils.isAlpha(employee.getSurname())) {
            throw new IncorrectNameOrSurnameUsersException();
        } else
            employee.setSurname(StringUtils.capitalize(employee.getSurname()));
        employee.setName(StringUtils.capitalize(employee.getName()));
        employeeRepository.add(employee);
    }


    @Override
    public void removeEmployee(Integer id) {
        employeeRepository.remove(id);
    }

    @Override
    public Employee findEmployee(Integer id) {
        return employeeRepository.find(id);
    }

    @Override
    public void setDepartmentEmployee(Integer id, Integer department) {
        if (department < 0 || department > 5) throw new DepartmentNotFoundException();
        employeeRepository.find(id).setDepartment(department);
    }
}
