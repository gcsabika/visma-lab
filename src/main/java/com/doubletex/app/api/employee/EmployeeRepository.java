package com.doubletex.app.api.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e " +
            "where lower(concat(e.firstName, ' ', e.lastName)) like %?1%")
    Page<Employee> findEmployeeByFullNameLike(Pageable pageable, String name);
}
