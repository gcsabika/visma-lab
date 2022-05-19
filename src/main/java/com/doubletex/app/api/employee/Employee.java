package com.doubletex.app.api.employee;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id = 0L;
    @NotNull
    @NotEmpty
    private String firstName = "";
    @NotNull
    @NotEmpty
    private String lastName = "";
    @PositiveOrZero
    private double salary = 0;
}
