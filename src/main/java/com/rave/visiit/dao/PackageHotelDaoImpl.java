package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageHotel;

@Repository
public class PackageHotelDaoImpl extends AbstractDao implements PackageHotelDao {

	@Override
	public PackageHotel saveorupdate(PackageHotel pack) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PackageHotel pack =(PackageHotel) session.load(PackageHotel.class, id);
		session.delete(pack);
		return true;
	}

	@Override
	public List<PackageHotel> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageHotel.class);
		Package pack = new Package();
		pack.setPkId(id);
		c.add(Restrictions.eq("pack", pack));	
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
	    return c.list();
	}

	@Override
	public PackageHotel get(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageHotel.class);
		c.add(Restrictions.eq("pkhId", id));
		if(c.list() != null && !c.list().isEmpty()){
			return (PackageHotel) c.list().get(0);
		}
		return null;
	}

}
