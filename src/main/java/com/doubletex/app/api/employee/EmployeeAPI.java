package com.doubletex.app.api.employee;

import com.doubletex.app.errors.DbtNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeAPI {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return employeeService.get(id);
    }

    @PostMapping("")
    public void post(@Valid @RequestBody Employee employee){
        employeeService.post(employee);
    }

    @PutMapping("")
    public void put(@Valid @RequestBody Employee employee){
        employeeService.put(employee);
    }

    @PutMapping("/{id}/raiseSalary")
    public Employee raiseSalary(@PathVariable Long id, @RequestParam Double newSalary) {
        return employeeService.raiseSalary(id, newSalary);
    }

    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "25") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return employeeService.fetchPaginated(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/search")
    public Page<Employee> search(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "25") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String name
    ) {
        if (name.isEmpty())
            return employeeService.fetchPaginated(pageNumber, pageSize, sortBy);
        return employeeService.search(pageNumber, pageSize, sortBy, name);
    }


}
