import {EmployeeListProps} from "./EmployeeList";
import {useEffect, useState} from "react";
import {Employee} from "../EmployeeForm/EmployeeForm.types";
import {DEFAULT_PAGE, Page} from "./Page";

export function useEmployeeList(): EmployeeListProps {
    const [pageNumber, setPageNumber] = useState<number>(0);
    const [pageSize, setPageSize] = useState<number>(5);

    const[page, setPage] = useState<Page<Employee>>(DEFAULT_PAGE);

    async function fetchData(){
        const params = new URLSearchParams();

        params.set("pageNumber", "" + pageNumber);
        params.set("pageSize", "" + pageSize);

        const response = await window.fetch("/api/employee/?" + params.toString(), {
            method: "GET",
            headers: {
                'Content-Type': 'application/json'
            }
        })

        const newPage = await response.json();

        setPage(newPage);
    }

    //you have to update this (might be last page or less element in it
    useEffect(()=> {
        fetchData();
    }, [pageNumber, pageSize]);

    return {
        page,
        setPageNumber
    };
}