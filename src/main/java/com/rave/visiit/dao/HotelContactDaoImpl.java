package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.HotelContact;

@Repository
public class HotelContactDaoImpl extends AbstractDao implements HotelContactDao {

	@Override
	public HotelContact saveorupdate(HotelContact hotelCont) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(hotelCont);
		return hotelCont;
	}

	@Override
	public boolean delete(HotelContact hotelCont) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(hotelCont);
		return true;
	}

	@Override
	public List<HotelContact> getAll() throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(HotelContact.class).list();
	}

	@Override
	public HotelContact get(Integer id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		HotelContact hotel = (HotelContact) session.get(HotelContact.class,id);
		return hotel;
	}
	

}
