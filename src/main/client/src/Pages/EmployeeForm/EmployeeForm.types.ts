export interface Employee {
    id: number | null,
    firstName: string,
    lastName: string,
    salary: number,
}

export type EmployeeError = {
    [key in keyof Employee]?: string
}

export const blankEmployee: Employee= {
    id: null,
    firstName: '',
    lastName: '',
    salary: 0
}
