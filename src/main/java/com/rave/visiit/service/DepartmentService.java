package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Department;

public interface DepartmentService {
	
	public void saveDepartment(Department dept) throws Exception;
    public void updateDepartment(Department dept);
    public String getCode();
    public List<Department> listDepartments();
    public Department getDepartmentById(int id);
    public void removeDepartment(int id);
    public int getMaxDepartmentId();
    public int checkExistingDepartmentName(String departmentName, Integer departmentId);
    boolean checkFieldRefInAnyWhere(Integer id);
}
