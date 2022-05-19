package com.doubletex.app.api.employee;

import com.doubletex.app.errors.DbtBadRequest;
import com.doubletex.app.errors.DbtNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee get(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new DbtNotFound(Employee.class, id));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void post(@RequestBody Employee employee){
         employeeRepository.save(employee);
    }

    public Employee raiseSalary(Long id, Double newSalary) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new DbtNotFound(Employee.class, id));
        validateSalaryRaise(employee, newSalary);
        DbtBadRequest.current().throwIfNecessary();
        employee.setSalary(newSalary);
        return employeeRepository.save(employee);
    }

    private void validateEmployee(Employee employee) {
        DbtBadRequest doubletexBadRequest = DbtBadRequest.current();
        if (employee.getFirstName().isEmpty())
            doubletexBadRequest.addValidation("firstName", "Should not be empty");
        if (employee.getLastName().isEmpty())
            doubletexBadRequest.addValidation("firstName", "Should not be empty");
    }

    public void validateSalaryRaise(Employee employee, Double newSalary) {
        DbtBadRequest doubletexBadRequest = DbtBadRequest.current();
        if(employee.getSalary() > newSalary) {
            doubletexBadRequest.addValidation("salary", "Should be a raise");
        }
    }

}
