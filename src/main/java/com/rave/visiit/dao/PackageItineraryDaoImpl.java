package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.rave.visiit.entity.Package;

import com.rave.visiit.entity.PackageItinerary;

@Repository
public class PackageItineraryDaoImpl extends AbstractDao implements
		PackageItineraryDao {

	@Override
	public PackageItinerary saveorupdate(PackageItinerary pack)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PackageItinerary pack = (PackageItinerary) session.load(PackageItinerary.class, id);
		if(pack != null) session.delete(pack);
		return true;
	}

	@Override
	public List<PackageItinerary> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageItinerary.class);
		Package pack = new Package();
		pack.setPkId(id);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));	
		c.addOrder(Order.asc("pkiDay"));
	    return c.list();
	}

	@Override
	public PackageItinerary get(Integer id) throws Exception {
		
		Session session = this.getSessionFactory().getCurrentSession();
		PackageItinerary p = (PackageItinerary) session.get(PackageItinerary.class, new Integer(id));
		
		/*Session session=getSessionFactory().getCurrentSession();
		return (PackageItinerary) session.load(PackageItinerary.class, id);*/
		return p;
	}
	
	public Boolean checkDayExists(Integer id,Integer day) throws Exception {
		
		Session session = getSessionFactory().getCurrentSession();
		Package pack = new Package();
		pack.setPkId(id);
		Criteria c = session.createCriteria(PackageItinerary.class);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		c.add(Restrictions.eq("pkiDay", day));	
		c.addOrder(Order.asc("pkiDay"));
		c.setMaxResults(1);
		if(c.uniqueResult() != null){
			return true;
		}
		return false;
	}
}
