import {Employee} from "../EmployeeForm/EmployeeForm.types";

export type Page<T> = {
    content: T[];
    totalPages: number;
    number: number;
    pageSize: number;
}

export const DEFAULT_PAGE = {
    content: [],
    totalPages: 0,
    number: 0,
    pageSize: 0
}