package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rave.visiit.entity.Module;

public class ModuleDaoImpl extends AbstractDao implements ModuleDao {

	private static final Logger logger = LoggerFactory.getLogger(ModuleDaoImpl.class); 

	@Override
	public List<Module> listModule() {
		logger.debug("getting all modules");
		Session session = getSessionFactory().getCurrentSession();
		List<Module> list = null;
		if (session != null) {
			list = session.createQuery("from Module where parentId is null and isDeleted=false and modIsActive=true").list();
		}
		return list;
	}

	@Override
	public List<Module> getSubModulesForModuleId(int parentId) {
		logger.debug("getting all sub modules for parent Id"+parentId);
		Session session = getSessionFactory().getCurrentSession();
		List<Module> list = null;
		if (session != null) {
			list = session.createQuery("from Module where isDeleted=false and modIsActive=true and parentId="+parentId).list();
		}
		return list;
	}

	
	@Override
	public List<Module> getSubModulesByModuleIds(List<Integer> parentIds) {
		logger.debug("getting all sub modules for parent Id"+parentIds);
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Module.class);
		c.add(Restrictions.in("parentId", parentIds));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		c.add(Restrictions.eq("modIsActive", Boolean.TRUE));
		return c.list();
	}

	@Override
	public List<Module> getModulesByModuleIds(List<Integer> userModuleIds) {
		logger.debug("getting all modules for parent Id"+userModuleIds);
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Module.class);
		c.add(Restrictions.in("modId", userModuleIds));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		c.add(Restrictions.eq("modIsActive", Boolean.TRUE));
		return c.list();
	}
}
