package com.rave.visiit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.CronJobAudit;
import com.rave.visiit.entity.CronJobException;
import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.Featuredpackage;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCategory;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageLocation;
import com.rave.visiit.entity.PackageView;
import com.rave.visiit.util.CommonUtil;

@Repository
public class PackageDaoImpl extends AbstractDao implements PackageDao {

	@Override
	@Transactional
	public Package saveorupdate(Package pack) throws Exception {
		System.out.println(pack.toString());
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(pack);
		System.out.println(pack.toString());
		return pack;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Package pack = (Package) session.load(Package.class, new Integer(id));
		if (pack != null)
			session.delete(pack);
		return true;
	}

	@Override
	public List<Package> getAll() throws Exception {
		/*
		 * Session session=getSessionFactory().getCurrentSession(); return
		 * session.createCriteria(Package.class).list();
		 */

		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Package.class);
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		c.addOrder(Order.desc("pkId"));
		return c.list();
	}

	@Override
	@Transactional
	public Package get(Integer id) throws Exception {

		Session session = getSessionFactory().getCurrentSession();
		List<Package> list = null;
		Package pack = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(Package.class);
				crt.add(Restrictions.eq("pkId", id));
				list = crt.list();
				if (list != null && list.size() > 0) {
					pack = list.get(0);
				}

			}
			return pack;
		} catch (RuntimeException re) {
			throw re;
		}

		/*
		 * 
		 * 
		 * Session session=getSessionFactory().getCurrentSession(); return
		 * (Package) session.load(Package.class, id);
		 */
	}

	@Override
	public String getMaxCode() throws Exception {
		String code = "";
		Session session = getSessionFactory().getCurrentSession();
		List codeList = session.createQuery("select max(pkCode) from Package")
				.list();
		code = (String) codeList.get(0);
		return code;
	}

	@Override
	public List<Package> getFeatured() throws Exception {
		List<Featuredpackage> fpkList = null;
		List<Integer> packIds = new ArrayList<Integer>();
		Session session = getSessionFactory().getCurrentSession();
		Criteria c1 = session.createCriteria(Featuredpackage.class);
		c1.add(Restrictions.le("fpkOrder", 10));
		fpkList = c1.list();
		for (Featuredpackage featuredpackage : fpkList) {
			packIds.add(featuredpackage.getPack());
		}
		Criteria c = session.createCriteria(Package.class);
		c.add(Restrictions.in("pkId", packIds));
		return c.list();
	}

	@Override
	public List<Package> getByCategory(Category cat) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Package.class);
		// c.add(Restrictions.eq("pkIsactive", "Y"));
		c.add(Restrictions.eq("category", cat));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		c.add(Restrictions.eq("isValid", Boolean.TRUE));
		c.addOrder(Order.asc("pkModifiedOn"));
		return c.list();
	}
	
	@Override
	public List<Package> getPackByCategory(Category cat) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		List<PackageCategory> packCategories = null;
		Criteria c = session.createCriteria(PackageCategory.class);
		c.add(Restrictions.eq("cat", cat.getCatId()));
		c.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		packCategories = c.list();
		Criteria c1 = session.createCriteria(Package.class);
		if(packCategories!=null && !packCategories.isEmpty()){
			List<Integer> pkIds = new ArrayList<Integer>();
			for (PackageCategory pkc : packCategories) {
				pkIds.add(pkc.getPack());
			}
			c1.add(Restrictions.in("pkId", pkIds));
			c1.add(Restrictions.eq("isValid", Boolean.TRUE));
			c1.addOrder(Order.asc("pkModifiedOn"));
			return c1.list();
		}
		return null;
	}

	@Override
	@Transactional
	public Package getPackageByCode(String code) throws Exception {

		Session session = getSessionFactory().getCurrentSession();
		List<Package> list = null;
		Package pack = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(Package.class);
				crt.add(Restrictions.eq("pkCode", code));
				list = crt.list();
				if (list != null && list.size() > 0) {
					pack = list.get(0);
				}

			}
			return pack;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	@Transactional
	public List<Package> getAllPackagesByType(String type) throws Exception {

		Session session = getSessionFactory().getCurrentSession();
		List<Package> list = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(Package.class);
				crt.add(Restrictions.eq("pkSpecial", type));
				crt.add(Restrictions.eq("isDeleted", Boolean.FALSE));
				crt.add(Restrictions.eq("isValid", Boolean.TRUE));
				// crt.add(Restrictions.ilike("pkSpecial", type));
				list = crt.list();
				if (list != null && list.size() > 0) {
					return list;
				}

			}
			return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Package> getAllPackages() throws Exception {
		List<Package> list = null;
		Session session = getSessionFactory().getCurrentSession();
		list = session
				.createQuery(
						"from Package where isDeleted is false and isValid is true and (pkSpecial is null or pkSpecial ='') and (pkOrder is not null or pkOrder !='') group by pkOrder order by pkOrder asc")
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<PackageCost> getAllHotDealPackages(String type)
			throws Exception {
		List<PackageCost> list = null;
		Session session = getSessionFactory().getCurrentSession();
		list = session
				.createQuery(
						"from PackageCost pc join fetch pc.pack as p where p.isDeleted is false and pc.offerPrice is not null and pc.pack = p.pkId and pc.offerPrice !=0 and p.pkSpecial='"
								+ type + "'").list();
		return list;
	}

	@Override
	public Long getPackageCountByPackLoaction(Integer packageLocationId) {
		Long count = null;
		Session session = getSessionFactory().getCurrentSession();
		if (session != null) {
			Query query = session
					.createQuery("from PackageLocation where pklLocation = "
							+ packageLocationId);
			List<PackageLocation> locations = query.list();

			if (locations != null && !locations.isEmpty()) {
				Query countQuery = session
						.createQuery("select count(pkId) from Package where isDeleted is false and pkId = "
								+ locations.get(0).getPklPkId());
				count = (Long) countQuery.uniqueResult();
			}
		}
		return count;
	}

	@Override
	public Long getPackageCountByCategory(Integer categoryId) {
		Long count = null;
		Session session = getSessionFactory().getCurrentSession();
		if (session != null) {
			Query query = session
					.createQuery("from Package where isDeleted is false and pk_cat_id = "
							+ categoryId);
			List<Package> list = query.list();
			if (list != null && !list.isEmpty()) {
				count = (long) list.size();
			} else {
				count = 0l;
			}
			// count = (Long) query.uniqueResult();
		}
		return count;
	}

	public boolean getPackagevalid(Integer packageId) throws Exception {
		List list = getTableRefForField("package", "pk_id");
		boolean result = checkFieldValueReference(packageId, list);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public <T> List getTableRefForField(String tableName, String primaryKeyId) {
		Session session = getSessionFactory().getCurrentSession();
		List list = session
				.createSQLQuery(
						"select table_name, column_name from information_schema.key_column_usage"
								+ " where referenced_table_name='" + tableName
								+ "' and referenced_column_name='"
								+ primaryKeyId + "'").list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean checkFieldValueReference(Integer id, List list) {
		Session session = this.getSessionFactory().getCurrentSession();
		boolean isRefered = false;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] str = (Object[]) list.get(i);
				String tableName = (String) str[0];
				String columnName = (String) str[1];
				if (!tableName.equalsIgnoreCase("person_detail")
						&& !tableName.equalsIgnoreCase("package_attractions")
						&& !tableName.equalsIgnoreCase("traveller_detail")) {
					String qry = "select count(*) as count from " + tableName
							+ " where " + columnName + "=" + id
							+ " and is_deleted=false";
					Query query = session.createSQLQuery(qry).addScalar(
							"count", Hibernate.LONG);
					List<Long> refList = query.list();
					if (refList != null && refList.get(0) != null
							&& refList.get(0) != 0) {
						isRefered = true;
						return isRefered;
					}
				}
			}
		}
		return isRefered;
	}

	@Override
	public CronJobAudit saveCronJobAudit(CronJobAudit cronJobAudit) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(cronJobAudit);
		return cronJobAudit;
	}

	@Override
	public CronJobException saveCronJobAuditException(
			CronJobException cronJobException) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(cronJobException);
		return cronJobException;
	}

	@Override
	public List<Package> getAllPackageAuto(String packStr) {
		Session session = getSessionFactory().getCurrentSession();
		List<Package> list = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(Package.class);
				crt.add(Restrictions.eq("isDeleted", Boolean.FALSE));
				crt.add(Restrictions.eq("isValid", Boolean.TRUE));
				crt.add(Restrictions
						.like("pkName", packStr, MatchMode.ANYWHERE));
			
				if (crt.list() != null && crt.list().size() > 0) {
					list = crt.list();
				}

			}
		} catch (Exception e) {

		}
		return list;
	}
	
	@Override
	public Map<String,Integer> getPackCount() throws Exception{
		Map<String,Integer> map = new HashMap<String,Integer>();
		Session session = getSessionFactory().getCurrentSession();
		Criteria c0 = session.createCriteria(Package.class);
		c0.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		c0.add(Restrictions.eq("isValid", Boolean.TRUE));
		Integer validPack = (Integer) c0.setProjection(Projections.rowCount()).uniqueResult();
		if(validPack!=null){
			map.put("validPack", validPack);
		}
		Criteria c1 = session.createCriteria(Package.class);
		c1.add(Restrictions.eq("isDeleted", Boolean.FALSE));
		c1.add(Restrictions.eq("isValid", Boolean.FALSE));
		Integer incompletePack = (Integer) c1.setProjection(Projections.rowCount()).uniqueResult();
		if(validPack!=null){
			map.put("incompletePack", incompletePack);
		}
		Criteria c2 = session.createCriteria(Category.class);
		c2.add(Restrictions.eq("isDeleted",  Boolean.FALSE));
		Integer validCat = (Integer) c2.setProjection(Projections.rowCount()).uniqueResult();
		if(validCat!=null){
			map.put("validCat", validCat);
		}
		Criteria c3 = session.createCriteria(CustRegistration.class);
		c3.add(Restrictions.eq("isDelete",  Boolean.FALSE));
		Integer totalCustomers = (Integer) c3.setProjection(Projections.rowCount()).uniqueResult();
		if(totalCustomers!=null){
			map.put("totalCustomers", totalCustomers);
		}
		return map;
	}
	/*	@Override
	@Transactional
	public List<Package> searchPackages(String searchString) throws Exception {
	
			Session session = getSessionFactory().getCurrentSession();
			if (session != null) {
				List<Package> packs = new ArrayList<Package>();
				Set<String> pkIds = new HashSet<String>();
				Set<String> catIds = null;
				Query catQuery = session.createQuery("from Category where (catTitle like '%" +searchString +"%' or catDescription like '%" +searchString +"%') and isDeleted is false and catIsactive is true");
				if(catQuery.list() != null && !catQuery.list().isEmpty()){
					catIds = new HashSet<String>();
					List<Category> categories = catQuery.list();
					for (Category category : categories) {
						catIds.add(Integer.toString(category.getCatId()));
					}
				}
				Query incQuery = session.createQuery("from PackageInclusion where pkinDescription like '%" +searchString +"%' and is_deleted is false");
				if(incQuery.list() != null && !incQuery.list().isEmpty()){
					List<PackageInclusion> inclusions = incQuery.list();
					for (PackageInclusion packageInclusion : inclusions) {
						pkIds.add(Integer.toString(packageInclusion.getPack().getPkId()));
					}
				}
				
				Query excQuery = session.createQuery("from PackageExclusion where pkexDescription like '%" +searchString +"%' and is_deleted is false");
				if(excQuery.list() != null && !excQuery.list().isEmpty()){
					List<PackageExclusion> exclusion = excQuery.list();
					for (PackageExclusion packageExclusion : exclusion) {
						pkIds.add(Integer.toString(packageExclusion.getPack().getPkId()));
					}
				}
				String pkIdStr = null;
				String catStr = null;
				
				if(catIds != null && !catIds.isEmpty()){
					catStr = CommonUtil.getStringFromListCommaSeperatedWithQuotes(catIds);
					Query catPk = session.createQuery("from Package where category in (" +catStr+ ")");
					packs = catPk.list();
					if(packs != null && !packs.isEmpty()){
						for (Package pack : packs) {
							pkIds.add(Integer.toString(pack.getPkId()));
						}
					}
				}
				if(pkIds != null && !pkIds.isEmpty()){
					pkIdStr = CommonUtil.getStringFromListCommaSeperatedWithQuotes(pkIds);
				}
				
				Query packIdQuery = session.createQuery(populateSearchQuery(pkIdStr, searchString));
				if(packIdQuery.list() != null && !packIdQuery.list().isEmpty()){
					return packIdQuery.list();
				}
				//StringBuilder queryStr = populateSearchPackageQuery(searchString); 
				//Query query = session.createQuery("from Package where (pk_name like '%" +searchString +"%' or pk_descripton like '%" +searchString +"%') and is_deleted is false and is_valid is true");
				Query query1 = session.createQuery(populateSearchQuery(searchString));
				List<Package> list = query.list();
				if (list != null && !list.isEmpty()) {
					return list;
				} 
			}
			return null;
		}*/
	
	private StringBuilder populateSearchPackageQuery(String searchString){
		 StringBuilder query = new StringBuilder();
		  query.append("select p.pk_name from package p ");
		  query.append("inner join category c ON p.pk_cat_id = c.cat_id ");
		  query.append("inner join package_inclusion pi ON p.pk_id = pi.pkin_pk_id ");
		  query.append("inner join package_exclusion px ON p.pk_id = px.pkex_pk_id ");
		  query.append("where (px.pkex_description like '%"+searchString+"%' or pi.pkin_description like '%"+searchString+"%' or c.cat_title like '%"+searchString+"%' or ");
		  query.append("c.cat_description like '%"+searchString+"%' or p.pk_name like '%"+searchString+"%' or p.pk_descripton like '%"+searchString+"%') and ");
		  query.append("px.is_deleted is false and pi.is_deleted is false and c.is_deleted is false and p.is_deleted is false and p.is_valid is true and c.cat_is_active is true");
		  
		  return query;
	}
	
	private String populateSearchQuery(String pkStr,String searchString){
		StringBuilder query = new StringBuilder();
		if(pkStr != null && !pkStr.isEmpty()){
			query.append("from Package where (pkId in("+pkStr+") or (pkName like '%" +searchString +"%' or pkDescripton like '%" +searchString +"%')) and (isDeleted is false and isValid is true)");
		} else {
			query.append("from Package where (pkName like '%" +searchString +"%' or pkDescripton like '%" +searchString +"%') and (isDeleted is false and isValid is true)");
		}
		  return query.toString();
	}
	
	@Override
	@Transactional
	public List<PackageView> searchPackages(String searchString) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		if (session != null) {
			Query packIdQuery = session.createQuery("from PackageView where pkexDescription like '%"+searchString +"%' or pkinDescription like '%"+searchString +"%' or pkiDescription like '%"+searchString +"%'");
			if(packIdQuery.list() != null && !packIdQuery.list().isEmpty()){
				return packIdQuery.list();
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Package> searchAllPackages(String searchString) throws Exception {
	
			Session session = getSessionFactory().getCurrentSession();
			if (session != null) {
				List<Package> packs = new ArrayList<Package>();
				Set<String> pkIds = new HashSet<String>();
				Set<String> catIds = null;
				Query catQuery = session.createQuery("from Category where (catTitle like '%" +searchString +"%' or catDescription like '%" +searchString +"%') and isDeleted is false and catIsactive is true");
				if(catQuery.list() != null && !catQuery.list().isEmpty()){
					catIds = new HashSet<String>();
					List<Category> categories = catQuery.list();
					for (Category category : categories) {
						catIds.add(Integer.toString(category.getCatId()));
					}
				}
				Query incQuery = session.createQuery("from PackageInclusion where pkinDescription like '%" +searchString +"%' and is_deleted is false");
				if(incQuery.list() != null && !incQuery.list().isEmpty()){
					List<PackageInclusion> inclusions = incQuery.list();
					for (PackageInclusion packageInclusion : inclusions) {
						pkIds.add(Integer.toString(packageInclusion.getPack().getPkId()));
					}
				}
				
				Query excQuery = session.createQuery("from PackageExclusion where pkexDescription like '%" +searchString +"%' and is_deleted is false");
				if(excQuery.list() != null && !excQuery.list().isEmpty()){
					List<PackageExclusion> exclusion = excQuery.list();
					for (PackageExclusion packageExclusion : exclusion) {
						pkIds.add(Integer.toString(packageExclusion.getPack().getPkId()));
					}
				}
				String pkIdStr = null;
				String catStr = null;
				
				if(catIds != null && !catIds.isEmpty()){
					catStr = CommonUtil.getStringFromListCommaSeperatedWithQuotes(catIds);
					Query catPk = session.createQuery("from Package where category in (" +catStr+ ")");
					packs = catPk.list();
					if(packs != null && !packs.isEmpty()){
						for (Package pack : packs) {
							pkIds.add(Integer.toString(pack.getPkId()));
						}
					}
				}
				if(pkIds != null && !pkIds.isEmpty()){
					pkIdStr = CommonUtil.getStringFromListCommaSeperatedWithQuotes(pkIds);
				}
				
				Query packIdQuery = session.createQuery(populateSearchQuery(pkIdStr, searchString));
				if(packIdQuery.list() != null && !packIdQuery.list().isEmpty()){
					return packIdQuery.list();
				}
			}
			return null;
		}
		
		@Override
	@Transactional
	public List<Package> searchPackagesByName(String searchString) throws Exception {
	
			Session session = getSessionFactory().getCurrentSession();
			if (session != null) {
				Query packIdQuery = session.createQuery("from Package where pkName like '"+searchString +"%' and isDeleted is false and isValid is true");
				if(packIdQuery.list() != null && !packIdQuery.list().isEmpty()){
					return packIdQuery.list();
				}
			}
			return null;
		}
}