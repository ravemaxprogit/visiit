package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Employees;

@Repository
public class EmployeeDaoImp implements EmployeeDao {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImp.class); 
	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void addEmployees(Employees e) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(e);
        logger.info("Employee saved successfully, Employee Details="+e);

	}

	@Override
	public void updateEmployees(Employees e) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(e);
        logger.info("Employee updated successfully, Employee Details="+e);	
	}

	@Override
	public List<Employees> listEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		 List<Employees> EmployeessList = session.createQuery("from Employees").list();
	        for(Employees e : EmployeessList){
	            logger.info("Employees List::"+e);
	        }
	        return EmployeessList;
	}

	@Override
	public Employees getEmployeesById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
        Employees p = (Employees) session.load(Employees.class, new Integer(id));
        logger.info("Employees loaded successfully, Employees details="+p);
        return p;
	}

	@Override
	public void removeEmployees(int id) {
		 Session session = this.sessionFactory.getCurrentSession();
	        Employees p = (Employees) session.load(Employees.class, new Integer(id));
	        if(null != p){
	            session.delete(p);
	        }
	        logger.info("Employees deleted successfully, Employees details="+p);
	    }

}
