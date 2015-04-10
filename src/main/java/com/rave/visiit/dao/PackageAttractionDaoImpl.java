package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.PackageAttraction;

@Repository
public class PackageAttractionDaoImpl extends AbstractDao implements
		PackageAttractionDao {

	@Override
	public PackageAttraction saveorupdate(PackageAttraction pack)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(PackageAttraction pack) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.delete(pack);
		return true;
	}

	@Override
	public List<PackageAttraction> getAll() throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		return session.createCriteria(PackageAttraction.class).list();
	}

	@Override
	public PackageAttraction get(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		return (PackageAttraction) session.load(PackageAttraction.class, id);
	}

}
