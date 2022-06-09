import {EmployeeFormProps} from "./EmployeeForm";
import {useEffect, useState} from "react";
import {blankEmployee, Employee, EmployeeError} from "./EmployeeForm.types";

function validateName(name: string) : string | undefined  {
    if(name.length==0){
        return "Cannot be blank"
    }
    if(name.includes("+")){
        return "Cannot contain Plus"
    }
    if(name.match("[0-9]")){
        return "Cannot contain digits"
    }
}

function validateEmployee(employee: Employee): EmployeeError {
    let error: EmployeeError = {};
    const firstName = validateName(employee.firstName);
    const lastName = validateName(employee.lastName);
    if(firstName) error.firstName = firstName;
    if(lastName) error.lastName = lastName;
    return error;
}

export function useEmployeeForm(): EmployeeFormProps {
    const [employee, setEmployee] = useState<Employee>(blankEmployee);
    const [error, setError] = useState<EmployeeError>({});

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    useEffect(() => {
        const newError = validateEmployee(employee);
        setError(newError);
    }, [employee]);

    useEffect(() => {
        load();
    }, []);

    function submit() {
        if (employee.id == null) {
            create();
        } else {
            update();
        }
    }

    async function load() {
        if (id !== null) {
            const receivedEmployee = await window.fetch("/api/employee/" + id, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(r => r.json());
            setEmployee(receivedEmployee);
        }
    }

    function create() {
        console.log(Object.keys(error).length)
        if (Object.keys(error).length == 0) {
            window.fetch("/api/employee", {
                method: "POST",
                body: JSON.stringify(employee),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(r => r.json()).then(r => console.log(r))
        }
    }

    function update() {
        if (Object.keys(error).length == 0) {
            window.fetch("/api/employee", {
                method: "PUT",
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