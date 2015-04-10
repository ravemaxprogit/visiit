/**
 * 
 */
package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author admin
 *
 */
public class AbstractDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public <T> T save(final T o) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(o);
		return o;
	}
	
	public <T> boolean delete(final T o) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		try {
			session.delete(o);
			result = true;		} catch (RuntimeException re) {
			throw re;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public <T> List getTableRefForField(String tableName, String primaryKeyId){
		Session session = this.sessionFactory.getCurrentSession();
		List list = session.createSQLQuery("select table_name, column_name from information_schema.key_column_usage"
				+ " where referenced_table_name='"+tableName+"' and referenced_column_name='"+primaryKeyId+"'").list();
		System.out.println(list);
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean checkFieldValueReference(Integer id, List list) {
		Session session = this.sessionFactory.getCurrentSession();
		boolean isRefered = false;
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Object[] str = (Object[]) list.get(i);
				String tableName=(String) str[0];
				String columnName=(String) str[1];		
				System.out.println("tableName :::"+tableName+" columnName:::::"+columnName);
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
