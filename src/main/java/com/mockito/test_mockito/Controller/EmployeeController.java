package com.mockito.test_mockito.Controller;

import com.mockito.test_mockito.domian.Employee;
import com.mockito.test_mockito.exception.DepartmentNotFoundException;
import com.mockito.test_mockito.service.api.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam String name, @RequestParam String surname) {
        Employee employee = new Employee(name, surname);
        employeeService.addEmployee(employee);
        return "Поьзователь добавлен в базу";
    }

    @GetMapping("/set-salary")
    public String setSalaryEmployee(@RequestParam Integer id, @RequestParam Integer salary) {
        employeeService.setSalaryEmployee(id, salary);
        return "Зарплата изенена";
    }

    @GetMapping("/set-department")
    public String setDepartmentEmployee(@RequestParam Integer id, @RequestParam Integer department) {
        try {
            employeeService.setDepartmentEmployee(id, department);
            return "Отдел у работника изменен";
        } catch (DepartmentNotFoundException e) {
            return "Такого отдела не существует";
        }
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam Integer id) {
        employeeService.removeEmployee(id);
        return "Работник удален";
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam Integer id) {
        return employeeService.findEmployee(id);
    }
}

