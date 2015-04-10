package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageConditions;
import com.rave.visiit.entity.PackageItinerary;

@Repository
public class PackageConditionDaoImpl extends AbstractDao implements
		PackageConditionDao {

	@Override
	public PackageConditions saveorupdate(PackageConditions pack)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}


	@Override
	public List<PackageConditions> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageConditions.class);
		Package pack = new Package();
		pack.setPkId(id);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));	
	    return c.list();
	}

	@Override
	public PackageConditions get(Integer id) throws Exception {
		
		Session session = this.getSessionFactory().getCurrentSession();
		PackageConditions p = (PackageConditions) session.get(PackageConditions.class, new Integer(id));
		
		/*Session session=getSessionFactory().getCurrentSession();
		return (PackageItinerary) session.load(PackageItinerary.class, id);*/
		return p;
	}

}
