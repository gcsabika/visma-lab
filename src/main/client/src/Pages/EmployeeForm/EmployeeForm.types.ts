export interface Employee {
    firstName: string,
    lastName: string,
    salary: number,
}

export type EmployeeError = {
    [key in keyof Employee]?: string
}

export const blankEmployee: Employee= {
    firstName: '',
    lastName: '',
    salary: 0
}
