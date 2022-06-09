package com.doubletex.app.api.employee;

import com.doubletex.app.errors.DbtBadRequest;
import com.doubletex.app.errors.DbtNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public void put(@RequestBody Employee employee){
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
        DbtBadRequest dbtBadRequest = DbtBadRequest.current();
        if (employee.getFirstName().isEmpty())
            dbtBadRequest.addValidation("firstName", "Should not be empty");
        if (employee.getLastName().isEmpty())
            dbtBadRequest.addValidation("lastName", "Should not be empty");
    }

    public void validateSalaryRaise(Employee employee, Double newSalary) {
        DbtBadRequest dbtBadRequest = DbtBadRequest.current();
        if(employee.getSalary() > newSalary) {
            dbtBadRequest.addValidation("salary", "Should be a raise");
        }
    }

    public Page<Employee> fetchPaginated(Integer pageNumber, Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> search(Integer pageNumber, Integer pageSize, String sortBy, String name){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return employeeRepository.findEmployeeByFullNameLike(pageable, name);
    }

}
