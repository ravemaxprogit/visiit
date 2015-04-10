package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.EmployeeDao;
import com.rave.visiit.entity.Employees;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	private EmployeeDao employeeDao;
	 
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

	@Override
	@Transactional
	public void addEmployees(Employees e) {
		this.employeeDao.addEmployees(e);
	}

	@Override
	@Transactional
	public void updateEmployees(Employees e) {
		this.employeeDao.updateEmployees(e);
	}

	@Override
	@Transactional
	public List<Employees> listEmployees() {
		return this.employeeDao.listEmployees();
	}

	@Override
	@Transactional
	public Employees getEmployeesById(int id) {
		return this.employeeDao.getEmployeesById(id);
	}

	@Override
	@Transactional
	public void removeEmployees(int id) {
		this.employeeDao.removeEmployees(id);
	}

}
