package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Featuredpackage;

@Repository
public class FeaturedpackageDaoImpl extends AbstractDao implements
		FeaturedpackageDao {

	@Override
	public Featuredpackage saveorupdate(Featuredpackage pack) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Featuredpackage pack = (Featuredpackage) session.load(Featuredpackage.class, id);
		if(pack != null) session.delete(pack);
		return true;
	}

	@Override
	public List<Featuredpackage> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Featuredpackage.class);
		return c.list();
	}

	@Override
	public Featuredpackage get(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Featuredpackage pack = (Featuredpackage) session.load(Featuredpackage.class, id);
		return pack;
	}

	@Override
	public Featuredpackage getByPack(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Featuredpackage.class);
		c.add(Restrictions.eq("pack", id));
		List<Featuredpackage> list = c.list();
		if(list.size()>0){
			return list.get(list.size()-1);
		}
		return null;
	}

}
