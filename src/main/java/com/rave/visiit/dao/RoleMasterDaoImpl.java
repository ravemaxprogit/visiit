package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.RoleMaster;

@Repository
public class RoleMasterDaoImpl extends AbstractDao implements RoleMasterDao {

	@Override
	public RoleMaster saveorupdate(RoleMaster role) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(role);
		return role;
	}

	@Override
	public boolean delete(RoleMaster role) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(role);
		return true;
	}

	@Override
	public List<RoleMaster> getAll() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(RoleMaster.class).list();
	}

	@Override
	public RoleMaster get(Integer id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		RoleMaster role = (RoleMaster) session.get(RoleMaster.class,id);
		return role;
	}
}