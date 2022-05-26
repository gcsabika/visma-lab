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
        <form autoCapitalize={'off'} className={"employee-form__form"}>
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
            }}/>

            <Button variant={"contained"} onClick={(e)=> {
                console.log(props.submit)
            }}>
                Submit
            </Button>

        </form>
    )
}