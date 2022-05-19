package com.doubletex.app.errors;


import com.doubletex.app.api.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
@JsonIgnoreProperties({"suppressed", "cause", "stackTrace", "localizedMessage"})
public class DbtNotFound extends RuntimeException{
    private int httpCode = HttpStatus.NOT_FOUND.value();
    private Class<?> entityClass;
    private Long id;
    private LocalDateTime time = LocalDateTime.now();

    public DbtNotFound(Class<Employee> entityClass, Long id) {
        this.entityClass = entityClass;
        this.id = id;
    }


    @Override
    public String getMessage() {
        return "An entity of types " + entityClass.getSimpleName() + " with id: " + id + " was not found!";
    }

}
