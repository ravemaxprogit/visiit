package com.rave.visiit.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Vendor;
import com.rave.visiit.entity.VendorKey;

@Repository
public class VendorDaoImpl implements VendorDao {
	
private static final Logger logger = LoggerFactory.getLogger(VendorDaoImpl.class); 
	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	@Override
	@Transactional
	public void save(Vendor vendor) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(vendor);
	}

	@Override
	public List<Vendor> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Vendor> list = null;
		try {
			if (session != null) {
				list = session.createQuery("from Vendor where isDeleted is false").list();
			}
			return list;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@Override
	public Vendor get(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		Vendor vendor = (Vendor) session.get(Vendor.class, new Integer(id));
		return vendor;
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Vendor vendor = get(id);
		session.delete(vendor);
	}

	@Override
	public String generateVendorCode() {
		Session session = this.sessionFactory.getCurrentSession();
		VendorKey vendorKey = null;
		  String prefix = "VDR";
		String vendorCode = null;
		try {
			if (session != null) {
				Criteria c = session.createCriteria(VendorKey.class);
				c.addOrder(Order.desc("key"));
				c.setMaxResults(1);
				vendorKey = (VendorKey)c.uniqueResult();
				int key=1;
				if(vendorKey!=null)
				{
					key=vendorKey.getKey()+1;
				}
				vendorCode = prefix + StringUtils.leftPad("" + key,4, '0');
				
			}	
		} catch (RuntimeException re) {
//			log.error("get failed", re);
			throw re;
		}
		return vendorCode;
	}

	@Override
	@Transactional
	public VendorKey saveVendorKey(VendorKey vendorKey) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(vendorKey);
		return vendorKey;
	}
	
	@Override
	public Long getVendorCountByCountryId(Integer countryId) {
		Long count = null;
		Session session = this.sessionFactory.getCurrentSession();
			if (session != null) {
				Query query = session.createQuery("select count(viSeqId) from Vendor where isDeleted is false and viCountry = " +countryId);
				count = (Long) query.uniqueResult();
			}
			return count;
	}
	
	@Override
	public Long getVendorCountByStateId(Integer stateId) {
		Long count = null;
		Session session = this.sessionFactory.getCurrentSession();
			if (session != null) {
				Query query = session.createQuery("select count(viSeqId) from Vendor where isDeleted is false and viState = " +stateId);
				count = (Long) query.uniqueResult();
			}
			return count;
	}
	
	@Override
	public Long getVendorCountByCityId(Integer cityId) {
		Long count = null;
		Session session = this.sessionFactory.getCurrentSession();
			if (session != null) {
				Query query = session.createQuery("select count(viSeqId) from Vendor where isDeleted is false and viCity = " +cityId);
				count = (Long) query.uniqueResult();
			}
			return count;
	}

	@Override
	public boolean validateVendorRefference(Integer vendorId) throws Exception {
		List list = getTableRefForField("vendor_information", "vi_seq_id");
		return checkFieldValueReference(vendorId, list);
	}
	
	private <T> List getTableRefForField(String tableName, String primaryKeyId){
		Session session = this.sessionFactory.getCurrentSession();
		List list = session.createSQLQuery("select table_name, column_name from information_schema.key_column_usage"
				+ " where referenced_table_name='"+tableName+"' and referenced_column_name='"+primaryKeyId+"'").list();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean checkFieldValueReference(Integer id, List list) {
		Session session = this.sessionFactory.getCurrentSession();
		boolean isRefered = false;
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Object[] str = (Object[]) list.get(i);
				String tableName=(String) str[0];
				String columnName=(String) str[1];		
				String qry = "select count(*) as count from "+tableName+" where "+columnName+"="+id+" and is_deleted=false";
				Query query = session.createSQLQuery(qry).addScalar("count",Hibernate.LONG);
				List<Long> refList = query.list();
				if(refList!=null && refList.get(0)!=null && refList.get(0)!=0){
					isRefered=true;
					return isRefered;
				}
			}
		}
		return isRefered;
	}
}