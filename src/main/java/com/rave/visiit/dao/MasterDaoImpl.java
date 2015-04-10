package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Salutation;
@Repository
public class MasterDaoImpl implements MasterDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Salutation> getAllSalutation() {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Salutation.class);
		c.add(Restrictions.ne("active", false));
		return c.list();
	}
	
	
}
