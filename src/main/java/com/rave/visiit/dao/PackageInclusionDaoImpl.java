package com.rave.visiit.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageInclusionKey;

@Repository
public class PackageInclusionDaoImpl extends AbstractDao implements
		PackageInclusionDao {

	@Override
	public PackageInclusion saveorupdate(PackageInclusion pack)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(PackageInclusion pack) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(pack);
		return true;
	}

	@Override
	public List<PackageInclusion> getAll() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(PackageInclusion.class).list();
	}

	@Override
	public PackageInclusion get(Integer id) throws Exception {
		
		/*Session session = getSessionFactory().getCurrentSession();
		return (PackageInclusion) session.load(PackageInclusion.class, id);*/
		
		Session session = getSessionFactory().getCurrentSession();
		return (PackageInclusion) session.get(PackageInclusion.class, id);
	}

	@Override
	public List<PackageInclusion> getAllByPackage(Integer pkId)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageInclusion.class);
		c.add(Restrictions.ne("pack", pkId));
		return c.list();
	}

	@Override
	public Integer getNextInclusionId() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		PackageInclusionKey packageInclusionKey = null;

		int key = 1;
		try {
			if (session != null) {
				Criteria c = session.createCriteria(PackageInclusionKey.class);
				c.addOrder(Order.desc("key"));
				c.setMaxResults(1);
				packageInclusionKey = (PackageInclusionKey) c.uniqueResult();

				if (packageInclusionKey != null) {
					key = packageInclusionKey.getKey() + 1;
				}

			}
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			throw re;
		}

		return key;
	}

	@Override
	public List<PackageInclusion> getInclusionByPackage(Integer a_id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageInclusion.class);
		Package pack = new Package();
		pack.setPkId(a_id);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("pkinActive", Boolean.TRUE));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		return c.list();
	}

	@Override
	public List<PackageInclusion> getValideInclusionByPackage(Integer a_id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageInclusion.class);
		Package pack = new Package();
		pack.setPkId(a_id);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("pkinActive", Boolean.TRUE));
		c.add(Restrictions.eq("pkinStatus", Boolean.TRUE));
		return c.list();
	}

	@Override
	@Transactional
	public PackageInclusionKey saveorupdateInclusionKey(
			PackageInclusionKey packageInclusionKey) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(packageInclusionKey);
		return packageInclusionKey;
	}

}
