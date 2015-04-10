package com.rave.visiit.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.model.TravellerModel;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;

public class TravellerDetailDaoImpl implements TravellerDetailDao {

	private SessionFactory sessionFactory;

	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public TravellerDetail saveorupdateTraveler(
			TravellerDetail travellerDetail, Integer packId) {
		Session session = getSessionFactory().getCurrentSession();
		if (travellerDetail.getPack() == null) {
			Package pack = new Package();
			pack.setPkId(packId);
			travellerDetail.setPack(pack);
		}

		session.saveOrUpdate(travellerDetail);
		return travellerDetail;
	}

	@Override
	@Transactional
	public PersonDetail saveorupdatePerson(List<PersonDetail> personDetailList,
			Integer travellerId, Integer packId) {
		Session session = getSessionFactory().getCurrentSession();
		Package pack = new Package();
		pack.setPkId(packId);
		TravellerDetail travellerDetail = new TravellerDetail();
		travellerDetail.setId(travellerId);
		for (PersonDetail personDetail : personDetailList) {
			personDetail.setTravellerDetail(travellerDetail);
			personDetail.setPack(pack);
			session.saveOrUpdate(personDetail);
		}
		return null;
	}

	@Override
	@Transactional
	public TravellerDetail getTravellerDetailByTripCode(String tripCode) {
		TravellerDetail travelerDetail = null;
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(TravellerDetail.class);
		c.add(Restrictions.eq("tripcode", tripCode));
		if (c.list() != null && c.list().size() > 0)
			travelerDetail = (TravellerDetail) c.list().get(0);
		return travelerDetail;
	}

	@Override
	@Transactional
	public List<PersonDetail> getPersonDetailByTraverlerId(
			TravellerDetail traveler) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PersonDetail.class);
		c.add(Restrictions.eq("travellerDetail", traveler));
		return c.list();
	}

	@Override
	public TravellerModel getTravellerDetails(Map searchFields,
			String sortType, String sortValue, String filterType,
			String startIndex, String endIndex) {

		TravellerModel travellerDetailModel = new TravellerModel();
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(TravellerDetail.class);
		if ((sortType != null && !sortType.isEmpty())
				&& (sortValue != null && !sortValue.isEmpty())) {
			if (sortType.equalsIgnoreCase(ConstantUtil.ASC)) {
				c.addOrder(Order.asc(sortValue));
			} else {
				c.addOrder(Order.desc(sortValue));
			}
		} else {
			// c.addOrder(Order.desc(ConstantUtil.ENQ_STATUS));
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

					} else if (searchField.getKey().toString()
							.equalsIgnoreCase("travelDate")
							|| searchField.getKey().toString()
									.equalsIgnoreCase("paymentDate")) {
						String[] spltdateString = searchField.getValue()
								.toString().split("#");

						try {

							Date startDate =CommonUtil.getDateFormat(formatter.parse(spltdateString[0]), 0, 0, 0);
							Date endDate =CommonUtil.getDateFormat(formatter.parse(spltdateString[1]), 23, 59, 59);
							if (startDate != null && endDate != null) {
								
									c.add(Restrictions.between(searchField
											.getKey().toString(), startDate,
											endDate));
								
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {

						c.add(Restrictions.like(
								searchField.getKey().toString(), searchField
										.getValue().toString(),
								MatchMode.ANYWHERE));
					}
				}

			}

		}
		if (c.list() != null) {

			travellerDetailModel.setMaxRecord(c.list().size());
			if ((startIndex != null && !startIndex.equals(""))
					&& (endIndex != null && !startIndex.equals(""))) {

				c.setFirstResult(Integer.parseInt(startIndex));
				c.setMaxResults(Integer.parseInt(endIndex));
			}
			travellerDetailModel.setTravellerDetail(c.list());
		}
		return travellerDetailModel;
	}

	@Override
	@Transactional
	public List<PersonDetail> getAllPersonByTraverlerId(Integer travelerId) {
		Session session = getSessionFactory().getCurrentSession();
		List<PersonDetail> list = null;
		try {
			if (session != null) {
				list = session.createQuery(
						"from PersonDetail where fk_traveller_id = "
								+ travelerId).list();
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}

		/*
		 * Session session = this.getSessionFactory().getCurrentSession();
		 * Criteria c = session.createCriteria(PersonDetail.class);
		 * c.add(Restrictions.eq("travellerDetail", traveler)); return c.list();
		 */
	}

	@Override
	@Transactional
	public Integer getTravellerDetailDao() {
		Session session = getSessionFactory().getCurrentSession();

		Criteria c = session.createCriteria(TravellerDetail.class);
		c.add(Restrictions.eq("paymentStatus", Constants.SUCCESS));
		return c.list().size();
	}

	@Override
	@Transactional
	public Integer getNoTripCompleted() {
		Session session = getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(TravellerDetail.class);
		c.add(Restrictions.eq("paymentStatus", Constants.SUCCESS));
		c.add(Restrictions.eq("approvedStatus", Constants.APPROVED));
		return c.list().size();
	}
}
