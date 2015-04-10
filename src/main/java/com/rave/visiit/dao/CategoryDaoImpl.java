package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Category;


@Repository
public class CategoryDaoImpl extends AbstractDao implements CategoryDao {

	@Override
	public Category saveorupdate(Category category) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(category);
		return category;
	}

	@Override
	public boolean delete(Category category) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.delete(category);
		return true;
	}

	@Override
	public List<Category> getAll() throws Exception {
		/*Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Category.class);
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));*/
		//c.add((Criterion) Order.asc("seqId"));
		/*Session session=getSessionFactory().getCurrentSession();
		return session.createCriteria(Category.class).list();*/
		Session session = getSessionFactory().getCurrentSession();
		List<Category> list = null;
		try {
			if (session != null) {
				list = session.createQuery("from Category where is_deleted is false ORDER BY seq_id asc").list();
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Category get(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		return (Category) session.get(Category.class, id);
	}

	@Override
	public List<Category> getTop() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		List<Category> list = null;
		try {
			if (session != null) {
				list = session.createQuery("from Category where cat_is_active is true and is_deleted is false ORDER BY seq_id asc").list();
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Override
	public String getImageUrlById(Integer id) {
		Session session = getSessionFactory().getCurrentSession();
		String imageUrl = null;
			if (session != null) {
				List imageUrls = null;
				Query query = session.createQuery("select imageUrl from Category where isDeleted is false and catId = " +id);
				imageUrls = query.list();
				if(imageUrls != null && !imageUrls.isEmpty()){imageUrl = (String) imageUrls.get(0);}
			}
			return imageUrl;
	}
	
	@Override
	public String getIconUrlById(Integer id) {
		Session session = getSessionFactory().getCurrentSession();
		String iconUrl = null;
			if (session != null) {
				List iconUrls = null;
				Query query = session.createQuery("select iconUrl from Category where isDeleted is false and catId = " +id);
				iconUrls = query.list();
				if(iconUrls != null && !iconUrls.isEmpty()){iconUrl = (String) iconUrls.get(0);}
			}
			return iconUrl;
	}

}
