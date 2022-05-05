import {EmployeeFormProps} from "./EmployeeForm";
import {useEffect, useState} from "react";
import {blankEmployee, Employee, EmployeeError} from "./EmployeeForm.types";

function validateFirstName(firstName: string) : string | undefined  {
    if(firstName.length == 0) {
        return "Cannot be blank"
    }
    if(firstName.includes("+")) {
        return "Cannot contain Plus"
    }
}

function validateEmployee(employee: Employee): EmployeeError {
    let error: EmployeeError = {};
    const firstName = validateFirstName(employee.firstName)
    const lastName = validateFirstName(employee.lastName)
    if (firstName) error.firstName = firstName;
    if (lastName) error.firstName = lastName;
    return error;
}

export function useEmployeeForm(): EmployeeFormProps {
    const [employee, setEmployee] = useState<Employee>(blankEmployee);
    const [error, setError] = useState<EmployeeError>({});

    useEffect(()=> {
        const newError = validateEmployee(employee);
        setError(newError);
    }, [employee])

    function submit() {
        if(Object.keys(error).length == 0) {
            window.fetch("/api/epmloyee", {
                method: "POST",
                body: JSON.stringify(employee),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(r => r.json()).then(r => console.log(r))
        }
    }

    return {
        employee,
        setEmployee,
        error,
        submit
    }
}