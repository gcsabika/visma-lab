import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import {Employee} from "../EmployeeForm/EmployeeForm.types";
import './EmployeeList.css';
import {Page} from "./Page";
import {TableToolbar} from "./toolbar/TableToolbar";

export type EmployeeListProps = {
    page: Page<Employee>
    setPageNumber(newNumber: number): void;
}

function EmployeeRow(props: {employee: Employee}) {
    return (
        <TableRow>
            <TableCell>
                <a href={"/employee/?id=" +props.employee.id}>
                    {props.employee.firstName}
                </a>
            </TableCell>
            <TableCell>{props.employee.lastName}</TableCell>
            <TableCell>{props.employee.salary}</TableCell>
        </TableRow>
    )
}

export function EmployeeList(props: EmployeeListProps) {
    return (
      <div className={"employee-list__table"}>
          <TableToolbar
              pageNumber={props.page.number}
              totalPages={props.page.totalPages}
              setPageNumber={props.setPageNumber}
          />

          <TableContainer component={Paper}>
              <Table>
                  <TableHead>
                      <TableRow>
                          <TableCell>First name</TableCell>
                          <TableCell>Last name</TableCell>
                          <TableCell>Salary</TableCell>
                      </TableRow>
                  </TableHead>
                  <TableBody>{
                      props.page.content.map((employee) => <EmployeeRow employee={employee} />)}
                  </TableBody>
              </Table>
          </TableContainer>
      </div>
    )
}