package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Department;

public interface DepartmentDao {
	
	public void saveDepartment(Department dept) throws Exception;
	public String getCode();
    public void updateDepartment(Department dept);
    public List<Department> listDepartments();
    public Department getDepartmentById(int id);
    public void removeDepartment(int id);
	public int getMaxDepartmentId();
	public int checkExistingDepartmentName(String departmentName, Integer departmentId);
	public boolean checkFieldRefInAnyWhere(Integer id);

}
