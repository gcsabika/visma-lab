package com.doubletex.app.api.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeAPI {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Optional<Employee> get(@PathVariable Long id) {
        return employeeService.get(id);
    }

    @PostMapping("")
    public void post(@RequestBody Employee employee){
        employeeService.post(employee);
    }
}
