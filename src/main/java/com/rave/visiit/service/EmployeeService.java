package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Employees;

public interface EmployeeService {
	
	 public void addEmployees(Employees emp);
	 public void updateEmployees(Employees emp);
	 public List<Employees> listEmployees();
	 public Employees getEmployeesById(int id);
	 public void removeEmployees(int id);

}
