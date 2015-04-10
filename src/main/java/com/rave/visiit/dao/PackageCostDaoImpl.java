package com.rave.visiit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCost;

@Repository
public class PackageCostDaoImpl extends AbstractDao implements PackageCostDao {

	@Override
	public PackageCost saveorupdate(PackageCost pack) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PackageCost pack =(PackageCost) session.load(PackageCost.class, id);
		session.delete(pack);
		return true;
	}

	@Override
	public List<PackageCost> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageCost.class);
		Package pack = new Package();
		pack.setPkId(id);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
	    return c.list();
	}

	@Override
	public PackageCost get(Integer id) throws Exception {
		
		Session session = this.getSessionFactory().getCurrentSession();
		PackageCost p = (PackageCost) session.get(PackageCost.class, new Integer(id));
		
		
		/*Session session=getSessionFactory().getCurrentSession();
		return (PackageCost) session.load(PackageCost.class, id);*/
		return p;
	}

	@Override
	public List getAllIn(List<Package> packList)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageCost.class);
		c.setProjection(Projections.projectionList()
			.add(Projections.min("pkcCost"))
			.add(Projections.groupProperty("pack"),"pk"));
		c.add(Restrictions.in("pack", packList));
		c.addOrder(Order.asc("pk"));
		return c.list();
	}
	
	@Override
	public List<PackageCost> getAllPrice(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageCost.class);
		Package pack = new Package();
		pack.setPkId(id);
	
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		c.add(Restrictions.le("pkcValidFrom",new java.sql.Timestamp(new Date().getTime())));
		c.add(Restrictions.ge("pkcValidTo",new java.sql.Timestamp(new Date().getTime())));
		c.addOrder(Order.asc("pkcCost"));
	    return c.list();
	}
	
	@Override
	public List<PackageCost> getOfferPrice(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		List<PackageCost> list = null;
		list = session.createQuery("from PackageCost where isDeleted is false and pkc_pk_id = '" +id+ "' and offerPrice is not null and offerPrice !=''").list();
	    return list;
	}

	public PackageCost getminimumPrice(Integer id) throws Exception {
		
		Session session = getSessionFactory().getCurrentSession();
		Package pack = new Package();
		pack.setPkId(id);
		Criteria c = session.createCriteria(PackageCost.class);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		c.add(Restrictions.le("pkcValidFrom",new java.sql.Timestamp(new Date().getTime())));
		c.add(Restrictions.ge("pkcValidTo",new java.sql.Timestamp(new Date().getTime())));
		c.addOrder(Order.asc("pkcCost"));
		c.setMaxResults(1);
		return (PackageCost) c.uniqueResult();
	}
	
	@Override
	public PackageCost getHotDealsPrice(Integer id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Package pack = new Package();
		pack.setPkId(id);
		Criteria c = session.createCriteria(PackageCost.class);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		//c.add(Restrictions.ne("offerPrice", null));
		c.add(Restrictions.ne("offerPrice", 0));
		c.add(Restrictions.le("pkcValidFrom",new java.sql.Timestamp(new Date().getTime())));
		c.add(Restrictions.ge("pkcValidTo",new java.sql.Timestamp(new Date().getTime())));
		c.addOrder(Order.asc("pkcCost"));
		c.setMaxResults(1);
		return (PackageCost) c.uniqueResult();
		
	}

}
