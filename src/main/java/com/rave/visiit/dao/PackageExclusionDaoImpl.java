package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageExclusionKey;

@Repository
public class PackageExclusionDaoImpl extends AbstractDao implements
		PackageExclusionDao {

	@Override
	public PackageExclusion saveorupdate(PackageExclusion pack)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		return pack;
	}

	@Override
	public boolean delete(PackageExclusion pack) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(pack);
		return true;
	}

	@Override
	public List<PackageExclusion> getAll() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(PackageExclusion.class).list();
	}

	@Override
	public PackageExclusion get(Integer id) throws Exception {
		/*Session session = getSessionFactory().getCurrentSession();
		return (PackageExclusion) session.load(PackageExclusion.class, id);*/
		Session session = getSessionFactory().getCurrentSession();
		return (PackageExclusion) session.get(PackageExclusion.class, id);
	}

	@Override
	public List<PackageExclusion> getAllByPackage(Integer pkId)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageExclusion.class);
		c.add(Restrictions.ne("pack", pkId));
		return c.list();
	}

	@Override
	public Integer getNextExclusionId() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		PackageExclusionKey packageExclusionKey = null;

		int key = 1;
		try {
			if (session != null) {
				Criteria c = session.createCriteria(PackageExclusion.class);
				c.addOrder(Order.desc("key"));
				c.setMaxResults(1);
				packageExclusionKey = (PackageExclusionKey) c.uniqueResult();
				if (packageExclusionKey != null) {
					key = packageExclusionKey.getKey() + 1;
				}

			}
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			throw re;
		}

		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageExclusion> getExclusionByPackage(Integer a_id) {
		
		Session session = getSessionFactory().getCurrentSession();
		List<PackageExclusion> list = null;
		try {
			if (session != null) {
				list = session.createQuery("from PackageExclusion where pkexActive is true and isDeleted is false and pkexStatus is true and pkex_pk_id ='" +a_id+"'" ).list();
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
		
		/*Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageExclusion.class);
		Package pack = new Package();
		pack.setPkId(a_id);
		c.add(Restrictions.eq("pack", a_id));
		c.add(Restrictions.like("pkexActive", Boolean.TRUE));
		return c.list();*/
	}

	@Override
	public List<PackageExclusion> getValideExclusionByPackage(Integer a_id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageExclusion.class);
		Package pack = new Package();
		pack.setPkId(a_id);
		c.add(Restrictions.eq("pack", pack));
		c.add(Restrictions.eq("pkexActive", Boolean.TRUE));
		c.add(Restrictions.eq("pkexStatus", Boolean.TRUE));
		return c.list();
	}

	@Override
	public PackageExclusionKey saveorupdateExclusionKey(
			PackageExclusionKey packageInclusionKey) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(packageInclusionKey);
		return packageInclusionKey;
	}

}
