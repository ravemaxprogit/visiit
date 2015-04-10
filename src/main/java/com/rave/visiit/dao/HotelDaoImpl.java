package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Query;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Hotel;
import com.rave.visiit.entity.HotelContact;
import com.rave.visiit.entity.HotelKey;
import com.rave.visiit.entity.PackageHotel;

@Repository
public class HotelDaoImpl extends AbstractDao implements HotelDao {

	@Override
	public Hotel saveorupdate(Hotel hotel) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(hotel);
		return hotel;
	}

	@Override
	public boolean delete(Hotel hotel) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(hotel);
		return true;
	}

	@Override
	public List<Hotel> getAll() throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Hotel.class);
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		return c.list();
	}

	@Override
	public Hotel get(Integer id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Hotel hotel = (Hotel) session.get(Hotel.class,id);
		return hotel;
	}

	@Override
	public String generateHotelCode() {
		Session session = getSessionFactory().getCurrentSession();
		HotelKey hotelKey = null;
		  String prefix = "HTL";
		String hotalCode = null;
		try {
			if (session != null) {
				Criteria c = session.createCriteria(HotelKey.class);
				c.addOrder(Order.desc("key"));
				c.setMaxResults(1);
				hotelKey = (HotelKey)c.uniqueResult();
				int key=1;
				if(hotelKey!=null){
				key=hotelKey.getKey()+1;
				}
				hotalCode = prefix + StringUtils.leftPad("" + key,4, '0');
				
			}	
		} catch (RuntimeException re) {
//			log.error("get failed", re);
			throw re;
		}

		return hotalCode;
	}

	@Override
	@Transactional
	public HotelKey saveorupdateHotelkey(HotelKey hotelkey) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(hotelkey);
		return hotelkey;
	}

	@Override
	@Transactional
	public List<HotelContact> getHotelContact(Integer id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(HotelContact.class);
		c.add(Restrictions.eq("hotelDetail", id));
		return c.list();
	}
	
	@Override
	public List<PackageHotel> getAllHotelByPKId(Integer id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		List<PackageHotel> list = null;
		try {
			if (session != null) {
				list = session.createQuery("from PackageHotel where isDeleted is false and pkh_pk_id = " +id).list();
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
		
		
		/*Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PackageHotel.class);
		c.add(Restrictions.eq("pack", id));
		return c.list();*/
	}
	
	@Override
	public List<Hotel> getHotelByCity(String id) throws Exception {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(Hotel.class);
		c.add(Restrictions.eq("hdCity", id));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		return c.list();
	}
	
	@Override
	public String getImageUrlById(Integer id) {
		Session session = getSessionFactory().getCurrentSession();
		String imageUrl = null;
			if (session != null) {
				List imageUrls = null;
				Query query = session.createQuery("select imageUrl from Hotel where isDeleted is false and hdSeqId = " +id);
				imageUrls = query.list();
				if(imageUrls != null && !imageUrls.isEmpty()){imageUrl = (String) imageUrls.get(0);}
			}
			return imageUrl;
	}
	
	@Override
	public Long getHotelCountByCountryId(Integer countryId) {
		Long count = null;
		Session session = getSessionFactory().getCurrentSession();
			if (session != null) {
				Query query = session.createQuery("select count(hdSeqId) from Hotel where isDeleted is false and hdCountry = " +countryId);
				count = (Long) query.uniqueResult();
			}
			return count;
	}
	
	@Override
	public Long getHotelCountByStateId(Integer stateId) {
		Long count = null;
		Session session = getSessionFactory().getCurrentSession();
			if (session != null) {
				Query query = session.createQuery("select count(hdSeqId) from Hotel where isDeleted is false and hdState = " +stateId);
				count = (Long) query.uniqueResult();
			}
			return count;
	}
	
	@Override
	public Long getHotelCountByCityId(Integer cityId) {
		Long count = null;
		Session session = getSessionFactory().getCurrentSession();
			if (session != null) {
				Query query = session.createQuery("select count(hdSeqId) from Hotel where isDeleted is false and hdCity = " +cityId);
				count = (Long) query.uniqueResult();
			}
			return count;
	}

	@Override
	public boolean validateVendorRefference(Integer hotelId) throws Exception {
		List list = getTableRefForField("hotel_detail", "hd_seq_id");
		return checkHotelFieldValueReference(hotelId, list);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean checkHotelFieldValueReference(Integer id, List list) {
		Session session = getSessionFactory().getCurrentSession();
		boolean isRefered = false;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] str = (Object[]) list.get(i);
				String tableName = (String) str[0];
				String columnName = (String) str[1];
				if (!tableName.equalsIgnoreCase("hotel_contact")) {
					String qry = "select count(*) as count from " + tableName + " where " + columnName + "=" + id + " and is_deleted=false";
					Query query = session.createSQLQuery(qry).addScalar("count", Hibernate.LONG);
					List<Long> refList = query.list();
					if (refList != null && refList.get(0) != null && refList.get(0) != 0) {
						isRefered = true;
						return isRefered;
					}
				}
			}
		}
		return isRefered;
	}
}
