package com.rave.visiit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.City;
import com.rave.visiit.entity.Country;
import com.rave.visiit.entity.Locations;
import com.rave.visiit.entity.State;

@Repository
public class CountryDaoImpl extends AbstractDao implements CountryDao {

	private static Log log = LogFactory.getLog(CountryDao.class);

	@Override
	public List<State> getStates(Integer countryId) throws Exception {
		log.debug("getting all State");
		Session session = getSessionFactory().getCurrentSession();
		List<State> list = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(State.class);
				crt.add(Restrictions.eq("country", countryId));
				// crt.add(Restrictions.eq("stateIsactive", "Y"));
				crt.addOrder(Order.asc("stateName"));
				list = crt.list();
				if (list == null) {
					log.debug("get successful, no instance found");
				} else {
					log.debug("get successful, instance found");
				}
			}
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<City> getCities(Integer stateId) throws Exception {
		log.debug("getting all cities");
		Session session = getSessionFactory().getCurrentSession();
		List<City> list = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(City.class);
				crt.add(Restrictions.eq("state", stateId));
				// crt.add(Restrictions.eq("cityIsactive", "Y"));
				crt.addOrder(Order.asc("cityName"));
				list = crt.list();
				if (list == null) {
					log.debug("get successful, no instance found");
				} else {
					log.debug("get successful, instance found");
				}
			}
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Locations> getLocations(Integer cityId) throws Exception {
		log.debug("getting all locations");
		Session session = getSessionFactory().getCurrentSession();
		List<Locations> list = null;
		try {
			if (session != null) {
				Criteria crt = session.createCriteria(Locations.class);
				crt.add(Restrictions.eq("city", cityId));
				// crt.add(Restrictions.eq("locIsactive", "Y"));
				crt.addOrder(Order.asc("locName"));
				list = crt.list();
				if (list == null) {
					log.debug("get successful, no instance found");
				} else {
					log.debug("get successful, instance found");
				}
			}
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional
	public List<Country> getAllCountry() throws Exception {
		log.debug("getting all Country");
		Session session = getSessionFactory().getCurrentSession();
		List<Country> list = null;
		try {
			if (session != null) {
				list = session.createQuery("from Country").list();
			}
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public Country getCountry(Integer id) throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Country p = (Country) session.get(Country.class, new Integer(id));
		return p;
	}

	@Override
	public State getState(Integer stateId) throws Exception {
		State p = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			p = (State) session.get(State.class, new Integer(stateId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public City getCity(Integer cityId) throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		City p = (City) session.get(City.class, new Integer(cityId));
		return p;
	}

	@Override
	public Locations getLocation(Integer locId) throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Locations p = (Locations) session.get(Locations.class, new Integer(locId));
		return p;
	}

	@Override
	@Transactional
	public List<State> getAllStates(List<Country> country,boolean isActive) throws Exception {
		List<Integer> cntryIds = new ArrayList<Integer>();
		for (Country cntry : country) {
			cntryIds.add(cntry.getCountryId());
		}
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(State.class);
		c.add(Restrictions.in("country", cntryIds));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(isActive){
			c.add(Restrictions.ne("stateIsactive", Boolean.FALSE));
		}
		return c.list();
	}

	@Override
	@Transactional
	public List<City> getAllCities(List<State> state,boolean isActive) throws Exception {
		List<Integer> stateIds = new ArrayList<Integer>();
		for (State st : state) {
			stateIds.add(st.getStateId());
		}
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(City.class);
		c.add(Restrictions.in("state", stateIds));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(isActive){
			c.add(Restrictions.ne("cityIsactive", Boolean.FALSE));
		}
		c.addOrder(Order.asc("cityName"));
		
		return c.list();
	}

	@Override
	@Transactional
	public List<Locations> getAllLocations(List<City> city,boolean isActive) throws Exception {
		List<Integer> cityIds = new ArrayList<Integer>();
		for (City cty : city) {
			cityIds.add(cty.getCityId());
		}
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Locations.class);
		c.add(Restrictions.in("city", cityIds));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(isActive){
			c.add(Restrictions.ne("locIsactive", Boolean.FALSE));
		}
		c.addOrder(Order.asc("locName"));
		return c.list();
	}
	@Override
	@Transactional
	public List<State> getAllStatesByCountryId(Integer cntryId,boolean isActive) throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(State.class);
		c.add(Restrictions.eq("country", cntryId));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(isActive){
			c.add(Restrictions.ne("stateIsactive", Boolean.FALSE));
		}
		c.addOrder(Order.asc("stateName"));
		return c.list();
	}

	@Override
	@Transactional
	public List<City> getAllCitiesByStateIds(List<Integer> stateIds,boolean isActive)
			throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(City.class);
		c.add(Restrictions.eq("state", stateIds.get(0)));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(isActive){
			c.add(Restrictions.ne("cityIsactive", Boolean.FALSE));
		}
		c.addOrder(Order.asc("cityName"));
		return c.list();
	}

	@Override
	@Transactional
	public List<Locations> getAllLocationsByCityIds(List<Integer> cityIds,boolean isActive)
			throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Locations.class);
		c.add(Restrictions.in("city", cityIds));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(isActive){
			c.add(Restrictions.ne("locIsactive", Boolean.FALSE));
		}
		c.addOrder(Order.asc("locName"));
		return c.list();
	}
	
	@Transactional
	public List<Country> getAllCountries(boolean isActive)throws Exception {
		log.debug("getting all Country");
		Session session = getSessionFactory().getCurrentSession();
		List<Country> list = null;
		try {
			if (session != null) {
				if(!isActive){
					list = session.createQuery("from Country where isDeleted is false ORDER BY country_name asc").list();
				} else {
					list = session.createQuery("from Country where isDeleted is false and countryIsactive is true ORDER BY country_name asc").list();
				}
			}
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			//throw re;
		}
		return list;
	}
}