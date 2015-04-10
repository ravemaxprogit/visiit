package com.rave.visiit.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.Hotel;
import com.rave.visiit.model.Enquiry;
import com.rave.visiit.model.SearchField;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;

public class CustomerEnquiryDaoImpl implements CustomerEnquiryDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public CustomerEnquiry saveandupdate(CustomerEnquiry customerEnquiry) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(customerEnquiry);
		return customerEnquiry;
	}

	@SuppressWarnings("null")
	@Override
	public Enquiry getAllEnquiry(Map searchFields, String sortType,
			String sortValue, String filterType, String startIndex,
			String endIndex) {
		Enquiry enqury = new Enquiry();
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(CustomerEnquiry.class);
		if (ConstantUtil.GLOBAL.equalsIgnoreCase(filterType)) {
			Date endDate = new Date();
			Date startDate = CommonUtil.DateCalculation(endDate, 10);
			if (startDate != null && endDate != null) {
				c.add(Restrictions.between(ConstantUtil.ENQ_CREATED_ON,
						startDate, endDate));

			}

		}
		if ((sortType != null && !sortType.isEmpty())
				&& (sortValue != null && !sortValue.isEmpty())) {
			if (sortType.equalsIgnoreCase(ConstantUtil.ASC)) {
				c.addOrder(Order.asc(sortValue));
			} else {
				c.addOrder(Order.desc(sortValue));
			}
		} else {
			c.addOrder(Order.desc("enqId"));
		}
		if (searchFields != null && !searchFields.isEmpty()) {

			Iterator entries = searchFields.entrySet().iterator();
			while (entries.hasNext()) {
				Entry searchField = (Entry) entries.next();
				if (!searchField.getValue().toString().equals("")) {

					if (searchField.getKey().toString()
							.equalsIgnoreCase("customer")) {

						CustRegistration custRegistration = new CustRegistration();
						custRegistration.setCustomerId(Integer
								.parseInt(searchField.getValue().toString()));
						c.add(Restrictions.eq(searchField.getKey().toString(),
								custRegistration));

					} else {
						c.add(Restrictions.like(
								searchField.getKey().toString(), searchField
										.getValue().toString(),
								MatchMode.ANYWHERE));
					}
				}
			}

			// for (SearchField searchField : searchFields) {
			// c.add(Restrictions.ilike(searchField.getKey(),
			// searchField.getValue()));
			// }
		}
		enqury.setMaxRecord(c.list().size());
		if ((startIndex != null && !startIndex.equals(""))
				&& (endIndex != null && !startIndex.equals(""))) {

			c.setFirstResult(Integer.parseInt(startIndex));
			c.setMaxResults(Integer.parseInt(endIndex));
		}
		enqury.setCustomerEnquiry(c.list());
		return enqury;
	}

	@Override
	public CustomerEnquiry getById(Integer id) {
		Session session = getSessionFactory().getCurrentSession();
		CustomerEnquiry customerEnquiry = (CustomerEnquiry) session.get(
				CustomerEnquiry.class, id);
		return customerEnquiry;
	}

	@Override
	@Transactional
	public Integer getNoQuiryRequest() {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(CustomerEnquiry.class);

		return c.list().size();
	}

	@Override
	@Transactional
	public Integer getNoQuiryReply() {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(CustomerEnquiry.class);
		c.add(Restrictions.eq("enqStatus", ConstantUtil.CLOSED));
		return c.list().size();
		
	}

}
