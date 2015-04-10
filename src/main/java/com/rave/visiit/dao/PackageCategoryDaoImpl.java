package com.rave.visiit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.PackageCategory;
import com.rave.visiit.entity.Package;

@Repository
public class PackageCategoryDaoImpl extends AbstractDao implements
		PackageCategoryDao {

	@Override
	public List<PackageCategory> saveorupdate(
			List<PackageCategory> packcategory, boolean newPack, int packId)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		if(!newPack){
			@SuppressWarnings("unchecked")
			List<PackageCategory> pkcList= session.createCriteria(PackageCategory.class)
			.add(Restrictions.eq("pack", packId)).list();
			if(pkcList != null){
				for (PackageCategory packCat : pkcList) {
					session.delete(packCat);
				}
			}
		}
		for (PackageCategory pack : packcategory) {
			session.saveOrUpdate(pack);
		}
		return packcategory;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PackageCategory pack =(PackageCategory) session.load(PackageCategory.class, id);
		session.delete(pack);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageCategory> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageCategory.class);
		c.add(Restrictions.eq("pack", id));
		if(c.list() != null && !c.list().isEmpty()){
			return c.list();
		}
	    return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getValidCategories() throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria validPack= session.createCriteria(Package.class);
		validPack.setProjection(Projections.property("pkId"));
		validPack.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		validPack.add(Restrictions.eq("isValid", Boolean.TRUE));
		DetachedCriteria validCats= DetachedCriteria.forClass(PackageCategory.class);
		validCats.add(Restrictions.in("pack", validPack.list()));
		validCats.setProjection( Projections.distinct(Projections.property("cat")) );
		Criteria c = session.createCriteria(Category.class);
		c.add(Property.forName("catId").in(validCats));
	    return c.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Package> getAllPackByPackCategory(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageCategory.class);
		Criteria c1 = session.createCriteria(Package.class);
		c.add(Restrictions.eq("pack", id));
		if(c.list() != null && !c.list().isEmpty()){
			List<PackageCategory> packCategory = c.list();
			List<Integer> packs = new ArrayList<Integer>();
			for (PackageCategory packCat : packCategory) {
				packs.add(packCat.getPack());
			}
			c1.add(Restrictions.in("pack", packs));
			return c1.list();
		}
	    return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getAllCatByPackCategory(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageCategory.class);
		Criteria c1 = session.createCriteria(Category.class);
		c.add(Restrictions.eq("pack", id));
		if(c.list() != null && !c.list().isEmpty()){
			List<PackageCategory> packCategory = c.list();
			List<Integer> catlist = new ArrayList<Integer>();
			for (PackageCategory packCat : packCategory) {
				catlist.add(packCat.getCat());
			}
			c1.add(Restrictions.in("catId", catlist));
			return c1.list();
		}
	    return null;
	}

}
