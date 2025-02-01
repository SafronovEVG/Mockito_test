package com.mockito.test_mockito.Controller;

import com.mockito.test_mockito.domian.Employee;
import com.mockito.test_mockito.exception.DepartmentNotFoundException;
import com.mockito.test_mockito.service.api.DepartmentService;
import com.mockito.test_mockito.service.impl.DepartmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}/salary/min")
    public ResponseEntity<Employee> findMinSalaryByDepartment(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(departmentService.findMinSalaryByDepartment(id));
        } catch (DepartmentNotFoundException e) {
            throw new DepartmentNotFoundException();
        }
    }


    @GetMapping("/{id}/salary/max")
    public Employee findMaxSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.findMaxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getEmployeeByDepartment(@PathVariable Integer id) {
        return departmentService.findAllEmployeeByDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSumSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.allSumSalaryByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesSortedByDepartment() {
        return departmentService.getAllEmployee();
    }
}
