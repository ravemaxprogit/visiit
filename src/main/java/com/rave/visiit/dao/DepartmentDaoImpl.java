package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Department;

@Repository
public class DepartmentDaoImpl extends AbstractDao implements DepartmentDao {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class); 
	
	@Override
	public void saveDepartment(Department d) throws Exception {
        Session session = getSessionFactory().getCurrentSession();
        session.saveOrUpdate(d);
        logger.info("Department saved successfully, Department Details="+d);
	}

	@Override
	public void updateDepartment(Department d) {
        Session session = getSessionFactory().getCurrentSession();
        session.update(d);
        logger.info("Department updated successfully, Department Details="+d);	
	}

	@Override
	public List<Department> listDepartments() {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Department.class);
		//c.add(Restrictions.eq("deptIsActive", true));
		c.add(Restrictions.eq("isDeleted", false));
		 List<Department> DepartmentssList = c.list();
        return DepartmentssList;
	}

	@Override
	public Department getDepartmentById(int id) {
		Session session = getSessionFactory().getCurrentSession();      
        Department dept = (Department) session.get(Department.class, new Integer(id));
        logger.info("Department retrieved successfully, Department details="+dept);
        return dept;
	}

	@Override
	public void removeDepartment(int id) {
		 Session session = getSessionFactory().getCurrentSession();
	        Department dept = (Department) session.load(Department.class, new Integer(id));
	        if(null != dept){
	            session.delete(dept);
	        }
	        logger.info("Departments deleted successfully, Departments details="+dept);
	    }

	@Override
	public String getCode() {
		String code = "";
		Session session = getSessionFactory().getCurrentSession();
		List codeList = session.createQuery("select max(deptCode) from Department").list();
		code = (String) codeList.get(0);
		return code;
	}

	@Override
	public int getMaxDepartmentId() {
		int maxId = 1;
		Session session = getSessionFactory().getCurrentSession();
		List<Integer> codeList = (List<Integer>) session.createQuery("select max(deptId)+1 from Department").list();
		if(null!=codeList && null!=codeList.get(0)){
			maxId = codeList.get(0);
		}
		return maxId;
	}
	
	@Override
	public int checkExistingDepartmentName(String departmentName, Integer departmentId) {
		int maxId = 0;
		List codeList = null;
		Session session = getSessionFactory().getCurrentSession();
		
		if(null==departmentId){ //for new department
			codeList = session.createQuery("select count(*) from Department where deptName='"+departmentName+"'").list();
		}else{
			 //for Existing department department
			codeList = session.createQuery("select count(*) from Department where deptName='"+departmentName+"' and deptId!='" + departmentId + "'").list();
		}
		if(null!=codeList && null!=codeList.get(0)){
			maxId = Integer.parseInt(String.valueOf(codeList.get(0)));
		}
		return maxId;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkFieldRefInAnyWhere(Integer id){
		List list = getTableRefForField("department", "dept_id");
		return checkFieldValueReference(id, list);
	}

}

