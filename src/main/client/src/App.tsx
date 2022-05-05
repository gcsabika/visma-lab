import {Component, useEffect, useState} from 'react'
import logo from './logo.svg'
import './App.css'
import {useEmployeeForm} from "./Pages/EmployeeForm/useEmployeeForm";
import {EmployeeForm} from "./Pages/EmployeeForm/EmployeeForm";


function App() {

  return (
    <div className="App">
      <EmployeeForm {...useEmployeeForm()
      }></EmployeeForm>
    </div>
  )
}

export default App
