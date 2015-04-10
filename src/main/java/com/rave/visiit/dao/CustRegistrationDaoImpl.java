/**
 * 
 */
package com.rave.visiit.dao;

import java.util.List;

import javax.persistence.Temporal;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.State;

/**
 * @author admin
 *
 */
@Repository
public class CustRegistrationDaoImpl extends AbstractDao implements
		CustRegistrationDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public CustRegistration saveorupdate(CustRegistration custRegistration)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(custRegistration);
		return null;
	}

	@Override
	public CustRegistration get(String email) throws Exception {
		CustRegistration reg = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(CustRegistration.class);
		crt.add(Restrictions.eq("customerEmail", email));
		List<CustRegistration> list = crt.list();
		if (list != null && list.size() > 0) {
			reg = list.get(0);
		}
		return reg;
	}

	@Override
	public CustRegistration getByCode(String code) throws Exception {
		CustRegistration reg = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(CustRegistration.class);
		crt.add(Restrictions.eq("securityCode", code));
		List<CustRegistration> list = crt.list();
		if (list != null && list.size() > 0) {
			reg = list.get(0);
		}
		return reg;
	}

	@Override
	public CustRegistration getByPhoneNo(String phone) throws Exception {
		CustRegistration reg = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(CustRegistration.class);
		crt.add(Restrictions.eq("phone", phone));
		List<CustRegistration> list = crt.list();
		if (list != null && list.size() > 0) {
			reg = list.get(0);
		}
		return reg;
	}

	@Override
	@Transactional
	public CustRegistration getById(Integer id) {
		Session session =  this.sessionFactory.getCurrentSession();
		CustRegistration custRegistration = (CustRegistration) session.get(CustRegistration.class,id);
		return custRegistration;
	}
}