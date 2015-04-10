package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.DepartmentDao;
import com.rave.visiit.entity.Department;


@Service
public class DepartmentServiceImpl  extends AbstractService implements DepartmentService {
	
	private DepartmentDao departmentDao;
	 
    public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao DepartmentDao) {
        this.departmentDao = DepartmentDao;
    }

	@Override
	@Transactional
	public void saveDepartment(Department d) throws Exception {
		getDepartmentDao().saveDepartment(d);
	}

	@Override
	@Transactional
	public void updateDepartment(Department d) {
		this.departmentDao.updateDepartment(d);
	}

	@Override
	@Transactional
	public List<Department> listDepartments() {
		return this.departmentDao.listDepartments();
	}

	@Override
	@Transactional
	public Department getDepartmentById(int id) {
		return this.departmentDao.getDepartmentById(id);
	}

	@Override
	@Transactional
	public int getMaxDepartmentId() {
		return this.departmentDao.getMaxDepartmentId();
	}

	@Override
	@Transactional
	public void removeDepartment(int id) {
		this.departmentDao.removeDepartment(id);
	}

	@Override
	@Transactional
	public String getCode() {
		return this.departmentDao.getCode();
	}

	@Override
	@Transactional
	public int checkExistingDepartmentName(String departmentName, Integer departmentId) {
		return this.departmentDao.checkExistingDepartmentName(departmentName, departmentId);
	}
	
	@Override
	@Transactional
	public boolean checkFieldRefInAnyWhere(Integer id){
		return this.departmentDao.checkFieldRefInAnyWhere(id);
	}
}

