import {Button, TextField} from "@mui/material";
import {useState} from "react";
import {blankEmployee, Employee, EmployeeError} from "./EmployeeForm.types";
import './EmployeeForm.css';

export type EmployeeFormProps = {
    employee: Employee,
    setEmployee: (employee: Employee) => void;
    error: EmployeeError,
    submit: () => void;
}

export function EmployeeForm(props: EmployeeFormProps) {

    return (
        <form autoComplete={'off'} className={"employee-form__form"}>
            <h1>Employee</h1>
            <TextField
                value={props.employee.firstName}
                label={'First Name'}
                onChange={(e) => {
                    const newEmployee: Employee = {...props.employee, firstName: e.currentTarget.value}
                    props.setEmployee(newEmployee)
                }}
                error={props.error.firstName !== undefined}
                helperText={props.error.firstName}
            />

            <TextField
                value={props.employee.lastName}
                label={'Last Name'}
                onChange={(e) => {
                    const newEmployee: Employee = {...props.employee, lastName: e.currentTarget.value}
                    props.setEmployee(newEmployee)
            }}
                error={props.error.lastName !== undefined}
                helperText={props.error.lastName}
            />

            <TextField
                value={props.employee.salary}
                label={'salary'}
                onChange={(e) => {
                    const newEmployee: Employee = {...props.employee, salary: +e.currentTarget.value}
                    props.setEmployee(newEmployee)
                }}
                error={props.error.salary!==undefined}
                helperText={props.error.salary}
            />

            <Button variant={"contained"} onClick={(e)=> {
                console.log(props.submit)
            }}>
                Submit
            </Button>

        </form>
    )
}