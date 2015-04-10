package com.rave.visiit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageLocation;
import com.rave.visiit.util.CommonUtil;

@Repository
public class PackageLocationDaoImpl extends AbstractDao implements PackageLocationDao {
	
	@Override
	public PackageLocation saveorupdate(PackageLocation pack) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PackageLocation pack =(PackageLocation) session.load(PackageLocation.class, id);
		session.delete(pack);
		return true;
	}

	@Override
	public List<PackageLocation> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageLocation.class);
		c.add(Restrictions.eq("pklPkId", id));		  
	    return c.list();
	}

	@Override
	public PackageLocation get(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		return (PackageLocation) session.load(PackageLocation.class, id);
	}

	@Override
	public List<PackageLocation> saveorupdate(List<PackageLocation> packlocations, boolean newPack, int packId)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		if(!newPack){
			List<PackageLocation> pklList= session.createCriteria(PackageLocation.class)
			.add(Restrictions.eq("pklPkId", packId)).list();
			if(pklList != null){
				for (PackageLocation packLoc : pklList) {
					session.delete(packLoc);
				}
			}
		}
		for (PackageLocation pack : packlocations) {
			session.saveOrUpdate(pack);
		}
		return packlocations;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPackageLocationByPackage(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageLocation.class);
		c.add(Restrictions.eq("pklPkId", id));
		c.add(Restrictions.ilike("pklDescription", "location"));
		if(c.list() != null && !c.list().isEmpty()){
			List<PackageLocation> packLoacations = c.list();
			List<String> locIds = new ArrayList<String>();
			for (PackageLocation packageLocation : packLoacations) {
				locIds.add(Integer.toString(packageLocation.getPklLocation()));
			}
			if(locIds != null && !locIds.isEmpty()){
				String reviewCodeStr = CommonUtil.getStringFromListCommaSeperatedWithQuotes(locIds);
				List<String> reviewCode = session.createQuery("select reviewCode from Locations where locId in("+reviewCodeStr+")").list();
				return reviewCode;
			}
		}
	    return null;
	}
	
	@Override
	public List<PackageLocation> getAllPackageLocationByPackage(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageLocation.class);
		c.add(Restrictions.eq("pklPkId", id));
		if(c.list() != null && !c.list().isEmpty()){
			return c.list();
		}
	    return null;
	}
}
